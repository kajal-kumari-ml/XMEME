package com.crio.starter.service;

import java.util.List;
import com.crio.starter.data.Meme;
import com.crio.starter.repository.MemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MemeServiceImpl implements MemeService {

    @Autowired
    MemeRepository memeRepository;

    @Override
    public Meme postMeme(Meme meme) {
        // Your validation logic can go here (e.g., checking for duplicate memes)
        // Save the meme to the database
        return memeRepository.save(meme);
    }

    @Override
    public List<Meme> getLatestMemes() {
        Sort sort = Sort.by(Sort.Order.desc("id"));
        PageRequest pageRequest = PageRequest.of(0, 100, sort);
        Page<Meme> latestMemes = memeRepository.findAll(pageRequest);
        return latestMemes.getContent();
    }

    @Override
    public Meme getMemeById(String id) {
        // Retrieve a meme by its ID
        Meme meme = memeRepository.findById(id).orElse(null);
        return meme;
    }

    @Override
    public boolean isDatabaseEmpty() {
        return memeRepository.count() == 0;
    }

    @Override
    public boolean isDuplicate(String name,String url,String caption){
        List<Meme> existingMemes = memeRepository.findAll(); // You can add a method to get all existing MEMEs
        return existingMemes.stream()
            .anyMatch(existingMeme -> existingMeme.getCaption().equals(caption) &&
                                        existingMeme.getUrl().equals(url)&& existingMeme.getName().equals(name));
    }


 

}
