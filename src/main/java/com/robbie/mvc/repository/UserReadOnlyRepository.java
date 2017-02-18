package com.robbie.mvc.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface UserReadOnlyRepository<T,ID extends Serializable> extends Repository<T, ID> {
    List<T> findAll();
}
