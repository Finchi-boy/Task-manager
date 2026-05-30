package dev.maciejfrackiewicz.task_manager.category;

import dev.maciejfrackiewicz.task_manager.category.dto.CategoryResponse;
import dev.maciejfrackiewicz.task_manager.category.dto.CreateCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryResponse addCategory(CreateCategoryRequest request)
    {
        return categoryMapper.toResponse(categoryRepository.save(categoryMapper.toEntity(request)));
    }

    public Optional<CategoryResponse> getCategoryById(UUID id)
    {
        return categoryRepository.findById(id).map(categoryMapper::toResponse);
    }

    public CategoryResponse updateCategory(CreateCategoryRequest request)
    {
        return categoryMapper.toResponse(categoryRepository.save(categoryMapper.toEntity(request)));
    }

    public void deleteCategory(UUID id)
    {
        categoryRepository.deleteById(id);
    }





}
