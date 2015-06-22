package co.edu.unal.biciapp.service.ofy;

import co.edu.unal.biciapp.shared.many2many.guice.ofy.JCPoint;
import co.edu.unal.biciapp.shared.many2many.guice.ofy.OfyBase;
import co.edu.unal.biciapp.shared.many2many.guice.ofy.OfyBooking;
import co.edu.unal.biciapp.shared.many2many.guice.ofy.OfyUser;
import static com.googlecode.objectify.ObjectifyService.factory;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

public class OfyService {

	static {

		factory().register(OfyUser.class);
		factory().register(OfyBase.class);
		factory().register(OfyBooking.class);
		factory().register(JCPoint.class);
	}

	public static Objectify ofy() {
		return ObjectifyService.ofy();
	}
}
