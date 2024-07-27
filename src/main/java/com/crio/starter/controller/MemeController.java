package com.crio.starter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;
import com.crio.starter.data.Meme;
import com.crio.starter.exchange.MemeResponseDto;
import com.crio.starter.service.MemeService;

@RestController
@RequestMapping("/memes/")
public class MemeController {
     
     @Autowired
     MemeService memeService;

    @PostMapping
    public ResponseEntity<?> postMeme(@Valid @RequestBody Meme meme) {
        if(meme==null){
            return new ResponseEntity<>("Please Provide Meme Information.",HttpStatus.BAD_REQUEST);
        }
       
        if (meme == null || meme.getName() == null || meme.getUrl() == null || meme.getCaption() == null) {
            return ResponseEntity.badRequest().body("Invalid or incomplete meme data");
        }
    
        try {
            boolean existingMeme = memeService.isDuplicate(meme.getName(), meme.getUrl(), meme.getCaption());
            if (existingMeme) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate meme detected");
            }
    
            Meme postedMeme = memeService.postMeme(meme);
            MemeResponseDto memeResponseDto = new MemeResponseDto(postedMeme.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(memeResponseDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @GetMapping
    public ResponseEntity<List<Meme>> getLatestMemes() {
        if (memeService.isDatabaseEmpty()) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
        } else {
            List<Meme> latestMemes = memeService.getLatestMemes();
            if(latestMemes==null){
                return ResponseEntity.notFound().build();
            }
            return new ResponseEntity<>(latestMemes, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meme> getMemeById(@PathVariable String id) {
        if(id==null){
            return ResponseEntity.badRequest().build();
        }
        Meme meme = memeService.getMemeById(id);
        if (meme != null) {
            return ResponseEntity.ok(meme);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
   
}

