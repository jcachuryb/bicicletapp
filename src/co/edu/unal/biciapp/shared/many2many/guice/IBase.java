package co.edu.unal.biciapp.shared.many2many.guice;

public interface IBase {

	String getId();

	Long getTotalBikes();

	Long getInUseBikes();

	void ask4ABike();

	void returnBike();

	void setId(String id);

	public void setTotalBikes(Long totalBikes);

	public void setOnUseBikes(Long onUseBikes);

}
