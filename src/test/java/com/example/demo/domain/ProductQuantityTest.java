package com.example.demo.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ProductQuantityTest {


    private DynamicTest IntToTest(Integer value) {
        String testName = "Value " + value + " should be valid";
        return DynamicTest.dynamicTest(testName, () -> {

            // act
            ProductQuantity productQuantity = new ProductQuantity(value);

            // assert
            assertEquals(value, productQuantity.asInteger());
        });
    }


    @TestFactory
    @DisplayName("1 until 100 is a valid product quantity")
    Stream<DynamicTest> validValues() {
        return IntStream.rangeClosed(1, 100)
                .mapToObj(value -> this.IntToTest(value));
    }


    @Test
    @DisplayName("Product quantity null throws an error")
    void nullCheck() {
        // arrange
        Integer value = null;

        // act
        Executable executable = () -> new ProductQuantity(value);


        // assert
        assertThrows(NullPointerException.class, executable);
    }


    @Test
    @DisplayName("Invalid product quantity throws an error")
    void illegalValueCheck() {
        // arrange
        Integer value = -7;

        // act
        Executable executable = () -> new ProductQuantity(value);


        // assert
        assertThrows(IllegalArgumentException.class, executable);
    }

}