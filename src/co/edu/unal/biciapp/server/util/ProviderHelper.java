package co.edu.unal.biciapp.server.util;

import com.google.inject.Injector;

public class ProviderHelper {
	public final IUserProvider userProvider;
	public final IBaseProvider baseProvider;
	public final IBookingProvider bookingProvider;

	public ProviderHelper(Injector injector) {
		userProvider = injector.getInstance(IUserProvider.class);
		baseProvider = injector.getInstance(IBaseProvider.class);
		bookingProvider = injector.getInstance(IBookingProvider.class);
	}
}
