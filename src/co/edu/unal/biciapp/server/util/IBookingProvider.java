package co.edu.unal.biciapp.server.util;

import java.util.Date;

import co.edu.unal.biciapp.shared.many2many.guice.IBooking;

public interface IBookingProvider {

	
	IBooking getBooking();
	
	IBooking getBooking(String id, Date date);
}
