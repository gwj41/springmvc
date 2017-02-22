package com.robbie.mvc.services.impl;

import com.robbie.mvc.entity.Dealership;
import com.robbie.mvc.repository.DealershipRepository;
import com.robbie.mvc.services.DealershipService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "dealershipService")
@Transactional
@Getter @Setter
public class DealershipServiceImpl implements DealershipService {
    @Autowired
    private DealershipRepository dealershipRepository;
    public List<Dealership> findAll() {
        return dealershipRepository.findAll();
    }

    public Dealership findOne(Long id) {
        return dealershipRepository.findOne(id);
    }

    public void delete(Long id) {
        dealershipRepository.delete(id);
    }
}
