package controller;

import java.util.List;

import exception.SQLException;
import exception.TypeNotFoundException;

public interface Controller<T> {

	public void create( final T t );

	public void edit( final Integer id ) throws SQLException, TypeNotFoundException;

	public T get( Integer id );

	public List<T> getAll();

	public void list();

	public void newResource();

	public void update( final T t );
}
