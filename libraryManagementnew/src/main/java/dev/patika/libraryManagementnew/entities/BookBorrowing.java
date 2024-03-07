package dev.patika.libraryManagementnew.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "book_borrowings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrowing_id", columnDefinition = "serial")
    private int id;

    @NotNull
    @Column(name = "borrower_name")
    private String borrowerName;

    @NotNull
    @Column(name = "borrowing_date")
    private LocalDate borrowingDate;

    @NotNull
    @Column(name = "return_date")
    private LocalDate returnDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_borrow_id", referencedColumnName = "book_id")
    private Book book;
}
