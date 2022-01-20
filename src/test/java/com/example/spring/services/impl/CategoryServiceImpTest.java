package com.example.spring.services.impl;

import com.example.spring.dto.CategoryDto;
import com.example.spring.exception.EntityNotFoundException;
import com.example.spring.exception.ErrorCodes;
import com.example.spring.exception.InvalidEntityException;
import com.example.spring.model.Category;
import com.example.spring.services.CategoryService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImpTest {

    @Autowired
    private CategoryServiceImp categoryService;

    @Test
    public void shouldSaveCategoryWithSuccess(){
        CategoryDto expectedCategoryDto = CategoryDto .builder()
                .code("Test 1").designation("Test Test").idEntreprise(1).build();
       CategoryDto saveCategory = categoryService.save(expectedCategoryDto);

        assertNotNull(saveCategory);
        assertNotNull(saveCategory.getId());
        assertEquals(expectedCategoryDto.getCode(),saveCategory.getCode());
        assertEquals(expectedCategoryDto.getDesignation(),saveCategory.getDesignation());
        assertEquals(expectedCategoryDto.getIdEntreprise(),saveCategory.getIdEntreprise());

    }
    @Test
    public void shouldUpdateCategoryWithSuccess(){
        CategoryDto expectedCategoryDto = CategoryDto .builder()
                .code("Test 1").designation("Test Test").idEntreprise(1).build();
        CategoryDto saveCategory = categoryService.save(expectedCategoryDto);

        CategoryDto updateCategory =saveCategory;
        updateCategory.setCode("Test 2");
        saveCategory = categoryService.save(updateCategory);

        assertNotNull(updateCategory);
        assertNotNull(updateCategory.getId());
        assertEquals(updateCategory.getCode(),saveCategory.getCode());
        assertEquals(updateCategory.getDesignation(),saveCategory.getDesignation());
        assertEquals(updateCategory.getIdEntreprise(),saveCategory.getIdEntreprise());

    }

    @Test
    public void shouldReturnInvalidateEntityException(){
        CategoryDto expectedCategoryDto = CategoryDto .builder().build();

        InvalidEntityException expectedException =assertThrows(InvalidEntityException.class,()->categoryService.save(expectedCategoryDto));
        assertEquals(ErrorCodes.CATEGORY_NOT_VALID,expectedException.getErrorCode());
        assertEquals(1,expectedException.getErrors().size());
        assertEquals("Veuillez renseigner le code de la categorie",expectedException.getErrors().get(0));


    }

    @Test
    public void shouldReturnEntityNotFoundException(){

        EntityNotFoundException expectedException =assertThrows(EntityNotFoundException.class,()->categoryService.findById(0));
        assertEquals(ErrorCodes.CATEGORY_NOT_FOUND,expectedException.getErrorCode());
        assertEquals("Aucune category avec l'ID = 0 n' ete trouve dans la BDD",expectedException.getMessage());
    }

    @Test(expected = EntityNotFoundException.class)
    public void shouldReturnEntityNotFoundException2(){
        categoryService.findById(545);
    }
}
