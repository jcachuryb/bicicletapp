package co.edu.unal.biciapp.shared.many2many.guice.ofy;

import java.io.Serializable;
import java.util.Date;

import co.edu.unal.biciapp.shared.many2many.guice.IBooking;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class OfyBooking implements IBooking, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private Date date;

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public Date getBookingDate() {
		// TODO Auto-generated method stub
		return date;
	}

	@Override
	public void setID(String bookingID) {
		this.id = bookingID;
	}

	@Override
	public void setDate(Date date) {
		this.date = date;
	}

}
