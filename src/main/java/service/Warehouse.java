package service;

import entities.Product;
import entities.Category;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Warehouse {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        if (product.name() == null || product.name().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        products.add(product);
    }

    public void modifyProduct(String id, String newName, Category newCategory, int newRating) {
        Product product = getProductById(id);
        products.remove(product);
        products.add(product.modify(newName, newCategory, newRating));
    }

    public List<Product> getAllProducts() {
        return Collections.unmodifiableList(products);
    }

    public Product getProductById(String id) {
        return products.stream()
                .filter(p -> p.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No product found with id " + id));
    }

    public List<Product> getProductsByCategory(Category category) {
        return products.stream()
                .filter(p -> p.category() == category)
                .sorted(Comparator.comparing(Product::name))
                .collect(Collectors.toList());
    }

    public List<Product> getProductsCreatedAfter(LocalDate date) {
        return products.stream()
                .filter(p -> p.createdDate().isAfter(date))
                .collect(Collectors.toList());
    }

    public List<Product> getProductsModifiedAfterCreation() {
        return products.stream()
                .filter(p -> !p.createdDate().equals(p.modifiedDate()))
                .collect(Collectors.toList());
    }
}
