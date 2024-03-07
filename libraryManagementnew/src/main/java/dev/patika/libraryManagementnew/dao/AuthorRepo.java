package dev.patika.libraryManagementnew.dao;

import dev.patika.libraryManagementnew.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Integer>{
}
