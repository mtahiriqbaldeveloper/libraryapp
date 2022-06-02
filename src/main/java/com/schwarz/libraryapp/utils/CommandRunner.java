package com.schwarz.libraryapp.utils;

import com.schwarz.libraryapp.entity.Category;
import com.schwarz.libraryapp.entity.ECategories;
import com.schwarz.libraryapp.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandRunner implements CommandLineRunner {



    private final CategoryRepository categoryRepository;

    public CommandRunner(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }



    //insert some default categories
    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.findAll().isEmpty()) {
            ArrayList<Category> categoryArrayList = new ArrayList<>(List.of(new Category(ECategories.FICTION.name(), "fiction Books"),
                    new Category(ECategories.NOVEL.name(), " Story novels"),
                    new Category(ECategories.SCIENCE.name(), " Science stories"),
                    new Category(ECategories.ROMANCE.name(), " love stories")
            ));
            categoryRepository.saveAll(categoryArrayList);
            System.out.println("added to db");
        }
    }
}
