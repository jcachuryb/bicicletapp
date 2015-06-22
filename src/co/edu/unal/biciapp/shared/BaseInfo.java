package co.edu.unal.biciapp.shared;

import java.io.Serializable;

public class BaseInfo implements Serializable {

	private Long baseID;
	private Long totalBikes;
	private Long inUseBikes;
	private String baseName;
	public Long getBaseID() {
		return baseID;
	}
	public void setBaseID(Long baseID) {
		this.baseID = baseID;
	}
	public Long getTotalBikes() {
		return totalBikes;
	}
	public void setTotalBikes(Long totalBikes) {
		this.totalBikes = totalBikes;
	}
	public Long getInUseBikes() {
		return inUseBikes;
	}
	public void setInUseBikes(Long inUseBikes) {
		this.inUseBikes = inUseBikes;
	}
	public String getBaseName() {
		return baseName;
	}
	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}


}
