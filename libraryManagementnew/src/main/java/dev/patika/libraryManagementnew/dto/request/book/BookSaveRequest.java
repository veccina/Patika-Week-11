package dev.patika.libraryManagementnew.dto.request.book;

import dev.patika.libraryManagementnew.entities.Category;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookSaveRequest {
    @NotNull(message = "Kitap adı boş veya null olamaz")
    private String name;

    @NotNull(message = "Yayın yılı boş veya null olamaz")
    private int publicationYear;

    private int stock;

    private int pushlisherId;

    private int authorId;

    private List<Category> categoryList;

}
