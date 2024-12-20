package com.ecommerce.rest.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.ecommerce.rest.model.persistent.Image;

@Repository
public interface ImageRepository extends MongoRepository<Image, String> {

    @Query("{ 'userId': ?0 }")
    Optional<Image> findByUserId(String userId);

    @Query("{ 'postId': ?0 }")
    Optional<Image> findByPostId(String postId);

}
