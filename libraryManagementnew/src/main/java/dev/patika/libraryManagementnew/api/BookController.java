package dev.patika.libraryManagementnew.api;

import dev.patika.libraryManagementnew.business.abstracts.*;
import dev.patika.libraryManagementnew.core.config.modelMapper.IModelMapperService;
import dev.patika.libraryManagementnew.core.result.ResultData;
import dev.patika.libraryManagementnew.core.utilies.ResultHelper;
import dev.patika.libraryManagementnew.dto.request.book.BookSaveRequest;
import dev.patika.libraryManagementnew.dto.response.CursorResponse;
import dev.patika.libraryManagementnew.dto.response.book.BookResponse;
import dev.patika.libraryManagementnew.dto.response.category.CategoryResponse;
import dev.patika.libraryManagementnew.entities.Author;
import dev.patika.libraryManagementnew.entities.Book;
import dev.patika.libraryManagementnew.entities.Publisher;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/books")
public class BookController {

    private final IBookService bookService;
    private final IModelMapperService modelMapper;

    private final ICategoryService categoryService;

    private final IAuthorService authorService;

    private final IBookBorrowingService bookBorrowingService;
    private final IPublisherService publisherService;

    public BookController(IBookService bookService, IModelMapperService modelMapper, ICategoryService categoryService, IAuthorService authorService, IBookBorrowingService bookBorrowingService, IPublisherService publisherService) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookBorrowingService = bookBorrowingService;
        this.publisherService = publisherService;
    }

    @PostMapping("/borrow")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BookResponse> save(@Valid @RequestBody BookSaveRequest bookSaveRequest) {
        Book saveBook = this.modelMapper.forRequest().map(bookSaveRequest, Book.class);

        Publisher publisher = this.publisherService.get(bookSaveRequest.getPushlisherId());
        saveBook.setPublisher(publisher);

        Author author = this.authorService.get(bookSaveRequest.getAuthorId());
        saveBook.setAuthor(author);

        this.bookService.save(saveBook);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveBook, BookResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookResponse> get(@PathVariable("id") int id) {
        Book book = this.bookService.get(id);
        BookResponse bookResponse = this.modelMapper.forResponse().map(book, BookResponse.class);
        return ResultHelper.success(bookResponse);
    }

    @GetMapping("/category/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CategoryResponse> getCategory(@PathVariable("id") int id) {
        Book book = this.bookService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(book.getCategories(), CategoryResponse.class));
    }

}
