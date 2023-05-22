package se.alrh.iv1350.seminar3.controller;

import com.sun.xml.internal.ws.policy.spi.AssertionCreationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.alrh.iv1350.seminar3.integration.DatabaseConnectionErrorException;
import se.alrh.iv1350.seminar3.integration.InvalidItemIDInDatabase;
import se.alrh.iv1350.seminar3.integration.InventoryHandler;
import se.alrh.iv1350.seminar3.integration.ItemDTO;
import se.alrh.iv1350.seminar3.logAPI.LogHandler;
import se.alrh.iv1350.seminar3.model.Change;
import se.alrh.iv1350.seminar3.model.Payment;
import se.alrh.iv1350.seminar3.model.SaleInfoDTO;
import se.alrh.iv1350.seminar3.utility.LogType;
import se.alrh.iv1350.seminar3.view.ErrorMessageHandler;

import java.io.IOException;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    Controller controller;
    LogHandler logHandler;
    ErrorMessageHandler errorMessageHandler;
    LogType logType;
    InventoryHandler inventoryHandler;
    int itemID;
    int itemAmount;


    @BeforeEach
    void setUp() throws IOException {
        controller = new Controller();
        logHandler = new LogHandler();
        errorMessageHandler = new ErrorMessageHandler();
        itemAmount = 3;
        this.inventoryHandler = InventoryHandler.getInventoryHandler();
    }

    @AfterEach
    void tearDown() {
    }
    @Test
    void addItemMultipleWithException() throws InvalidIDException, DatabaseConnectionErrorRePackagerException, IOException, InvalidItemIDInDatabase {
        assertThrows(DatabaseConnectionErrorRePackagerException.class,()-> controller.addItemMultipleWithException(99999,1),"Wrong or no exception thrown");
        assertThrows(InvalidIDException.class, ()-> controller.addItemMultipleWithException(123,1), "wrong or no exception thrown");
    }
}