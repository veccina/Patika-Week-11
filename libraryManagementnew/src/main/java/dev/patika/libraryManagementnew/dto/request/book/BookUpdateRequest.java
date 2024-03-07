package dev.patika.libraryManagementnew.dto.request.book;

import dev.patika.libraryManagementnew.entities.BookBorrowing;
import dev.patika.libraryManagementnew.entities.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookUpdateRequest {
    @Positive(message = "Id değeri pozitif tam sayı olmalıdır")
    private int id;

    @NotNull(message = "Kitap adı boş veya null olamaz")
    private String name;

    @NotNull(message = "Yayın yılı boş veya null olamaz")
    private int publicationYear;

    private int stock;

    private int publisherId;

    private int authorId;

    private List<Category> categoryList;

    private List<BookBorrowing> bookBorrowingList;


}
