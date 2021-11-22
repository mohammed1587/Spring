package com.example.spring.controller;


import java.util.List;

import com.example.spring.controller.api.CategoryApi;
import com.example.spring.dto.CategoryDto;
import com.example.spring.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController implements CategoryApi {

  private final CategoryService categoryService;

  @Autowired
  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @Override
  public CategoryDto save(CategoryDto dto) {
    return categoryService.save(dto);
  }

  @Override
  public CategoryDto findById(Integer idCategory) {
    return categoryService.findById(idCategory);
  }

  @Override
  public CategoryDto findByCode(String codeCategory) {
    return categoryService.findByCode(codeCategory);
  }

  @Override
  public List<CategoryDto> findAll() {
    return categoryService.findAll();
  }

  @Override
  public void delete(Integer id) {
    categoryService.delete(id);
  }
}
