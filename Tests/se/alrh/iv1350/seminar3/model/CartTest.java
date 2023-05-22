package se.alrh.iv1350.seminar3.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.alrh.iv1350.seminar3.integration.InventoryHandler;
import se.alrh.iv1350.seminar3.integration.ItemDTO;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {
    Cart cart;
    ItemDTO itemDTO1;
    ItemDTO itemDTO2;
    InventoryHandler inventoryHandler;

    @BeforeEach
    void setUp() {
        cart = new Cart();
        inventoryHandler = InventoryHandler.getInventoryHandler();
        itemDTO1 = inventoryHandler.fetchItemDTOFromDatabaseAndReturnItemData(12345);
        itemDTO2 = inventoryHandler.fetchItemDTOFromDatabaseAndReturnItemData(54321);

    }

    @AfterEach
    void tearDown() {
        cart = null;
        itemDTO1 = null;
        itemDTO2 = null;
    }

    @Test
    void addItemsToCart() {
        cart.addItemsToCart(itemDTO1, 1);
        Item item1 = new Item(itemDTO1);
        assertEquals(1, cart.getItemCount(item1));

        cart.addItemsToCart(itemDTO2, 4);
        Item item2 = new Item(itemDTO2);
        assertEquals(4, cart.getItemCount(item2));

        cart.addItemsToCart(itemDTO2, 6);
        cart.addItemsToCart(itemDTO2, 2);
        assertEquals(12, cart.getItemCount(itemDTO2));
    }

    @Test
    void getItemCount() {
        cart.addItemsToCart(itemDTO1, 1);
        Item item1 = new Item(itemDTO1);
        assertEquals(1, cart.getItemCount(item1));

        cart.addItemsToCart(itemDTO2, 4);
        Item item2 = new Item(itemDTO2);
        assertEquals(4, cart.getItemCount(item2));

        cart.addItemsToCart(itemDTO2, 6);
        cart.addItemsToCart(itemDTO2, 2);
        assertEquals(12, cart.getItemCount(itemDTO2));
    }

    @Test
    void getTotalPriceIncludingCartDiscount() {
        cart.addItemsToCart(itemDTO1, 1);
        double priceOfFirstEntry = itemDTO1.getPrice()*1;
        double testPriceCalcOfFirstEntry = cart.getTotalPriceIncludingCartDiscount();
        assertEquals(priceOfFirstEntry, testPriceCalcOfFirstEntry, "wrong price");

        cart.addItemsToCart(itemDTO2, 8);
        double priceOfSecondEntry = itemDTO2.getPrice()*8+priceOfFirstEntry;
        double testPriceCalcOfSecondEntrySummedWithFirstEntry = cart.getTotalPriceIncludingCartDiscount();
        assertEquals(priceOfSecondEntry, testPriceCalcOfSecondEntrySummedWithFirstEntry, "wrong in the calculation");

    }

    @Test
    void getTotalVAT() {
        cart.addItemsToCart(itemDTO1, 1);
        double VATOfFirstItem = itemDTO1.getPrice()*1*0.2;
        double testPriceCalcOfFirstEntry = cart.getTotalVAT();
        assertEquals(VATOfFirstItem,testPriceCalcOfFirstEntry, "wrong price");

        cart.addItemsToCart(itemDTO2, 8);
        double VATOfSecondEntry = itemDTO2.getPrice()*8*0.1+VATOfFirstItem;
        double testPriceCalcOfSecondEntrySummedWithFirstEntry = cart.getTotalVAT();
        assertEquals(VATOfSecondEntry, testPriceCalcOfSecondEntrySummedWithFirstEntry, "wrong in the calculation");

    }

    @Test
    void iterator() {
        cart.addItemsToCart(itemDTO1, 1);
        cart.addItemsToCart(itemDTO2, 4);
        HashSet<Integer> itemID = new HashSet<>();
        for(Item item : cart){
            itemID.add(item.getItemID());
        }
        assertTrue(itemID.contains(itemDTO1.getItemID()), "item missing");
        assertTrue(itemID.contains(itemDTO2.getItemID()), "item missing");
        assertEquals(2, itemID.size());
    }
}