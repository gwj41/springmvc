package com.robbie.mvc.services.impl;

import com.robbie.mvc.entity.User;
import com.robbie.mvc.repository.UserRepository;
import com.robbie.mvc.services.UserService;
import lombok.Getter;
import lombok.Setter;
import org.apache.directory.api.ldap.model.constants.LdapSecurityConstants;
import org.apache.directory.api.ldap.model.password.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.Name;
import javax.naming.directory.*;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserService {
    private static final String BASE_DN = "dc=robbie,dc=com";
    private static final String ROLE_PREFIX = "ROLE_";
    @Autowired
    @Getter @Setter
    private UserRepository userRepository;
    @Autowired
    @Getter @Setter
    private LdapTemplate ldapTemplate;
    @Autowired
    @Getter @Setter
    private StandardPasswordEncoder passwordEncoder;

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
        Name dn = buildDn(user);
        DirContextAdapter context = new DirContextAdapter(dn);
        ldapTemplate.bind(buildAttributes(user,context));
        String userDn = "uid=" + user.getUsername() + ",ou=people," + BASE_DN;
        addUserToGroup("User",userDn);
        user.setRole(ROLE_PREFIX + "User".toUpperCase());
        user.setAuthorities(AuthorityUtils.createAuthorityList(user.getRole()));
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

    public boolean findRoleByUsername(String username) {
        return "ROLE_USER".equalsIgnoreCase(userRepository.findRoleByUsername(username));
    }

    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    private Name buildDn(User user){
        LdapNameBuilder ldapNameBuilder = LdapNameBuilder.newInstance();
        return ldapNameBuilder.add("ou","people").add("uid",user.getUsername()).build();
    }

    private DirContextAdapter buildAttributes(final User user,DirContextAdapter context){
        context.setAttributeValues("objectClass", new String[] {"inetOrgPerson"});
        context.setAttributeValue("cn",user.getFirstName() + " " + user.getLastName());
//        context.setAttributeValue("ou","people");
        context.setAttributeValue("uid",user.getUsername());
        context.setAttributeValue("sn", user.getFirstName());
        context.setAttributeValue("givenName", user.getLastName());
        context.setAttributeValue("userPassword", PasswordUtil.createStoragePassword(user.getPassword(), LdapSecurityConstants.HASH_METHOD_CRYPT_SHA512));
        Attributes attrs = new BasicAttributes();
        BasicAttribute ocattr = new BasicAttribute( "objectClass" );
        ocattr.add( "inetOrgPerson" );
/*        ocattr.add( "organizationalPerson" );
        ocattr.add( "person" );
        ocattr.add( "top" );*/
        attrs.put( ocattr );
        attrs.put( "cn", user.getFirstName() + " " + user.getLastName());
        // attrs.put( "rdn", "uid" ); // TODO check how to assign rdn
        attrs.put( "sn", user.getFirstName());
        attrs.put( "ou", "people");
        attrs.put( "uid", user.getUsername());

        attrs.put( "userPassword", PasswordUtil.createStoragePassword(user.getPassword(), LdapSecurityConstants.HASH_METHOD_CRYPT_SHA512) );
        attrs.put( "givenName", user.getLastName() );
        return context;
    }

    private Name buildGroupDn(String groupName) {
        return LdapNameBuilder.newInstance("ou=groups")
                .add("cn", groupName).build();
    }

    public void addUserToGroup(String groupName, String userDn) {
        Name groupDn = buildGroupDn(groupName);
        DirContextOperations ctx = ldapTemplate.lookupContext(groupDn);
        ctx.addAttributeValue("uniqueMember", userDn);
        ldapTemplate.modifyAttributes(ctx);
//        ldapTemplate.update(ctx);
    }
}
