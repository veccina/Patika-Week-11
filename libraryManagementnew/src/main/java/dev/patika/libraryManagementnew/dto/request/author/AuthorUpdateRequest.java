package dev.patika.libraryManagementnew.dto.request.author;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorUpdateRequest {
    @Positive(message = "Yazar id değeri pozitif tam sayı olmalıdır")
    private int id;

    @NotNull(message = "Yazar adı boş bırakılamaz")
    private String name;

    @NotNull(message = "Doğum tarihi alanı boş bırakılamaz")
    private String birthDate;

    @NotNull(message = "Ülke alanı boş bırakılamaz")
    private String country;
}
