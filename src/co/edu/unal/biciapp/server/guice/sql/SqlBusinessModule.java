package co.edu.unal.biciapp.server.guice.sql;

import javax.inject.Singleton;

import co.edu.unal.biciapp.shared.many2many.guice.IUser;
import co.edu.unal.biciapp.shared.many2many.guice.sql.SqlUser;

import com.google.inject.AbstractModule;
import com.googlecode.objectify.ObjectifyFilter;

public class SqlBusinessModule extends AbstractModule {
	public static boolean isActive = false;

	@Override
	protected void configure() {
		isActive = true;
		System.out.println("*** SqlBusinessModule::configure(): "
				+ "binding interfaces to SQL classes");
		bind(ObjectifyFilter.class).in(Singleton.class);
		bind(IUser.class).to(SqlUser.class);
	}

}
