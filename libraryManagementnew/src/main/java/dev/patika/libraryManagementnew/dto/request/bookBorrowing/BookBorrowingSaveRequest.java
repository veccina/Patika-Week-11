package dev.patika.libraryManagementnew.dto.request.bookBorrowing;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowingSaveRequest {

    @NotNull(message = "Kiralayan kişi adı boş veya null olamaz")
    private String borrowerName;

    @NotNull(message = "Kiralama tarihi boş veya null olamaz")
    private LocalDate borrowingDate;

    @NotNull(message = "Teslim tarihi boş veya null olamaz")
    private LocalDate returnDate;

    private int bookId;

}
