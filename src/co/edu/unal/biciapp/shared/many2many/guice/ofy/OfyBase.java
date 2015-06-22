package co.edu.unal.biciapp.shared.many2many.guice.ofy;

import java.io.Serializable;

import co.edu.unal.biciapp.shared.many2many.guice.IBase;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class OfyBase implements IBase, Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private Long totalBikes;
	private Long onUseBikes;

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public Long getTotalBikes() {
		// TODO Auto-generated method stub
		return totalBikes;
	}

	@Override
	public Long getInUseBikes() {
		// TODO Auto-generated method stub
		return onUseBikes;
	}

	public void setTotalBikes(Long totalBikes) {
		this.totalBikes = totalBikes;
	}

	public void setOnUseBikes(Long onUseBikes) {
		this.onUseBikes = onUseBikes;
	}

	@Override
	public void ask4ABike() {
		onUseBikes++;
	}

	@Override
	public void returnBike() {
		onUseBikes--;
	}

	@Override
	public void setId(String id) {
		this.id = id;		
	}

	

}
