package com.ecommerce.rest.database;

import java.sql.SQLException;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.ecommerce.rest.exception.UserDuplicationException;
import com.ecommerce.rest.exception.UserException;
import com.ecommerce.rest.exception.UserUpsertionException;
import com.ecommerce.rest.model.data.UserData;
import com.ecommerce.rest.model.persistent.User;
import com.ecommerce.rest.repository.UserRepository;
import com.ecommerce.rest.resource.v1.UserResource;
import org.modelmapper.ModelMapper;

import static com.ecommerce.rest.common.Messages.ErrorMessage.USERNAME_NOT_FOUND;

@Component
public class UserDatabase {

    private static final Logger logger = Logger.getLogger(UserResource.class.getName());

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    public UserData saveUser(UserData userData) throws UserDuplicationException {
        logger.info("Inside saveUser with userData: " + userData);
        try {
            User user = modelMapper.map(userData, User.class);
            userRepository.insert(user);
            return userData;
        } catch (DuplicateKeyException ex) {
            boolean usernamePresent = userRepository.existsByUsername(userData.getUsername());
            boolean emailPresent = userRepository.existsByEmail(userData.getEmail());
            throw new UserDuplicationException(ex.getMessage(), usernamePresent, emailPresent);
        } catch (DataIntegrityViolationException ex) {
            throw new UserUpsertionException("Some exception occurred while saving user.");
        }
    }

    public UserData getUserFromUsername(String username) throws UsernameNotFoundException {
        logger.info("Inside getUserFromUsername with username: " + username);
        return userRepository.findByUsername(username)
                             .map(user -> new UserData(user.getUsername(), user.getPassword(), user.getRole()))
                             .orElseThrow(() -> new UsernameNotFoundException(USERNAME_NOT_FOUND.formatted(username)));
    }

    public UserData getUserFromEmailOrUsername(String username, String email) {
        logger.info("Inside getUserFromEmailOrUsername with username: " + username + " email: " + email);
        return userRepository.findByEmailOrUsername(username, email)
                             .map(user -> new UserData(user.getUsername(), user.getPassword(), user.getRole()))
                             .orElseThrow(() -> new UsernameNotFoundException(USERNAME_NOT_FOUND.formatted(username)));
        ;
    }

}
