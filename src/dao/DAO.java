package dao;

import java.util.List;

import model.Entity;

public interface DAO<T extends Entity> {
	public T getById( Integer id ) throws Exception;

	public List<T> list();

	public void saveOrUpdate( T obj ) throws Exception;
}
