package com.offcn.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    @Column(name = "pid")
    private Long id;

    @Column(name = "name",nullable = true,length = 200)
    private String name;

    @Column(name = "age",nullable = true,length = 4)
    private Integer age;

}
