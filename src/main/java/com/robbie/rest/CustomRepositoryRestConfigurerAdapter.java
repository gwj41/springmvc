package com.robbie.rest;

import com.robbie.mvc.entity.User;
import com.robbie.mvc.repository.UserRepository;
import com.robbie.mvc.repository.projection.UserProjection;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.http.MediaType;

public class CustomRepositoryRestConfigurerAdapter extends RepositoryRestConfigurerAdapter {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.setBasePath("api");
        config.setDefaultMediaType(MediaType.APPLICATION_JSON);
        config.setDefaultPageSize(5);
//        config.getProjectionConfiguration().addProjection(UserProjection.class);
        config.withEntityLookup().forRepository(UserRepository.class, User::getLastName,UserRepository::findByLastName);
        super.configureRepositoryRestConfiguration(config);
    }
}
