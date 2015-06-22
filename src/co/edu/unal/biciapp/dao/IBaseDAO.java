package co.edu.unal.biciapp.dao;

import co.edu.unal.biciapp.shared.many2many.guice.IBase;

public interface IBaseDAO {

	IBase fetchById(String baseID);

	void save(IBase user);

}
