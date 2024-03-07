package dev.patika.libraryManagementnew.dto.request.publisher;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherUpdateRequest {
    @Positive(message = "Id değeri pozitif tam sayı olmalıdır")
    private int id;

    @NotNull(message = "Yayınevi ismi boş veya null olamaz")
    private String name;

    @NotNull(message = "Kuruluş yılı boş veya null olamaz")
    private int establishmentYear;

    @NotNull(message = "Adres boş veya null olamaz")
    private String address;
}
