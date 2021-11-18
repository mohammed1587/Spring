package com.example.spring.services.impl;

import com.example.spring.dto.ArticleDto;
import com.example.spring.dto.CategoryDto;
import com.example.spring.exception.EntityNotFoundException;
import com.example.spring.exception.ErrorCodes;
import com.example.spring.exception.InvalidEntityException;
import com.example.spring.repository.CategoryRepository;
import com.example.spring.services.CategoryService;
import com.example.spring.validator.CategoryValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImp implements CategoryService {

    private CategoryRepository repository;

    public CategoryServiceImp(CategoryRepository repository) {
        this.repository = repository;
    }


    @Override
    public CategoryDto save(CategoryDto dto) {
        List<String> errors = CategoryValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Category is not valid {}", dto);
            throw new InvalidEntityException("la category n'est pas valid", ErrorCodes.CATEGORY_NOT_VALID, errors);
        }
        return CategoryDto.fromEntity(repository.save(CategoryDto.toEntity(dto)));
    }

    @Override
    public CategoryDto findById(Integer id) {
        if (id == null) {
            log.error("Category id is null");
            return null;
        }
        return repository.findById(id).map(CategoryDto::fromEntity).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune category avec l'ID = " + id + " n' ete trouve dans la BDD",
                        ErrorCodes.CATEGORY_NOT_FOUND)
        );
    }

    @Override
    public CategoryDto findByCode(String code) {
        if (!StringUtils.hasLength(code)) {
            log.error("Category code is null");
            return null;
        }
        return repository.findCategoryByCode(code).map(CategoryDto::fromEntity).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucunee category avec le code = " + code + " n' ete trouve dans la BDD",
                        ErrorCodes.CATEGORY_NOT_FOUND)
        );
    }

    @Override
    public List<CategoryDto> findAll() {
        return repository.findAll().stream().map(CategoryDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Category id is null");
            return;
        }
        repository.deleteById(id);
    }
}
