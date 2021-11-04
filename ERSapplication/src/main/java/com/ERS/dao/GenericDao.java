package com.ERS.dao;

import java.util.List;

public interface GenericDao<T> { //  to create a generic interface

		List<T> getAll();
		T getByName(String name);
		void update(T entity);
		void insert(T entity);
		void delete(T entity);
}
// note Resultset is a temporary view of my table