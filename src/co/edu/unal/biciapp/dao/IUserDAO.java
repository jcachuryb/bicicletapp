package co.edu.unal.biciapp.dao;

import java.util.List;

import co.edu.unal.biciapp.shared.many2many.guice.IUser;

public interface IUserDAO {

	IUser fetchById(String userID);

	void save(IUser user);

	void dropUser(IUser user);

	List<IUser> fetchAllUsers();

	boolean isEmpty();

}
