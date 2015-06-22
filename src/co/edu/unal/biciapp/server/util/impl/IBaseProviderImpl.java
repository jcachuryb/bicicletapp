package co.edu.unal.biciapp.server.util.impl;

import javax.inject.Provider;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import co.edu.unal.biciapp.server.util.IBaseProvider;
import co.edu.unal.biciapp.shared.many2many.guice.IBase;

@Singleton
public class IBaseProviderImpl implements IBaseProvider {

	Provider<IBase> baseProvider;
	
	@Inject
	public IBaseProviderImpl(Provider<IBase> baseProvider) {
		this.baseProvider = baseProvider;
	}

	
	@Override
	public IBase getNewBase() {
		return baseProvider.get();
	}

}
