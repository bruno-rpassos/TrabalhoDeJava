package dao;

import java.util.List;

import model.Entity;
import model.Listener;

public interface DAO<T extends Entity> {
	public void addListener( Listener l );

	public T getById( Integer id ) throws Exception;

	public List<T> list();

	public void saveOrUpdate( T obj ) throws Exception;
}
