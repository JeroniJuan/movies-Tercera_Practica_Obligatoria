package com.esliceu.movies.services;

import com.esliceu.movies.models.Keyword;
import com.esliceu.movies.repos.KeywordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeywordService {
    @Autowired
    private KeywordRepo keywordRepo;

    public List<Keyword> findAll() {
        return keywordRepo.findAll();
    }

    public Keyword findById(int id) {
        return keywordRepo.findById(id);
    }

    public Keyword save(Keyword keyword) {
        return keywordRepo.save(keyword);
    }

    public void deleteById(int id) {
        keywordRepo.deleteById(id);
    }

    public Keyword findByName(String keywordName) {
        return keywordRepo.findByKeywordName(keywordName);
    }
}
