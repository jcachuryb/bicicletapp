package co.edu.unal.biciapp.shared.many2many.guice;

public interface IUser{
	String getName();

	String getLastName();

	String getEmail();

	String getRole();

	String getDocument();

	String getPhoneNumber();

	boolean hasABooking();

	String getAddress();

	Long getBaseID();

	void setName(String s);

	void setLastName(String s);

	void setEmail(String s);

	void setRole(String s);

	void setAddress(String s);

	void setDocument(String s);

	void setPhoneNumber(String s);

	void setBookingState(boolean b);

	void setBaseID(Long baseID);
	
}
