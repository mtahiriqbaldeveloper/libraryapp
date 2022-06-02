package com.schwarz.libraryapp.service;

import com.schwarz.libraryapp.entity.Category;
import com.schwarz.libraryapp.repository.CategoryRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@MockitoSettings(strictness = Strictness.LENIENT)
class CategoryServiceTest {

    @InjectMocks
    CategoryService categoryService;


    @Mock
    CategoryRepository categoryRepository;



    @Test
    void getAllCategories() {

        List<Category> list = new ArrayList<>();

        when(categoryRepository.findAll()).thenReturn(list);

        List<Category> categoryList = categoryService.getAllCategories();

        assertEquals(0,categoryList.size());


    }

    @Test
    void getCategory() {

        Category category=categoryService.getCategory(Long.valueOf(1));

        assertEquals(category,new Category("NOVEL"," Story novels"));
    }

    @Test
    void findCategory() {
    }

    @Test
    void saveCategory() {
    }

    @Test
    void updateCategory() {
    }

    @Test
    void deleteCategory() {
    }
}