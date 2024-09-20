package service;

import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.Warehouse;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseTest {
    private Warehouse warehouse;

    @BeforeEach
    void setUp() {
        warehouse = new Warehouse();
    }

    @Test
    void addProductShouldFailIfNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () ->
                warehouse.addProduct(new Product("1", "", Category.ELECTRONICS, 5, LocalDate.now(), LocalDate.now()))
        );
    }

    @Test
    void modifyProductShouldUpdateFieldsCorrectly() {
        Product product = new Product("1", "Laptop", Category.ELECTRONICS, 7, LocalDate.now(), LocalDate.now());
        warehouse.addProduct(product);

        warehouse.modifyProduct("1", "Gaming Laptop", Category.ELECTRONICS, 9);
        Product modifiedProduct = warehouse.getProductById("1");

        assertEquals("Gaming Laptop", modifiedProduct.name());
        assertEquals(9, modifiedProduct.rating());
    }

    // Lägg till fler tester för de andra metoderna
}
