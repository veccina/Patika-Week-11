package dev.patika.libraryManagementnew.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "publishers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id", columnDefinition = "serial")
    private int id;

    @NotNull
    @Column(name = "publisher_name")
    private String name;

    @NotNull
    @Column(name = "establishment_year")
    private int establishmentYear;

    @NotNull
    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Book> books;
}
