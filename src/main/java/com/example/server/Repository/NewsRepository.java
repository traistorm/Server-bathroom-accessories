package com.example.server.Repository;

import com.example.server.Entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
    News findNewsById(Integer id);
}
