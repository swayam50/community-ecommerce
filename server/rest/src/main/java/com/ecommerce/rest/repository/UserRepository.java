package com.ecommerce.rest.repository;

import java.sql.SQLException;
import java.util.Optional;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ecommerce.rest.model.persistent.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    @Modifying
    @Query("""
        INSERT INTO users (u_id, u_fullname, u_email, u_username, u_password, u_role)
        VALUES (:#{#user.id}, :#{#user.name}, :#{#user.email}, :#{#user.username}, :#{#user.password}, :#{#user.role.name})
    """)
    int insert(@Param("user") User user) throws DuplicateKeyException;

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @Query("SELECT u_email, u_username FROM users WHERE u_email = :email OR u_username = :username")
    Optional<User> findByEmailOrUsername(String email, String username);

    Optional<User> findByUsername(String username);

}
