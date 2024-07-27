package com.crio.starter.service;

import java.util.List;
import com.crio.starter.data.Meme;

public interface MemeService {
    Meme postMeme(Meme meme);
    List<Meme> getLatestMemes();
    Meme getMemeById(String id);
    boolean isDatabaseEmpty();
    boolean isDuplicate(String name,String url,String caption);

}
