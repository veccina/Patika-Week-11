package dev.patika.libraryManagementnew.api;

import dev.patika.libraryManagementnew.business.abstracts.ICategoryService;
import dev.patika.libraryManagementnew.core.config.modelMapper.IModelMapperService;
import dev.patika.libraryManagementnew.core.result.Result;
import dev.patika.libraryManagementnew.core.result.ResultData;
import dev.patika.libraryManagementnew.core.utilies.ResultHelper;
import dev.patika.libraryManagementnew.dto.request.category.CategorySaveRequest;
import dev.patika.libraryManagementnew.dto.request.category.CategoryUpdateRequest;
import dev.patika.libraryManagementnew.dto.response.CursorResponse;
import dev.patika.libraryManagementnew.dto.response.category.CategoryResponse;
import dev.patika.libraryManagementnew.entities.Category;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {

    private final ICategoryService categoryService;

    private final IModelMapperService modelmapper;

    public CategoryController(ICategoryService categoryService, IModelMapperService modelmapper) {
        this.categoryService = categoryService;
        this.modelmapper = modelmapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CategoryResponse> save(@Valid @RequestBody CategorySaveRequest categorySaveRequest) {
        Category saveCategory = this.modelmapper.forRequest().map(categorySaveRequest, Category.class);
        this.categoryService.save(saveCategory);
        return ResultHelper.created(this.modelmapper.forResponse().map(saveCategory, CategoryResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CategoryResponse> get(@PathVariable("id") int id) {
        Category category = this.categoryService.get(id);
        CategoryResponse categoryResponse = this.modelmapper.forResponse().map(category, CategoryResponse.class);
        return ResultHelper.success(categoryResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<CategoryResponse>> cursor(
        @RequestParam(name = "page", required = false, defaultValue = "0") int page,
        @RequestParam(name = "size", required = false, defaultValue = "10") int size
    ) {
        Page<Category> categoryPage = this.categoryService.cursor(page, size);
        Page<CategoryResponse> categoryResponsePage = categoryPage.map(category -> this.modelmapper.forResponse().map(category, CategoryResponse.class));
        return ResultHelper.cursor(categoryResponsePage);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CategoryResponse> update(@Valid @RequestBody CategoryUpdateRequest categoryUpdateRequest) {
        Category category = this.modelmapper.forRequest().map(categoryUpdateRequest, Category.class);
        Category updatedCategory = this.categoryService.update(category);
        return ResultHelper.success(this.modelmapper.forResponse().map(updatedCategory, CategoryResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.categoryService.delete(id);
        return ResultHelper.ok();
    }

}
