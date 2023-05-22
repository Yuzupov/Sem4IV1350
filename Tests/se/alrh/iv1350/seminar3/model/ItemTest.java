package se.alrh.iv1350.seminar3.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.alrh.iv1350.seminar3.integration.ItemDTO;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    Item item;

    @BeforeEach
    void setUp() {
        item = testItem();
    }

    private ItemDTO testItemDTO(){
        int itemID = 123;
        String description = "another test item";
        double price = 20;
        double VAT = 0.1;
        return new ItemDTO(itemID,description,price,VAT);
    }
    private Item testItem(){
        ItemDTO itemDTO = testItemDTO();
        return new Item(itemDTO);
    }

    @AfterEach
    void tearDown() {
        item = null;

    }

    @Test
    void getItemID() {
        assertEquals(123, item.getItemID(), "wrong itemID");
    }

    @Test
    void getDescription() {
        assertEquals("another test item", item.getDescription(), "wrong description");
    }

    @Test
    void getPrice() {
        assertEquals(20, item.getPrice(), "wrong price");
    }

    @Test
    void getVAT() {
        assertEquals(0.1, item.getVAT(), "wrong VAT");
    }

    @Test
    void getDiscount() {
        assertEquals(0, item.getDiscount(), "wrong discount");
    }

    @Test
    void setDiscount() {
        item.setDiscount(0);
        assertEquals(0, item.getDiscount(), "wrong discount set for item");
    }
}