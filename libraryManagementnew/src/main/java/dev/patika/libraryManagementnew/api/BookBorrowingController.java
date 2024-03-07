package dev.patika.libraryManagementnew.api;

import dev.patika.libraryManagementnew.business.abstracts.IBookBorrowingService;
import dev.patika.libraryManagementnew.business.abstracts.IBookService;
import dev.patika.libraryManagementnew.core.config.modelMapper.IModelMapperService;
import dev.patika.libraryManagementnew.core.result.Result;
import dev.patika.libraryManagementnew.core.result.ResultData;
import dev.patika.libraryManagementnew.core.utilies.ResultHelper;
import dev.patika.libraryManagementnew.dto.request.bookBorrowing.BookBorrowingSaveRequest;
import dev.patika.libraryManagementnew.dto.request.bookBorrowing.BookBorrowingUpdateRequest;
import dev.patika.libraryManagementnew.dto.response.CursorResponse;
import dev.patika.libraryManagementnew.dto.response.bookborrowing.BookBorrowingResponse;
import dev.patika.libraryManagementnew.entities.Book;
import dev.patika.libraryManagementnew.entities.BookBorrowing;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/book-borrowings")
public class BookBorrowingController {
    private final IBookBorrowingService bookBorrowingService;
    private final IModelMapperService modelmapper;

    private IBookService bookService;


    public BookBorrowingController(IBookBorrowingService bookBorrowingService, IModelMapperService modelmapper, IBookService bookService) {
        this.bookBorrowingService = bookBorrowingService;
        this.modelmapper = modelmapper;
        this.bookService = bookService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BookBorrowingResponse> save(@Valid @RequestBody BookBorrowingSaveRequest bookBorrowingSaveRequest) {
        BookBorrowing saveBookBorrowing = this.modelmapper.forRequest().map(bookBorrowingSaveRequest, BookBorrowing.class);

        Book book = this.bookService.get(bookBorrowingSaveRequest.getBookId());
        saveBookBorrowing.setBook(book);

        this.bookBorrowingService.save(saveBookBorrowing);
        return ResultHelper.created(this.modelmapper.forResponse().map(saveBookBorrowing, BookBorrowingResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookBorrowingResponse> get(@PathVariable("id") int id) {
        BookBorrowing bookBorrowing = this.bookBorrowingService.get(id);
        BookBorrowingResponse bookBorrowingResponse = this.modelmapper.forResponse().map(bookBorrowing, BookBorrowingResponse.class);
        return ResultHelper.success(bookBorrowingResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<BookBorrowingResponse>> cursor(
        @RequestParam(name = "page", required = false, defaultValue = "0") int page,
        @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {
        Page<BookBorrowing> bookBorrowingPage = this.bookBorrowingService.cursor(page, pageSize);
        Page<BookBorrowingResponse> bookBorrowingResponsePage = bookBorrowingPage
                .map(bookBorrowing -> this.modelmapper.forResponse().map(bookBorrowing, BookBorrowingResponse.class));
        return ResultHelper.cursor(bookBorrowingResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookBorrowingResponse> update(@Valid @RequestBody BookBorrowingUpdateRequest bookBorrowingUpdateRequest) {
        this.bookBorrowingService.get(bookBorrowingUpdateRequest.getId());
        BookBorrowing updateBookBorrowing = this.modelmapper.forRequest().map(bookBorrowingUpdateRequest, BookBorrowing.class);
        this.bookBorrowingService.update(updateBookBorrowing);
        return ResultHelper.success(this.modelmapper.forResponse().map(updateBookBorrowing, BookBorrowingResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.bookBorrowingService.delete(id);
        return ResultHelper.ok();
    }

    @GetMapping("/getActiveBorrowings")
    @ResponseStatus(HttpStatus.OK)
    public List<BookBorrowing> getActiveBorrowings() {
        return this.bookBorrowingService.getAll();


    }

}
