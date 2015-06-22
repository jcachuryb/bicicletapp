package co.edu.unal.biciapp.dao.ofy;

import java.util.LinkedList;
import java.util.List;

import static co.edu.unal.biciapp.service.ofy.OfyService.ofy;
import co.edu.unal.biciapp.dao.IUserDAO;
import co.edu.unal.biciapp.shared.many2many.guice.IUser;
import co.edu.unal.biciapp.shared.many2many.guice.ofy.OfyUser;

import com.google.inject.Singleton;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;

@Singleton
public class OfyUserDAO implements IUserDAO {

	@Override
	public OfyUser fetchById(String userID) {
		Key<OfyUser> key = Key.create(OfyUser.class, userID);

		return ofy().load().key(key).now();
	}

	@Override
	public void save(IUser user) {
		ofy().save().entity(user).now();
	}

	@Override
	public void dropUser(IUser user) {
		ofy().delete().entity(user).now();
	}

	@Override
	public List<IUser> fetchAllUsers() {
		Query<OfyUser> q = ofy().load().type(OfyUser.class);

		return new LinkedList<IUser>(q.list());
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

}
