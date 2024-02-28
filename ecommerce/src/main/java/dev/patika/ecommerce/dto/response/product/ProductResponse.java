package dev.patika.ecommerce.dto.response.product;

import dev.patika.ecommerce.entities.Category;
import dev.patika.ecommerce.entities.Supplier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private int id;

    private String name;

    private double prc;

    private int stock;

    private int supplierId;

    private int categoryId;


}
