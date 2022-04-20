package com.samperry.mayparker.security;

import com.samperry.mayparker.database.dao.UserDAO;
import com.samperry.mayparker.database.dao.UserRoleDAO;
import com.samperry.mayparker.database.entity.User;
import com.samperry.mayparker.database.entity.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    public static final Logger LOG = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserDAO userDao;

    @Autowired
    private UserRoleDAO userRoleDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Username '" + username + "' not found in database");
        }

        List<UserRole> userRoles = userRoleDao.findByUserId(user.getId());

        // check the account status
        boolean accountIsEnabled = true;
        // spring security configs
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        // setup user roles
        // List<Permission> permissions = userDao.getPermissionsByEmail(username);
        // Collection<? extends GrantedAuthority> springRoles = buildGrantAuthorities(permissions);
        Collection<? extends GrantedAuthority> springRoles = buildGrantAuthorities(userRoles);

        String password = user.getPassword();

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), accountIsEnabled, accountNonExpired, credentialsNonExpired, accountNonLocked, springRoles);
    }

    private Collection<? extends GrantedAuthority> buildGrantAuthorities(List<UserRole> userRoles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (UserRole role : userRoles) {
            authorities.add(new SimpleGrantedAuthority(role.getUserRole()));
        }

        return authorities;
    }
}
