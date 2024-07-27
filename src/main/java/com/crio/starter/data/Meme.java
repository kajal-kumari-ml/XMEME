package com.crio.starter.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "meme")
@NoArgsConstructor
public class Meme {

    @Id
    String id;

    
    String name;

    
    String url;

    
    String caption;
}

