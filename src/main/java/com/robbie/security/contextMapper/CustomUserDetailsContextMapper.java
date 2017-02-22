package com.robbie.security.contextMapper;

import com.robbie.mvc.entity.User;
import com.robbie.mvc.services.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component("contextMapper")
@Getter @Setter
public class CustomUserDetailsContextMapper implements UserDetailsContextMapper {
    @Autowired
    private UserService userService;
    public UserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection<? extends GrantedAuthority> authorities) {
        User user = (User) userService.loadUserByUsername(username);
        user.setFirstName(ctx.getStringAttribute("givenName"));
        user.setLastName(ctx.getStringAttribute("sn"));
        user.setUsername(username);
//        user.setPassword(ctx.getStringAttribute("userPassword"));
        user.setAuthorities(authorities);
        return user;
    }

    public void mapUserToContext(UserDetails user, DirContextAdapter ctx) {
        User user1 = (User)user;
        ctx.setAttributeValue("givenName",user1.getLastName());
        ctx.setAttributeValue("sn",user1.getFirstName());
        ctx.setAttributeValue("uid",user1.getUsername());
    }
}
