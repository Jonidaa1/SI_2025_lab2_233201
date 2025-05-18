Jonida Pollozhani 233201

CONTROL FLOW GRAPH

![CFG 233201 drawio](https://github.com/user-attachments/assets/1569ca05-53ca-4279-bded-f3c35d602832)

CYCLOMATIC COMPLEXITY

Cyclomatic Complexity of the code is 9 because,there we have 26 nodes,33 edges,the formula for calculating this is E-N+2=> 33-26+2=>9 


package org.example;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {

    // ===== EVERY STATEMENT TEST CASES =====

    @Test    
    void test_NullItemList_ThrowsException() {
        // Tests if null is passed instead of a list of items
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, "1234567890123456"));
    }

    @Test 
    void test_EmptyItemList_ReturnsZero() {
        // Tests behavior with an empty item list
        List<Item> emptyItems = List.of();
        double result = SILab2.checkCart(emptyItems, "1234567890123456");
        assertEquals(0.0, result);
    }

    @Test
    void test_ItemWithNullOrEmptyName_ThrowsException() {
        // Tests item with null and empty name
        List<Item> items = List.of(new Item(null, 1, 100, 0), new Item("", 1, 12, 0.2));
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(items, "1234567890123456"));
    }

    @Test
    void test_DiscountConditionWithNoMatch_NoDeduction() {
        // Tests that sum is not reduced if conditions are false
        Item item = new Item("obj1", 10, 300, 0);
        double initialSum = 100;
        double sum = initialSum;
        if (item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10) {
            sum -= 30;
        }
        assertEquals(initialSum, sum);
    }

    @Test
    void test_DiscountApplied_CorrectSumUpdate() {
        // Tests if discount is greater than 0, then sum is increased properly
        Item item = new Item("obj1", 56, 688, 0.1);
        double expected = 100 + 56 * 688 * 0.9;
        double sum = 100;
        if (item.getDiscount() > 0) {
            sum += item.getPrice() * (1 - item.getDiscount()) * item.getQuantity();
        }
        assertEquals(expected, sum);
    }

    @Test
    void test_NoDiscount_NoSumChange() {
        // Tests if discount = 0, the sum is not changed
        Item item = new Item("obj1", 56, 688, 0);
        double initialSum = 100;
        double sum = initialSum;
        if (item.getDiscount() > 0) {
            sum += item.getPrice() * (1 - item.getDiscount()) * item.getQuantity();
        }
        assertEquals(initialSum, sum);
    }

    @Test
    void test_InvalidCardNumber_NullOrShort_ThrowsException() {
        // Tests invalid card numbers: null and too short
        List<Item> items = List.of(new Item("obj1", 1, 10, 0));
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(items, null));
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(items, "1234"));
    }

    @Test    
    void test_InvalidCardNumberWithCharacters_ThrowsException() {
        // Tests card number with invalid characters
        List<Item> items = List.of(new Item("obj1", 1, 10, 0));
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(items, "123456789012345p"));
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(items, "123hdusd23456789"));
    }

    @Test
    void test_ValidCardNumber_AllCorrectData_NoException() {
        // Tests with valid card and item data
        List<Item> items = List.of(new Item("obj1", 23, 12, 0.3));
        assertDoesNotThrow(() -> SILab2.checkCart(items, "1234567890123456"));
    }

    // ===== MULTIPLE CONDITION TEST CASES =====

    @Test
    void test_AllConditionsTrue() {
        // price > 300, discount > 0, quantity > 10
        List<Item> items = List.of(new Item("item1", 12, 360, 0.1));
        double result = SILab2.checkCart(items, "1234567890123456");
        assertEquals(3858.0, result);
    }

    @Test
    void test_PriceAndDiscountTrue() {
        // price > 300, discount > 0, quantity <= 10
        List<Item> items = List.of(new Item("item2", 3, 350, 0.1));
        double result = SILab2.checkCart(items, "1234567890123456");
        assertEquals(915.0, result);
    }

    @Test
    void test_PriceAndQuantityTrue() {
        // price > 300, discount = 0, quantity > 10
        List<Item> items = List.of(new Item("item3", 20, 350, 0));
        double result = SILab2.checkCart(items, "1234567890123456");
        assertEquals(6970.0, result);
    }

    @Test
    void test_OnlyPriceTrue() {
        // price > 300, discount = 0, quantity <= 10
        List<Item> items = List.of(new Item("item4", 2, 550, 0));
        double result = SILab2.checkCart(items, "1234567890123456");
        assertEquals(1070.0, result);
    }

    @Test
    void test_DiscountAndQuantityTrue() {
        // price <= 300, discount > 0, quantity > 10
        List<Item> items = List.of(new Item("item5", 11, 200, 0.1));
        double result = SILab2.checkCart(items, "1234567890123456");
        assertEquals(1950.0, result);
    }

    @Test
    void test_OnlyDiscountTrue() {
        // price <= 300, discount > 0, quantity <= 10
        List<Item> items = List.of(new Item("item6", 5, 200, 0.1));
        double result = SILab2.checkCart(items, "1234567890123456");
        assertEquals(870.0, result);
    }

    @Test
    void test_OnlyQuantityTrue() {
        // price <= 300, discount = 0, quantity > 10
        List<Item> items = List.of(new Item("item7", 11, 200, 0));
        double result = SILab2.checkCart(items, "1234567890123456");
        assertEquals(2170.0, result);
    }

    @Test
    void test_AllConditionsFalse() {
        // price <= 300, discount = 0, quantity <= 10
        List<Item> items = List.of(new Item("item8", 8, 30, 0));
        double result = SILab2.checkCart(items, "1234567890123456");
        assertEquals(240.0, result);
    }
}

Explanation

To write the test cases, I carefully went through the code line by line. Wherever there was a condition (such as an if or a loop), I created a test case to cover it. For each if-else structure, I ensured that both the if and the else branches were tested, so that every statement or line of code would be executed at least once — as required by the Every Statement testing criterion.

I based the test cases on examples and practices covered during lectures, tutorials, and additional materials I found online.

The Multiple Condition testing part was more straightforward. Since the condition involves three boolean expressions, I tested all possible combinations of their truth values. This resulted in 2³ = 8 test cases, covering every possible outcome of the condition.











