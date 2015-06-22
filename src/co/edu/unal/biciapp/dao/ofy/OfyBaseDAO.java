package co.edu.unal.biciapp.dao.ofy;

import static co.edu.unal.biciapp.service.ofy.OfyService.ofy;
import co.edu.unal.biciapp.dao.IBaseDAO;
import co.edu.unal.biciapp.shared.many2many.guice.IBase;
import co.edu.unal.biciapp.shared.many2many.guice.ofy.OfyBase;

import com.google.inject.Singleton;
import com.googlecode.objectify.Key;
@Singleton
public class OfyBaseDAO implements IBaseDAO {

	@Override
	public IBase fetchById(String baseID) {
		Key<OfyBase> key = Key.create(OfyBase.class, Long.parseLong(baseID));

		return ofy().load().key(key).now();
	}

	@Override
	public void save(IBase base) {
		ofy().save().entity(base).now();
	}

}
