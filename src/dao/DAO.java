package dao;

import java.sql.SQLException;
import java.util.List;

import model.Entity;

public interface DAO<T extends Entity> {
	public T getById( Integer id ) throws Exception;

	public List<T> list() throws SQLException, Exception;

	public void saveOrUpdate( T obj ) throws Exception;
}
