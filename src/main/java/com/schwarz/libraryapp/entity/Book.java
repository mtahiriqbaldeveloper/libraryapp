package com.schwarz.libraryapp.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy  = GenerationType.AUTO)
    private Long id;

    private String title;

    private String author;

    private String publisher;

    private int publishYear;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Book(String title, String author, String publisher, int publishYear, Category category) {
        this.title=title;
        this.author = author;
        this.publisher=publisher;
        this.publishYear=publishYear;
        this.category=category;
    }
}
