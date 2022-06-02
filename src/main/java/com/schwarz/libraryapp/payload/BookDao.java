package com.schwarz.libraryapp.payload;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BookDao {

    private String title;

    private String author;

    private String publisher;

    private int publishYear;

    private String category;


    public BookDao(String title, String author, String publisher, int publishYear, String category) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.category = category;
    }

    @Override
    public String toString() {
        return "BookDao{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publishYear=" + publishYear +
                ", category='" + category + '\'' +
                '}';
    }
}
