package dev.patika.libraryManagementnew.business.concretes;

import dev.patika.libraryManagementnew.business.abstracts.ICategoryService;
import dev.patika.libraryManagementnew.core.exception.NotFoundException;
import dev.patika.libraryManagementnew.core.utilies.Msg;
import dev.patika.libraryManagementnew.dao.CategoryRepo;
import dev.patika.libraryManagementnew.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryManager implements ICategoryService {

    private final CategoryRepo categoryRepo;

    public CategoryManager(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Category save(Category category) {
        return this.categoryRepo.save(category);
    }

    @Override
    public Category get(int id) {
        return this.categoryRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Category> cursor(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.categoryRepo.findAll(pageable);
    }

    @Override
    public Category update(Category category) {
        this.get(category.getId());
        return this.categoryRepo.save(category);
    }

    @Override
    public boolean delete(int id) {
        Category category = this.get(id);
        this.categoryRepo.delete(category);
        return true;
    }

    @Override
    public List<Category> getAllByIds(List<Integer> ids) {
        return this.categoryRepo.findAllById(ids);
    }
}
