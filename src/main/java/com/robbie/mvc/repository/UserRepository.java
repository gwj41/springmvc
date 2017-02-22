package com.robbie.mvc.repository;


import com.robbie.mvc.entity.User;
import com.robbie.mvc.repository.projection.UserProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;

@RepositoryRestResource(path = "suzhou_users", collectionResourceRel = "suzhou_users",
        collectionResourceDescription = @Description(value = "Users in Suzhou City"), excerptProjection = UserProjection.class)
public interface UserRepository extends BaseRepository<User, Long> {
    @RestResource(path = "userFirstName", exported = false)
    List<User> findByFirstName(String firstName);

    @RestResource(path = "userLastName")
    List<User> findByLastName(@Param(value = "lastName") String lastName);

    List<User> findByLastNameContainingOrderByLastNameAsc(String lastName);

    List<User> findByLastNameContainingOrderByLastNameDesc(String lastName);

    List<User> findByBirthdayBefore(Date birthday);

    List<User> findByDealershipName(@Param(value = "name") String name);

    List<User> findAllByOrderByAgeDesc(Pageable pageable);

    Page<User> findByAgeGreaterThanOrderByAgeDesc(int age, Pageable pageable);

    //    @Query(value = "update com.robbie.mvc.entity.User u set u.age = ?2 where u.lastName like ?1")
    @Query(value = "update com.robbie.mvc.entity.User u set u.age = :age where u.lastName like :lastName")
    @Modifying
    int updateAge(@Param(value = "lastName") String lastName, @Param(value = "age") int age);

    User findByUsername(String username);

    String findRoleByUsername(String username);
}
