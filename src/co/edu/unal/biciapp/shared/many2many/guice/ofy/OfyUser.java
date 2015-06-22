package co.edu.unal.biciapp.shared.many2many.guice.ofy;

import java.io.Serializable;

import co.edu.unal.biciapp.shared.many2many.guice.IUser;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class OfyUser implements IUser, Serializable , Cloneable{

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String name;
	private String lastName;
	private String role;
	private String doc;
	private String phone;
	private String address;
	private boolean hasABooking = false;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((baseID == null) ? 0 : baseID.hashCode());
		result = prime * result + ((doc == null) ? 0 : doc.hashCode());
		result = prime * result + (hasABooking ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OfyUser other = (OfyUser) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (baseID == null) {
			if (other.baseID != null)
				return false;
		} else if (!baseID.equals(other.baseID))
			return false;
		if (doc == null) {
			if (other.doc != null)
				return false;
		} else if (!doc.equals(other.doc))
			return false;
		if (hasABooking != other.hasABooking)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}

}
