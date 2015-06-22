package co.edu.unal.biciapp.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

import co.edu.unal.biciapp.client.GreetingService;
import co.edu.unal.biciapp.dao.IBaseDAO;
import co.edu.unal.biciapp.dao.IBookingDAO;
import co.edu.unal.biciapp.dao.IUserDAO;
import co.edu.unal.biciapp.server.guice.ofy.OfyBusinessModule;
import co.edu.unal.biciapp.server.guice.sql.SqlBusinessModule;
import co.edu.unal.biciapp.server.util.ProviderHelper;
import co.edu.unal.biciapp.shared.BaseInfo;
import co.edu.unal.biciapp.shared.BookingInfo;
import co.edu.unal.biciapp.shared.LoginInfo;
import co.edu.unal.biciapp.shared.UserInfo;
import co.edu.unal.biciapp.shared.many2many.guice.IBase;
import co.edu.unal.biciapp.shared.many2many.guice.IBooking;
import co.edu.unal.biciapp.shared.many2many.guice.IUser;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	Injector injector;
	ProviderHelper providerHelper;

	IUserDAO userDao;
	IBaseDAO baseDao;
	IBookingDAO bookingDao;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

	public GreetingServiceImpl() {
		if (OfyBusinessModule.isActive) {
			injector = Guice.createInjector(new OfyBusinessModule());
		} else {
			injector = Guice.createInjector(new SqlBusinessModule());
		}
		providerHelper = new ProviderHelper(injector);

		userDao = injector.getInstance(IUserDAO.class);
		baseDao = injector.getInstance(IBaseDAO.class);
		bookingDao = injector.getInstance(IBookingDAO.class);

	}

	private static Logger log = Logger.getLogger(GreetingServiceImpl.class
			.getCanonicalName());

	@Override
	public String getUserEmail(final String token) {
		final UserService userService = UserServiceFactory.getUserService();
		final User user = userService.getCurrentUser();
		if (user != null) {
			return user.getEmail();
		} else {
			return "noreply@sample.com";
		}
	}

	@Override
	public LoginInfo login(final String requestUri) {
		final UserService userService = UserServiceFactory.getUserService();
		final User user = userService.getCurrentUser();
		final LoginInfo loginInfo = new LoginInfo();
		if (user != null) {
			loginInfo.setLoggedIn(true);
			loginInfo.setName(user.getEmail());
			loginInfo.setLogoutUrl(userService.createLogoutURL(requestUri));
		} else {
			loginInfo.setLoggedIn(false);
			loginInfo.setLoginUrl(userService.createLoginURL(requestUri));
		}
		return loginInfo;
	}

	@Override
	public UserInfo loginDetails(final String token) {
		String url = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token="
				+ token;
		String email = getUserEmail(token);

		final StringBuffer r = new StringBuffer();
		try {
			final URL u = new URL(url);
			final URLConnection uc = u.openConnection();
			final int end = 1000;
			InputStreamReader isr = null;
			BufferedReader br = null;
			try {
				isr = new InputStreamReader(uc.getInputStream());
				br = new BufferedReader(isr);
				final int chk = 0;
				while ((url = br.readLine()) != null) {
					if ((chk >= 0) && ((chk < end))) {
						r.append(url).append('\n');
					}
				}
			} catch (final java.net.ConnectException cex) {
				r.append(cex.getMessage());
			} catch (final Exception ex) {
				log.log(Level.SEVERE, ex.getMessage());
			} finally {
				try {
					br.close();
				} catch (final Exception ex) {
					log.log(Level.SEVERE, ex.getMessage());
				}
			}
		} catch (final Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}

		final UserInfo userInfo = new UserInfo();
		userInfo.setEmail(email);
		userInfo.setDocument("");
		userInfo.setAddress("");
		userInfo.setPhoneNumber("");
		userInfo.setRole(UserInfo.ROLE_NORMAL);
		try {
			final JsonFactory f = new JsonFactory();
			JsonParser jp;
			jp = f.createJsonParser(r.toString());
			jp.nextToken();
			while (jp.nextToken() != JsonToken.END_OBJECT) {
				final String fieldname = jp.getCurrentName();
				jp.nextToken();
				if ("picture".equals(fieldname)) {
					userInfo.setPictureUrl(jp.getText());
				} else if ("given_name".equals(fieldname)) {
					userInfo.setName(jp.getText());
				} else if ("family_name".equals(fieldname)) {
					userInfo.setLastName(jp.getText());
				}
			}
		} catch (final JsonParseException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (final IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		}

		return userInfo;
	}

	@Override
	public IUser loadUser(String email) {
		return userDao.fetchById(email);
	}

	@Override
	public BookingInfo loadBooking(String bookingID) {
		BookingInfo bi = new BookingInfo();
		try {
			IBooking b = bookingDao.fetchById(bookingID);
			bi.setDate(b.getBookingDate());
			bi.setUserID(b.getID());
		} catch (Exception e) {
			return null;
		}
		return bi;
	}

	@Override
	public BaseInfo loadBase(String baseID) {
		BaseInfo bi = new BaseInfo();
		try {
			IBase b = baseDao.fetchById(baseID);
			bi.setBaseID(Long.parseLong(baseID));
			bi.setInUseBikes(b.getInUseBikes());
			bi.setTotalBikes(b.getTotalBikes());
		} catch (Exception e) {
			System.out.println(e);
			return new BaseInfo();
		}
		return bi;
	}

	@Override
	public boolean saveUser(UserInfo user) {
		IUser u = providerHelper.userProvider.getNewUser();
		try {
			u.setEmail(user.getEmail());
			u.setName(user.getName());
			u.setLastName(user.getLastName());
			u.setDocument(user.getDocument());
			u.setPhoneNumber(user.getPhoneNumber());
			u.setAddress(user.getAddress());
			u.setBookingState(user.hasABooking());
			u.setRole(user.getRole());
			userDao.save(u);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	@Override
	public boolean createBooking(BookingInfo bookingInfo) {
		try {
			IBooking mainBooking = providerHelper.bookingProvider.getBooking();
			mainBooking.setID(bookingInfo.getUserID());
			mainBooking.setDate(bookingInfo.getDate());
			bookingDao.save(mainBooking);

			IUser mainUser = userDao.fetchById(bookingInfo.getUserID());
			mainUser.setBookingState(true);
			userDao.save(mainUser);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean cancelBooking(String bookingID) {
		try {
			IBooking b = providerHelper.bookingProvider.getBooking();
			b.setID(bookingID);
			bookingDao.drop(b);
			
			IUser u = userDao.fetchById(bookingID);
			u.setBookingState(false);
			userDao.save(u);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public BookingInfo loadBookingByOperator(String bookingID) {
		BookingInfo bi = new BookingInfo();
		try {
			IBooking b = bookingDao.fetchById(bookingID);
			IUser u = userDao.fetchById(bookingID);

			bi.setDate(b.getBookingDate());
			bi.setUserID(b.getID());
			bi.userDoc = u.getDocument();
			bi.userName = u.getName() + u.getLastName();
		} catch (Exception e) {
			return null;
		}
		return bi;
	}

}
