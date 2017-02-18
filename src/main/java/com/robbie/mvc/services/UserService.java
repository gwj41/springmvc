package com.robbie.mvc.services;

import com.robbie.mvc.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService{
    List<User> getAllUsers();

    User findUserById(Long id);

    List<User> findUserByFirstName(String firstName);

    List<User> findUserByLastName(String lastName);

    void save(User user);

    void delete(Long id);

    List<User> findByDealershipName(String name);

    List<User> findAllByPage(int pageNo,int pageSize);

    Page<User> findAllByPageAndSort(int pageNo, int pageSize);

    List<User> findByIDs(Long... ids);

    Page<User> findByAgeGreatThanOrderByAgeDesc(int pageNo, int pageSize,int age);

    int updateAge(String lastName,int age);

    Page<User> findAll(Pageable pageable);
}
