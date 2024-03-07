package dev.patika.libraryManagementnew.dao;

import dev.patika.libraryManagementnew.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
