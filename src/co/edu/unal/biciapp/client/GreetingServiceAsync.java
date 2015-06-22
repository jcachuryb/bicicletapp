package co.edu.unal.biciapp.client;

import co.edu.unal.biciapp.shared.BaseInfo;
import co.edu.unal.biciapp.shared.BookingInfo;
import co.edu.unal.biciapp.shared.LoginInfo;
import co.edu.unal.biciapp.shared.UserInfo;
import co.edu.unal.biciapp.shared.many2many.guice.IUser;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {

	// TODO #10: create login helper methods in service asynchronous interface
	void getUserEmail(String token, AsyncCallback<String> callback);

	void login(String requestUri, AsyncCallback<LoginInfo> asyncCallback);

	void loginDetails(String token, AsyncCallback<UserInfo> asyncCallback);

	// TODO #10:> end

	void loadUser(String email, AsyncCallback<IUser> asyncCallback);

	void loadBooking(String userID, AsyncCallback<BookingInfo> asyncCallback);
	
	void loadBookingByOperator(String userID, AsyncCallback<BookingInfo> asyncCallback);

	void loadBase(String baseID, AsyncCallback<BaseInfo> asyncCallback);

	void saveUser(UserInfo user, AsyncCallback<Boolean> asyncCallback);

	void createBooking(BookingInfo bookingInfo,
			AsyncCallback<Boolean> asyncCallback);
	
	void cancelBooking(String bookingID,
			AsyncCallback<Boolean> asyncCallback);
}
