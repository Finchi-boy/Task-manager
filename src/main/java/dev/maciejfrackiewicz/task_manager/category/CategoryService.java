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

    public CategoryResponse addCategory(CreateCategoryRequest request)
    {
        return toResponse(categoryRepository.save(toEntity(request)));
    }

    public Optional<CategoryResponse> getCategoryById(UUID id)
    {
        return categoryRepository.findById(id).map(this::toResponse);
    }

    public CategoryResponse updateCategory(CreateCategoryRequest request)
    {
        return toResponse(categoryRepository.save(toEntity(request)));
    }

    public void deleteCategory(UUID id)
    {
        categoryRepository.deleteById(id);
    }

    private Category toEntity(CreateCategoryRequest request)
    {
        return Category.builder()
                .name(request.name())
                .color(request.color())
                .build();
    }

    private CategoryResponse toResponse(Category category)
    {
        return new CategoryResponse(category.getId(), category.getName(), category.getColor());
    }



}
