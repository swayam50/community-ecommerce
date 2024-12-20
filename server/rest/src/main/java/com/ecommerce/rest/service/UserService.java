package com.ecommerce.rest.service;

import java.util.UUID;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ecommerce.rest.model.data.UserData;
import com.ecommerce.rest.database.UserDatabase;
import com.ecommerce.rest.resource.v1.UserResource;

@Service
public class UserService implements UserDetailsService {

    private static final Logger logger = Logger.getLogger(UserResource.class.getName());

    @Autowired
    private UserDatabase userDatabase;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserData registerUser(UserData userData) {
        logger.info("Inside registerUser with userData: " + userData);
        String plainPassword = userData.getPassword(), hashedPassword = passwordEncoder.encode(plainPassword);
        userData.setPassword(hashedPassword);

        String userId = UUID.randomUUID().toString();
        userData.setUserId(userId);

        UserData savedUserData = userDatabase.saveUser(userData);
        savedUserData.setPassword(null);
        return savedUserData;
    }

    public UserData loginUser(UserData userData) {
        logger.info("Inside loginUser with userData: " + userData);

        UserData savedUserData = userDatabase.getUserFromUsername()
    }

    @Override
    public UserData loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return userDatabase.getUserFromUsername(username);
        } catch (UsernameNotFoundException ex) {
            throw ex;
        }
    }

    public UserData loadUserByEmail(String email) {
        return null;
    }


}
