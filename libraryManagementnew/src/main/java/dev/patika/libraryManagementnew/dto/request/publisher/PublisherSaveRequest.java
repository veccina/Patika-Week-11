package dev.patika.libraryManagementnew.dto.request.publisher;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherSaveRequest {
    @NotNull(message = "Yayınevi adı boş veya null olamaz")
    private String name;

    @NotNull(message = "Adres alanı boş veya null olamaz")
    private String address;

    @NotNull(message = "Kuruluş yılı alanı boş veya null olamaz")
    private int establishmentYear;
}
