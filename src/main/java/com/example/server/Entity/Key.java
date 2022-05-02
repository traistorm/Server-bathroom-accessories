package com.example.server.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "key")
public class Key {
    @Id
    private Integer id;
    private String value;
}
