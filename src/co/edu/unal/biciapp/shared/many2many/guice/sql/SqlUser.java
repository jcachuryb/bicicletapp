package co.edu.unal.biciapp.shared.many2many.guice.sql;

import co.edu.unal.biciapp.shared.many2many.guice.IUser;

public class SqlUser implements IUser {

	private String id;
	private String name;
	private String role;
	private String lastName;
	private String doc;
	private String phone;
	private String address;
	private boolean hasABooking;
	private Long baseID=(long) 0;
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public String getRole() {
		// TODO Auto-generated method stub
		return role;
	}

	@Override
	public String getDocument() {
		// TODO Auto-generated method stub
		return doc;
	}

	@Override
	public String getPhoneNumber() {
		// TODO Auto-generated method stub
		return phone;
	}

	@Override
	public void setName(String s) {
		this.name = s;
	}

	@Override
	public void setEmail(String s) {
		this.id = s;
	}

	@Override
	public void setRole(String s) {
		this.role = s;
	}

	@Override
	public void setDocument(String s) {
		this.doc = s;
	}

	@Override
	public void setPhoneNumber(String s) {
		this.phone = s;
	}


	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String s) {
		this.lastName = s;
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public void setAddress(String s) {
		this.address = s;
	}

	@Override
	public Long getBaseID() {
		return baseID;
	}

	@Override
	public void setBaseID(Long baseID) {
		this.baseID = baseID;
	}


	@Override
	public boolean hasABooking() {
		// TODO Auto-generated method stub
		return hasABooking;
	}

	@Override
	public void setBookingState(boolean b) {
		hasABooking = b;
	}

	

}
