package dev.maciejfrackiewicz.task_manager.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category addCategory(Category category)
    {
        return categoryRepository.save(category);
    }

    public Optional<Category> getCategoryById(UUID id)
    {
        return categoryRepository.findById(id);
    }

    public Category updateCategory(Category category)
    {
        return categoryRepository.save(category);
    }

    public void deleteCategory(UUID id)
    {
        categoryRepository.deleteById(id);
    }

}
