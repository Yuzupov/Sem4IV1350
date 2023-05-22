package se.alrh.iv1350.seminar3.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.alrh.iv1350.seminar3.integration.AccountingHandler;
import se.alrh.iv1350.seminar3.integration.InventoryHandler;
import se.alrh.iv1350.seminar3.integration.ItemDTO;
import se.alrh.iv1350.seminar3.integration.Printer;

import java.util.HashSet;
import java.util.Set;
import java.math.*;

import static org.junit.jupiter.api.Assertions.*;

class SaleTest {
    Sale sale;
    Cart cart;
    Change change;
    ItemDTO itemDTO1;
    ItemDTO itemDTO2;
    ItemDTO itemDTO3;
    AccountingHandler accountingHandler;
    InventoryHandler inventoryHandler;
    Printer printer;

    @BeforeEach
    void setUp() {
        accountingHandler = new AccountingHandler();
        inventoryHandler = InventoryHandler.getInventoryHandler();
        printer = new Printer();
        sale = new Sale(accountingHandler, inventoryHandler, printer);


        itemDTO1 = inventoryHandler.fetchItemDTOFromDatabaseAndReturnItemData(12345);
        itemDTO2 = inventoryHandler.fetchItemDTOFromDatabaseAndReturnItemData(54321);
        itemDTO3 = inventoryHandler.fetchItemDTOFromDatabaseAndReturnItemData(1357);
    }

    @AfterEach
    void tearDown() {
        accountingHandler = null;
        inventoryHandler = null;
        printer = null;
        sale = null;
        itemDTO3 = itemDTO1 = itemDTO2 = null;
    }

    @Test
    void addItems() {
        sale.addItems(itemDTO1, 1);
        sale.addItems(itemDTO2, 4);
        sale.addItems(itemDTO3, 3);
        SaleInfoDTO saleInfoDTO = sale.getSaleInfo();
        Set<Integer> itemID = new HashSet<>();
        for(Item item : saleInfoDTO){
            itemID.add(item.getItemID());
        }
        assertEquals(3, itemID.size(), "wrong amount of items in set");
    }

    @Test
    void getSaleInfo() {
        sale.addItems(itemDTO1, 1);
        sale.addItems(itemDTO2, 4);
        sale.addItems(itemDTO3, 3);
        SaleInfoDTO saleInfoDTO = sale.getSaleInfo();

        double expectedPrice = itemDTO1.getPrice()+((itemDTO2.getPrice()*4)+(itemDTO3.getPrice()*3));
        double testedPrice = saleInfoDTO.getTotalPrice();
        assertEquals(expectedPrice, testedPrice, "wrong price info in saleInfoDTO");

        double expectedVAT = ((itemDTO1.getPrice()* itemDTO1.getVAT())*1
                +(itemDTO2.getPrice()* itemDTO2.getVAT()*4)
                +(itemDTO3.getPrice()* itemDTO3.getVAT())*3);
        double testedPriceIncludingVAT = saleInfoDTO.getTotalVAT();
        assertEquals(testedPriceIncludingVAT, expectedVAT, "wrong VAT info in saleInfoDTO");

        assertNotNull(saleInfoDTO.getSaleTime());
        assertNull(saleInfoDTO.getPayment());
        assertNull(saleInfoDTO.getChange());

        Payment payment = new Payment(1000);
        sale.endSale(payment);
        saleInfoDTO= sale.getSaleInfo();
        assertEquals(payment,saleInfoDTO.getPayment(), "wrong payment");

        double expectedChange = (payment.getPayment() - (expectedPrice+expectedVAT));
        Change actualChange = saleInfoDTO.getChange();

        assertEquals(expectedChange, actualChange.getAmount(), "wrong change amount");
    }

    @Test
    void endSale() {
        Change change = sale.endSale(new Payment(100));
        assertNotNull(change);
    }

    @Test
    void applyDiscount() {
        //No need for applying discounts for this seminar
    }
}