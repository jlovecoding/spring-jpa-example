package com.example.jpaex.composedid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "books")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@IdClass(BookId.class)
public class Book {

    @Id
    private String author;

    @Id
    private String title;

    private String publisher;

}
