package co.edu.unal.biciapp.server.util;

import co.edu.unal.biciapp.shared.many2many.guice.IUser;

public interface IUserProvider {
	
	IUser getNewUser();
}
