package co.edu.unal.biciapp.client;

import co.edu.unal.biciapp.shared.BaseInfo;
import co.edu.unal.biciapp.shared.BookingInfo;
import co.edu.unal.biciapp.shared.LoginInfo;
import co.edu.unal.biciapp.shared.UserInfo;
import co.edu.unal.biciapp.shared.many2many.guice.IUser;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {

	String getUserEmail(String token);

	UserInfo loginDetails(String token);

	LoginInfo login(String requestUri);

	IUser loadUser(String email);

	BookingInfo loadBooking(String bookingID);
	
	BookingInfo loadBookingByOperator(String bookingID);

	BaseInfo loadBase(String baseID);

	boolean saveUser(UserInfo user);
	
	boolean createBooking(BookingInfo bookingInfo);
	
	boolean cancelBooking(String bookingID);

}
