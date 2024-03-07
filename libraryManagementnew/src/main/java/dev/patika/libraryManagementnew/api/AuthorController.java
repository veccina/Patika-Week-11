package dev.patika.libraryManagementnew.api;

import dev.patika.libraryManagementnew.business.abstracts.IAuthorService;
import dev.patika.libraryManagementnew.core.config.modelMapper.IModelMapperService;
import dev.patika.libraryManagementnew.core.result.Result;
import dev.patika.libraryManagementnew.core.result.ResultData;
import dev.patika.libraryManagementnew.core.utilies.ResultHelper;
import dev.patika.libraryManagementnew.dto.request.author.AuthorSaveRequest;
import dev.patika.libraryManagementnew.dto.request.author.AuthorUpdateRequest;
import dev.patika.libraryManagementnew.dto.response.CursorResponse;
import dev.patika.libraryManagementnew.dto.response.author.AuthorResponse;
import dev.patika.libraryManagementnew.entities.Author;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/authors")
public class AuthorController {
    private final IAuthorService authorService;
    private final IModelMapperService modelMapperService;

    public AuthorController(IAuthorService authorService, IModelMapperService modelMapperService) {
        this.authorService = authorService;
        this.modelMapperService = modelMapperService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AuthorResponse> save(@Valid @RequestBody AuthorSaveRequest authorSaveRequest) {
        Author saveCategory = this.modelMapperService.forRequest().map(authorSaveRequest, Author.class);
        this.authorService.save(saveCategory);
        return ResultHelper.created(this.modelMapperService.forResponse().map(saveCategory, AuthorResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AuthorResponse> get(@PathVariable("id") int id) {
        Author author = this.authorService.get(id);
        AuthorResponse authorResponse = this.modelMapperService.forResponse().map(author, AuthorResponse.class);
        return ResultHelper.success(authorResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AuthorResponse>> cursor(
        @RequestParam(name = "page", required = false, defaultValue = "0") int page,
        @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize){

        Page<Author> authorPage = this.authorService.cursor(page, pageSize);
        Page<AuthorResponse> authorResponsePage = authorPage
                .map(author -> this.modelMapperService.forResponse().map(author, AuthorResponse.class));

        return ResultHelper.cursor(authorResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AuthorResponse> update(@Valid @RequestBody AuthorUpdateRequest authorUpdateRequest) {
        this.authorService.get(authorUpdateRequest.getId());
        Author updateAuthor = this.modelMapperService.forRequest().map(authorUpdateRequest, Author.class);
        this.authorService.update(updateAuthor);
        return ResultHelper.success(this.modelMapperService.forResponse().map(updateAuthor, AuthorResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.authorService.delete(id);
        return ResultHelper.ok();
    }
}
