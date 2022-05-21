package com.example.server.Service;

import com.example.server.Entity.News;
import com.example.server.Repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    @Autowired
    NewsRepository newsRepository;
    public List<News> findAll()
    {
        return newsRepository.findAll();
    }
    public News findNewsById(Integer id)
    {
        return newsRepository.findNewsById(id);
    }
}
