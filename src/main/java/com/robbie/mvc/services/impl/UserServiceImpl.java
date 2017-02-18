package com.robbie.mvc.services.impl;

import com.robbie.mvc.entity.User;
import com.robbie.mvc.repository.UserRepository;
import com.robbie.mvc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

//@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findUserById(Long id) {
        return userRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    public List<User> findUserByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }

    @Transactional(readOnly = true)
    public List<User> findUserByLastName(String lastName) {
        return userRepository.findByLastName(lastName);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.delete(id);
    }

    public List<User> findByDealershipName(String name) {
        return userRepository.findByDealershipName(name);
    }

    public List<User> findAllByPage(int pageNo, int pageSize) {
        return userRepository.findAllByOrderByAgeDesc(new PageRequest(pageNo, pageSize));
    }

    public Page<User> findAllByPageAndSort(int pageNo, int pageSize) {
        List<Sort.Order> orders = new ArrayList() {{
            add(new Sort.Order(Sort.Direction.DESC, "age"));
            add(new Sort.Order(Sort.Direction.ASC, "lastName"));
        }};
        return userRepository.findAll(new PageRequest(pageNo, pageSize, new Sort(orders)));
    }

//    @Transactional
//    @Async
    public List<User> findByIDs(Long... ids) {
        List<User> users = new ArrayList<>();
        for (Long id:ids) {
            users.addAll(userRepository.findByIDs(new Long[]{id}));
        }
        return users;
    }

    public Page<User> findByAgeGreatThanOrderByAgeDesc(int pageNo, int pageSize, int age) {
        return userRepository.findByAgeGreaterThanOrderByAgeDesc(age, new PageRequest(pageNo,pageSize));
    }

    public int updateAge(String lastName, int age) {
        return userRepository.updateAge(lastName,age);
    }

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
