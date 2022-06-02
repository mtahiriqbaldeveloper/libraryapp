package com.schwarz.libraryapp.service;



import com.schwarz.libraryapp.entity.Category;
import com.schwarz.libraryapp.repository.BookRepository;
import com.schwarz.libraryapp.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;

    public CategoryService(CategoryRepository categoryRepository, BookRepository bookRepository) {
        this.categoryRepository = categoryRepository;

        this.bookRepository = bookRepository;
    }

    public List<Category> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();

        for(int i=0;i<categories.size();i++){
            categories.get(i).setAssignedBook(bookRepository.findBookByCategoryId(categories.get(i).getId()).size());
        }
        return categories;
    }

    public Category getCategory(Long id){
        Category category = categoryRepository.findById(id).get();
        category.setAssignedBook(bookRepository.findBookByCategoryId(id).size());
        return category;

    }

    public Category findCategory(String categoryName){

        Optional<Category> category =categoryRepository.findByName(categoryName);

        return category.orElse(null);
    }

    public Category saveCategory(Category category){
        category.setName(category.getName().toUpperCase(Locale.ROOT));
        return categoryRepository.save(category);
    }

    public boolean existById(Long id){
        return categoryRepository.existsById(id);
    }

    public String updateCategory(Long id, Category category) {

        Optional<Category> result = categoryRepository.findById(id);
        result.ifPresent(cat -> {
            cat.setName(category.getName());
            cat.setDescription(category.getDescription());
            categoryRepository.save(cat);
        });
        return "updated Successfully";
    }

    public String deleteCategory(Long id) {

        categoryRepository.deleteById(id);
        return "deleted successfully";
    }
}
