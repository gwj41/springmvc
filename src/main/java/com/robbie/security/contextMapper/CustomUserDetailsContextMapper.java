package com.robbie.security.contextMapper;

import com.robbie.mvc.entity.User;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component("contextMapper")
public class CustomUserDetailsContextMapper implements UserDetailsContextMapper {
    public UserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection<? extends GrantedAuthority> authorities) {
        User user = new User();
        user.setFirstName(ctx.getStringAttribute("givenName"));
        user.setLastModifiedBy(ctx.getStringAttribute("sn"));
        user.setUsername(username);
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
