package com.schwarz.libraryapp.repository;

import com.schwarz.libraryapp.entity.Book;
import com.schwarz.libraryapp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    @Override
    boolean existsById(Long aLong);
    List<Book> findBookByCategoryId(Long id);
}
