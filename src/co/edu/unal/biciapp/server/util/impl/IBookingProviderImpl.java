package co.edu.unal.biciapp.server.util.impl;

import java.util.Date;

import javax.inject.Provider;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import co.edu.unal.biciapp.server.util.IBookingProvider;
import co.edu.unal.biciapp.shared.many2many.guice.IBooking;

@Singleton
public class IBookingProviderImpl implements IBookingProvider {
	private Provider<IBooking> bookingProvider;

	@Inject
	public IBookingProviderImpl(Provider<IBooking> bookingProvider) {
		this.bookingProvider = bookingProvider;
	}

	@Override
	public IBooking getBooking() {
		return bookingProvider.get();
	}

	@Override
	public IBooking getBooking(String id, Date date) {
		IBooking b = bookingProvider.get();
		b.setID(id);
		b.setDate(date);
		return b;
	}

}
