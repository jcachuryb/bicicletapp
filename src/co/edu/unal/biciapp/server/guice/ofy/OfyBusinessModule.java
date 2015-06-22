package co.edu.unal.biciapp.server.guice.ofy;

import co.edu.unal.biciapp.dao.IBaseDAO;
import co.edu.unal.biciapp.dao.IBookingDAO;
import co.edu.unal.biciapp.dao.IUserDAO;
import co.edu.unal.biciapp.dao.ofy.OfyBaseDAO;
import co.edu.unal.biciapp.dao.ofy.OfyBookingDAO;
import co.edu.unal.biciapp.dao.ofy.OfyUserDAO;
import co.edu.unal.biciapp.server.util.IBaseProvider;
import co.edu.unal.biciapp.server.util.IBookingProvider;
import co.edu.unal.biciapp.server.util.IUserProvider;
import co.edu.unal.biciapp.server.util.impl.IBaseProviderImpl;
import co.edu.unal.biciapp.server.util.impl.IBookingProviderImpl;
import co.edu.unal.biciapp.server.util.impl.IUserProviderImpl;
import co.edu.unal.biciapp.shared.many2many.guice.IBase;
import co.edu.unal.biciapp.shared.many2many.guice.IBooking;
import co.edu.unal.biciapp.shared.many2many.guice.IUser;
import co.edu.unal.biciapp.shared.many2many.guice.ofy.OfyBase;
import co.edu.unal.biciapp.shared.many2many.guice.ofy.OfyBooking;
import co.edu.unal.biciapp.shared.many2many.guice.ofy.OfyUser;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.googlecode.objectify.ObjectifyFilter;

public class OfyBusinessModule extends AbstractModule {
	public static boolean isActive = false;

	@Override
	protected void configure() {
		isActive = true;
		System.out.println("*** OfyBusinessModule::configure(): "
				+ "binding interfaces to OFY classes");

		bind(ObjectifyFilter.class).in(Singleton.class);

		bind(IUser.class).to(OfyUser.class);
		bind(IBooking.class).to(OfyBooking.class);
		bind(IBase.class).to(OfyBase.class);
		 
		bind(IUserDAO.class).to(OfyUserDAO.class);
		bind(IBaseDAO.class).to(OfyBaseDAO.class);
		bind(IBookingDAO.class).to(OfyBookingDAO.class);
		
		bind(IUserProvider.class).to(IUserProviderImpl.class);
		bind(IBaseProvider.class).to(IBaseProviderImpl.class);
		bind(IBookingProvider.class).to(IBookingProviderImpl.class);
	}

}
