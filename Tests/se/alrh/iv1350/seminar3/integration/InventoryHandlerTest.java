package se.alrh.iv1350.seminar3.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryHandlerTest {
    InventoryHandler inventoryHandler;

    @BeforeEach
    void setUp() {
        inventoryHandler = InventoryHandler.getInventoryHandler();
    }

    @AfterEach
    void tearDown() {
        inventoryHandler = null;
    }

    @Test
    void ifExceptionIsThrownAssertionSucceeds(){
        assertThrows(InvalidItemIDInDatabase.class, () -> inventoryHandler.fetchItemDTOFromDatabaseAndReturnItemDataWithExceptionHandling(123), "wrong or no exceptions was thrown");
        assertThrows(DatabaseConnectionErrorException.class, ()-> inventoryHandler.fetchItemDTOFromDatabaseAndReturnItemDataWithExceptionHandling(99999), "wrong or no exception was thrown");
    }
}