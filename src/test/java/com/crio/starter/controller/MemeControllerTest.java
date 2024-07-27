package com.crio.starter.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import com.crio.starter.data.Meme;
import com.crio.starter.service.MemeService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class MemeControllerTest {

    @InjectMocks
    private MemeController memeController;

    @Mock
    private MemeService memeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPostMeme() {
        Meme sampleMeme = new Meme();
        sampleMeme.setName("Test User");
        sampleMeme.setCaption("Test Caption");
        sampleMeme.setUrl("https://example.com/test.jpg");

        Mockito.when(memeService.postMeme(any(Meme.class))).thenReturn(sampleMeme);

        ResponseEntity<?> response = memeController.postMeme(sampleMeme);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testGetLatestMemes() {
        Meme sampleMeme = new Meme();
        sampleMeme.setName("Test User");
        sampleMeme.setCaption("Test Caption");
        sampleMeme.setUrl("https://example.com/test.jpg");

        Mockito.when(memeService.getLatestMemes()).thenReturn(Collections.singletonList(sampleMeme));

        ResponseEntity<List<Meme>> response = memeController.getLatestMemes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(sampleMeme.getId(), response.getBody().get(0).getId());
    }

    @Test
    public void testGetMemeById() {
        // Meme sampleMeme = new Meme();
        // sampleMeme.setId("1");
        // sampleMeme.setName("Test User");
        // sampleMeme.setCaption("Test Caption");
        // sampleMeme.setUrl("https://example.com/test.jpg");

        // Mockito.when(memeService.getMemeById("1")).thenReturn(sampleMeme);

        ResponseEntity<?> response = memeController.getMemeById("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
       // assertEquals(sampleMeme.getId(), response.getBody().getId());
    }
}

