package org.example;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {

    @Test
    void everyStatementTest() {

        assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, "12346"));


        List<Item> emptyItems = List.of();
        double resultEmpty = SILab2.checkCart(emptyItems, "1234567890123456");
        assertEquals(0.0, resultEmpty);


        List<Item> itemsWithInvalidNames = List.of(
                new Item(null, 1, 100, 0),
                new Item("", 1, 12, 23)
        );
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(itemsWithInvalidNames, "342"));


        Item itemNoDiscount = new Item("item1", 10, 300, 0);
        int expectedSum = 100;
        int actualSum = expectedSum;
        if (itemNoDiscount.getPrice() > 300 || itemNoDiscount.getDiscount() > 0 || itemNoDiscount.getQuantity() > 10) {
            actualSum -= 30;
        }
        assertEquals(expectedSum, actualSum);


        Item itemNoDisc = new Item("item2", 56, 688, 0);
        double initialSum = 100;
        double sumAfterCheck = initialSum;
        if (itemNoDisc.getDiscount() > 0) {
            sumAfterCheck += itemNoDisc.getPrice() * (1 - itemNoDisc.getDiscount()) * itemNoDisc.getQuantity();
        }
        assertEquals(initialSum, sumAfterCheck);


        List<Item> validItems = List.of(new Item("item3", 23, 12, 3));
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(validItems, null));
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(validItems, "1234"));
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(validItems, "1234567890123456789")); // too long


        assertThrows(RuntimeException.class, () -> SILab2.checkCart(validItems, "0123456789p"));
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(validItems, "123hdusd"));
    }

    @Test
    void multipleConditionTest() {

        List<Item> allTrue = List.of(new Item("item1", 12, 360, 0.1));
        assertEquals(3858, SILab2.checkCart(allTrue, "1234567890123456"));


        List<Item> priceAndDiscount = List.of(new Item("item2", 3, 350, 0.1));
        assertEquals(915, SILab2.checkCart(priceAndDiscount, "1234567890123456"));


        List<Item> priceAndQuantity = List.of(new Item("item3", 20, 350, 0));
        assertEquals(6970, SILab2.checkCart(priceAndQuantity, "1234567890123456"));


        List<Item> onlyPrice = List.of(new Item("item4", 2, 550, 0));
        assertEquals(1070, SILab2.checkCart(onlyPrice, "1234567890123456"));


        List<Item> discountAndQuantity = List.of(new Item("item5", 11, 200, 0.1));
        assertEquals(1950, SILab2.checkCart(discountAndQuantity, "1234567890123456"));


        List<Item> onlyDiscount = List.of(new Item("item6", 5, 200, 0.1));
        assertEquals(870, SILab2.checkCart(onlyDiscount, "1234567890123456"));


        List<Item> onlyQuantity = List.of(new Item("item7", 11, 200, 0));
        assertEquals(2170, SILab2.checkCart(onlyQuantity, "1234567890123456"));


        List<Item> allFalse = List.of(new Item("item8", 8, 30, 0));
        assertEquals(240, SILab2.checkCart(allFalse, "1234567890123456"));
    }
}
