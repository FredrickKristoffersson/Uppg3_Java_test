package entities;

import java.time.LocalDate;

public record Product(
        String id,
        String name,
        Category category,
        int rating,
        LocalDate createdDate,
        LocalDate modifiedDate
) {
    public Product {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (rating < 0 || rating > 10) {
            throw new IllegalArgumentException("Rating must be between 0 and 10");
        }
    }

    public Product modify(String newName, Category newCategory, int newRating) {
        return new Product(id, newName, newCategory, newRating, createdDate, LocalDate.now());
    }
}
