package com.robbie.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.scheduling.annotation.Async;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
@SuppressWarnings(value = "unchecked")
public interface BaseRepository<T,ID extends Serializable> extends JpaRepository<T,ID>{
    @Async
    List<T> findByIDs(ID...ids);
}
