package com.example.server.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "news")
public class News {
    @Id
    private Integer id;
    private String title;
    private String content;
    private LocalDate posttime;
    private String poster;
    private String imagelink;
}
