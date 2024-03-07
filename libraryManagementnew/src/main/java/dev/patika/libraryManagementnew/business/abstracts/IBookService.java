package dev.patika.libraryManagementnew.business.abstracts;

import dev.patika.libraryManagementnew.entities.Book;
import org.springframework.data.domain.Page;

public interface IBookService {

    Book save(Book book);

    Book get(int id);

    Book update(Book book);

    boolean delete(int id);


    Page<Book> cursor(int page, int pageSize);
}
