package com.example.server.Service;

import com.example.server.Entity.CoatHanger;
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
    public List<News> finAllInPage(Integer page, Integer itemsPerPage)
    {
        List<News> newsList = newsRepository.findAll();
        if ((page - 1) * itemsPerPage + itemsPerPage > newsList.size())
        {
            return newsList.subList((page - 1) * itemsPerPage, newsList.size());
        }
        return newsList.subList((page - 1) * itemsPerPage, (page - 1) * itemsPerPage + itemsPerPage);
    }
}
