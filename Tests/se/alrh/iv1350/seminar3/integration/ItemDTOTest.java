package se.alrh.iv1350.seminar3.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemDTOTest {
    ItemDTO itemDTO;
    int itemID;
    String description;
    double price;
    double VAT;

    @BeforeEach
    void setUp() {
        itemDTO = testItemDTO();
    }
    public ItemDTO testItemDTO(){
        itemID = 123;
        description = "this is a test item";
        price = 100;
        VAT = 0.25;

        return new ItemDTO(itemID, description, price, VAT);
    }

    @AfterEach
    void tearDown() {
        itemDTO = null;
    }

    @Test
    void getItemID() {
        assertEquals(itemID, itemDTO.getItemID(), "wrongID");
    }

    @Test
    void getDescription() {
        assertEquals(description, itemDTO.getDescription(), "wrong description");
    }

    @Test
    void getPrice() {
        assertEquals(price, itemDTO.getPrice(), "wrong price");
    }

    @Test
    void getVAT() {
        assertEquals(0.25, itemDTO.getVAT(), "wrong VAT");
    }
}