package co.edu.unal.biciapp.server.util.impl;

import javax.inject.Provider;

import co.edu.unal.biciapp.server.util.IUserProvider;
import co.edu.unal.biciapp.shared.many2many.guice.IUser;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class IUserProviderImpl implements IUserProvider {

	
	private Provider<IUser> userProvider;

	@Inject
	public IUserProviderImpl(Provider<IUser> userProvider) {
		this.userProvider = userProvider;
	}
	
	@Override
	public IUser getNewUser() {
		return userProvider.get();
	}

}
