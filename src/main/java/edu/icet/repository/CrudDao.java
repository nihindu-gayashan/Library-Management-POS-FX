package edu.icet.repository;

public interface CrudDao <T>extends SuperDao {
    boolean save(T t);
}
