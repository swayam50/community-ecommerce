package com.ecommerce.rest.model.persistent;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "images")
public class Image {

    @Id
    @Field(name = "imageId")
    private String id;
}
