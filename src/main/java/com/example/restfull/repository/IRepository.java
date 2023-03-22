package com.example.restfull.repository;



import java.util.List;


public interface IRepository<T,TId> {

    public List<T> list();

    public  T getById(TId id);

    public boolean add(T employee);

    public boolean update(T employee);

    public boolean delete(TId id);
    public List<T> search(String searchStr);

    public void setType(Class<T> t);
}
