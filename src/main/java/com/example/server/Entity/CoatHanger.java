package com.example.server.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "coathanger")
public class CoatHanger {
    @Id
    private Integer id;
    private String name;
}
