package com.crio.starter.repository;

import com.crio.starter.data.Meme;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemeRepository extends MongoRepository<Meme, String> {
    Meme findByNameAndUrlAndCaption(String name,String url,String caption);
}
