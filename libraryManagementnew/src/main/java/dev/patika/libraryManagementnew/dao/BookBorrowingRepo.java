package dev.patika.libraryManagementnew.dao;

import dev.patika.libraryManagementnew.entities.BookBorrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookBorrowingRepo extends JpaRepository<BookBorrowing, Integer> {
}
