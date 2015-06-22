package co.edu.unal.biciapp.dao.ofy;

import static co.edu.unal.biciapp.service.ofy.OfyService.ofy;

import java.util.LinkedList;
import java.util.List;

import co.edu.unal.biciapp.dao.IBookingDAO;
import co.edu.unal.biciapp.service.ofy.OfyService;
import co.edu.unal.biciapp.shared.many2many.guice.IBooking;
import co.edu.unal.biciapp.shared.many2many.guice.ofy.OfyBooking;

import com.google.inject.Singleton;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;

@Singleton
public class OfyBookingDAO implements IBookingDAO {

	@Override
	public IBooking fetchById(String bookingID) {
		Key<OfyBooking> key = Key.create(OfyBooking.class, bookingID);

		return OfyService.ofy().load().key(key).now();
	}

	@Override
	public void save(IBooking booking) {
		OfyService.ofy().save().entity(booking).now();
	}

	@Override
	public void drop(IBooking booking) {
		OfyService.ofy().delete().entity(booking).now();
	}

	@Override
	public List<IBooking> getAllBookings() {
		Query<OfyBooking> q = ofy().load().type(OfyBooking.class);

		return new LinkedList<IBooking>(q.list());
	}

}
