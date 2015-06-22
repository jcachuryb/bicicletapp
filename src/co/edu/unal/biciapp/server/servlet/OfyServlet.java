package co.edu.unal.biciapp.server.servlet;

import java.io.IOException;

import javax.inject.Singleton;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.unal.biciapp.shared.many2many.guice.IUser;

import com.google.inject.Inject;
@Singleton
public class OfyServlet extends HttpServlet {

	public static final String PARAM = "param";
	@Inject IUser  iuser;
	@Override
	public void init(final ServletConfig sc) {
		try {
			super.init(sc);

			String param = sc.getInitParameter(PARAM);
			System.out.println("OfyServlet::init(): " + PARAM + "=" + param);

		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Acá le injectamos el usuario??");
	}
}
