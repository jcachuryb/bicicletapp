package co.edu.unal.biciapp.shared.many2many.guice;

import java.util.Date;

public interface IBooking {

	String getID();

	Date getBookingDate();

	void setID(String bookingID);

	void setDate(Date date);

}
