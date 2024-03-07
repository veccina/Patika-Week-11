package dev.patika.libraryManagementnew.dto.request.bookBorrowing;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowingUpdateRequest {

    @Positive(message = "Id değeri pozitif tam sayı olmalıdır")
    private int id;

    @NotNull(message = "Kirayalan kişi adı boş veya null olamaz")
    private int borrowerName;

    @NotNull(message = "Kiralanma tarihi boş veya null olamaz")
    private int borrowingDate;

    @NotNull(message = "Teslim tarihi boş veya null olamaz")
    private int returnDate;

}



