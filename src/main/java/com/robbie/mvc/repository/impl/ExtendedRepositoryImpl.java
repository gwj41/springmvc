package com.robbie.mvc.repository.impl;

import com.robbie.mvc.repository.BaseRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.scheduling.annotation.Async;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@SuppressWarnings(value = "unchecked")
public class ExtendedRepositoryImpl<T,ID extends Serializable> extends SimpleJpaRepository<T,ID> implements BaseRepository<T,ID>{
    private JpaEntityInformation entityInformation;
    private EntityManager entityManager;
    public ExtendedRepositoryImpl(JpaEntityInformation entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        this.entityManager = entityManager;
    }

    public ExtendedRepositoryImpl(Class domainClass, EntityManager em) {
        super(domainClass, em);
    }

    public List<T> findByIDs(ID[] ids) {
        Query query = this.entityManager.createQuery("select entity from " +
                entityInformation.getEntityName() + " entity where entity." +
                entityInformation.getIdAttribute().getName() + " in :ids");
        query.setParameter("ids", Arrays.asList(ids));
        long wait = new Random().nextInt(10000 - 1) + 1;
        System.out.println("=========================WAITING FOR " + wait + "===============================");
        try {
            Thread.sleep(wait);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Execute query for ID: " + Arrays.toString(ids));
        return (List<T>)query.getResultList();
    }
}
