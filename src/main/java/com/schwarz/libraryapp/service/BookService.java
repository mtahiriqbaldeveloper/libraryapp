package com.schwarz.libraryapp.service;


import com.schwarz.libraryapp.payload.BookDao;
import com.schwarz.libraryapp.entity.Book;
import com.schwarz.libraryapp.entity.Category;
import com.schwarz.libraryapp.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryService categoryService;


    public BookService(BookRepository bookRepository, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.categoryService = categoryService;
    }

    public List<Book> getAllBooks(){

        return bookRepository.findAll();
    }
    public Book saveBook(BookDao bookDao){

        Category category=categoryService.findCategory(bookDao.getCategory());
        Book book = new Book(bookDao.getTitle(),bookDao.getAuthor(),bookDao.getPublisher(),bookDao.getPublishYear(),category);
        return bookRepository.save(book);
    }
    public boolean isExistById(Long id){
        return bookRepository.existsById(id);
    }
    public String updateBook(BookDao bookDao,Long id){

        Optional<Book> result = bookRepository.findById(id);

        Category category=categoryService.findCategory(bookDao.getCategory());

        result.ifPresent(book -> {
            book.setTitle(bookDao.getTitle());
            book.setAuthor(bookDao.getAuthor());
            book.setPublisher(bookDao.getPublisher());
            book.setPublishYear(bookDao.getPublishYear());
            book.setCategory(category);
            bookRepository.save(book);
        });

        return "updated Successfully";
    }


    public String deleteBook(Long id) {
        bookRepository.deleteById(id);
       return "deleted successfully";
    }

    public Book getBook(Long id) {

        return bookRepository.findById(id).get();
    }
}
