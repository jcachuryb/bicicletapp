package co.edu.unal.biciapp.dao;

import java.util.List;

import co.edu.unal.biciapp.shared.many2many.guice.IBooking;

public interface IBookingDAO {
	IBooking fetchById(String bookingID);

	void save(IBooking booking);

	void drop(IBooking booking);
	
	List<IBooking> getAllBookings();

}
