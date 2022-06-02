package com.schwarz.libraryapp.controller;


import com.schwarz.libraryapp.payload.ApiResponse;
import com.schwarz.libraryapp.payload.BookDao;
import com.schwarz.libraryapp.entity.Book;
import com.schwarz.libraryapp.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(path = "/add-book")
    public ResponseEntity<?> addBook(@RequestBody BookDao book){

         return ResponseEntity.ok(bookService.saveBook(book));
    }

    @GetMapping(path = "/all-books")
    public ResponseEntity<?> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping(path = "get-book/{id}")
    public ResponseEntity<?> getBook(@PathVariable Long id){
        if(!bookService.isExistById(id)){

            return new ResponseEntity(new ApiResponse(false,"Book doesn't by this id"), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(bookService.getBook(id));
    }


    @PutMapping( path = "/update-book/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody BookDao bookDao){

        if(!bookService.isExistById(id)){

            return new ResponseEntity(new ApiResponse(false,"Book doesn't by this id"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(bookService.updateBook(bookDao,id));
    }

    @DeleteMapping(path = "/delete-book/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id){

        if(!bookService.isExistById(id)){

            return new ResponseEntity(new ApiResponse(false,"Book doesn't by this id"), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(bookService.deleteBook(id));
    }

}

