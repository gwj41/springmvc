package com.robbie.mvc.services;

import com.robbie.mvc.entity.Dealership;

import java.util.List;

public interface DealershipService {
    List<Dealership> findAll();
    Dealership findOne(Long id);
    void delete(Long id);
}
