package com.inn.cafe.JWT;

import java.util.ArrayList;
import java.util.Objects;

import com.inn.cafe.dao.UserDao;
import com.inn.cafe.POJO.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    private User userDetail;

    private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Inside loadUserByUsername {}", username);
        userDetail = userDao.findByEmailId(username);
        if (!Objects.isNull(userDetail)) {
            log.info("User found: {} with status: {}", userDetail.getEmail(), userDetail.getStatus());
            return new org.springframework.security.core.userdetails.User(
                    userDetail.getEmail(),
                    userDetail.getPassword(),
                    new ArrayList<>()
            );
        } else {
            log.error("User not found for email: {}", username);
            throw new UsernameNotFoundException("User not found");
        }
    }

    public User getUserDetail() {
        User user = new User();
        user.setId(userDetail.getId());
        user.setName(userDetail.getName());
        user.setEmail(userDetail.getEmail());
        user.setContactNumber(userDetail.getContactNumber());
        user.setStatus(userDetail.getStatus());
        user.setRole(userDetail.getRole());
        user.setPassword(null);
        return user;
    }
}
