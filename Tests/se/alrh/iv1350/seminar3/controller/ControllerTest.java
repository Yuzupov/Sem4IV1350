package se.alrh.iv1350.seminar3.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.alrh.iv1350.seminar3.integration.DatabaseConnectionErrorException;
import se.alrh.iv1350.seminar3.integration.InvalidItemIDInDatabase;
import se.alrh.iv1350.seminar3.integration.ItemDTO;
import se.alrh.iv1350.seminar3.model.Change;
import se.alrh.iv1350.seminar3.model.Payment;
import se.alrh.iv1350.seminar3.model.SaleInfoDTO;

import java.io.IOException;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    Controller controller;
    int itemID;
    int itemAmount;


    @BeforeEach
    void setUp() throws IOException {
        controller = new Controller();
        itemID = 12345;
        itemAmount = 3;
    }

    @AfterEach
    void tearDown() {
        controller = null;
        itemID = 0;
    }
}