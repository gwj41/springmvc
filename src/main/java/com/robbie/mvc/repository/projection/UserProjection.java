package com.robbie.mvc.repository.projection;

import com.robbie.mvc.entity.User;
import org.springframework.data.rest.core.config.Projection;
import org.springframework.stereotype.Component;

@Projection(name = "suzhou_users",types = {User.class})
public interface UserProjection {
    String getLastName();
    int getAge();
}
