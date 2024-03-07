package dev.patika.libraryManagementnew.dao;

import dev.patika.libraryManagementnew.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {
}
