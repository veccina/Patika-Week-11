package dev.patika.libraryManagementnew.business.abstracts;

import dev.patika.libraryManagementnew.entities.BookBorrowing;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IBookBorrowingService {

    BookBorrowing save(BookBorrowing bookBorrowing);

    BookBorrowing get(int id);

    Page<BookBorrowing> cursor(int page, int pageSize);

    BookBorrowing update(BookBorrowing bookBorrowing);

    List<BookBorrowing> getAll();

    boolean delete(int id);
}
