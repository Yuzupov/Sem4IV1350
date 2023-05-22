package se.alrh.iv1350.seminar3.model;

import se.alrh.iv1350.seminar3.integration.*;
import java.util.ArrayList;
import java.time.Instant;
/**
 * one single sale made by one single customer
 */
public class Sale {
    private final Cart cart;
    private final Instant saleTime;
    private final AccountingHandler accountingHandler;
    private final InventoryHandler inventoryHandler;
    private final Printer printer;
    private Payment payment;
    private Change change;
    private boolean finalized;
    private ArrayList<SaleObserver> saleObservers = new ArrayList<>();

    /**
     * models an instance of a Sale
     * handles transaction of a collection of items in exchange for a {@link Payment}
     * @param accountingHandler a handler for accounting
     * @param inventoryHandler a handler for the inventory system
     * @param printer prints receipts
     */
    public Sale(AccountingHandler accountingHandler, InventoryHandler inventoryHandler,Printer printer) {
        this.accountingHandler = accountingHandler;
        this.inventoryHandler = inventoryHandler;
        this.printer = printer;
        saleTime = Instant.now();
        cart = new Cart();
        finalized = false;
    }

    /**
     * add one {@link Item} or more (of the same {@link Item}) to the cart of the current sale
     * @param itemDTO an itemDTO with the item data
     * @param count quantity of said {@link Item}
     */
    public void addItems(ItemDTO itemDTO, int count){
        cart.addItemsToCart(itemDTO, count);
    }

    /**
     * gets the required information for accounting about the sale
     * @return a {@link SaleInfoDTO} that contains said information
     */
    public SaleInfoDTO getSaleInfo(){
        return new SaleInfoDTO(cart.getTotalPriceIncludingCartDiscount(), cart.getTotalVAT(), payment, change, saleTime, cart);
    }

    /**
     * ends the sale and returns the change
     * @param payment the payment entered
     * @return the change that is owed to the customer
     */
    public Change endSale(Payment payment){
        if(finalized){
            return this.change;
        }
        finalized = true;

        for(Item item : cart){
            int count = cart.getItemCount(item);
            inventoryHandler.updateInventory(item.getItemID(), count);
        }

        this.payment = payment;
        double priceWithVAT = (cart.getTotalPriceIncludingCartDiscount()+cart.getTotalVAT());
        this.change = new Change(payment.getPayment()-priceWithVAT);
        accountingHandler.logSale(getSaleInfo());
        Receipt receipt = new Receipt(getSaleInfo());
        printer.printReceipt(receipt);
        notifyObservers();
        return this.change;
    }
    private void notifyObservers() {
        for (SaleObserver obs : saleObservers){
            obs.newSale(getSaleInfo());
        }
    }
    public void addSaleObservers(ArrayList<SaleObserver> observers){
        saleObservers.addAll(observers);
    }
}
