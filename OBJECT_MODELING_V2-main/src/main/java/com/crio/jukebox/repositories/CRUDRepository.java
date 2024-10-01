package com.crio.jukebox.repositories;

import java.util.List;
import java.util.Optional;

public interface CRUDRepository<T> {
    public T save(T entity);
    public List<T> findAll();
    public Optional<T> findById(int id);
    public Optional<T> findByName(String name);
}

