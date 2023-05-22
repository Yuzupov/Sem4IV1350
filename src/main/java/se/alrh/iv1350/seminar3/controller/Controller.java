package se.alrh.iv1350.seminar3.controller;

import se.alrh.iv1350.seminar3.model.SaleObserver;
import se.alrh.iv1350.seminar3.integration.*;
import se.alrh.iv1350.seminar3.logAPI.LogHandler;
import se.alrh.iv1350.seminar3.model.*;
import se.alrh.iv1350.seminar3.utility.LogType;
import se.alrh.iv1350.seminar3.view.ErrorMessageHandler;
import java.io.IOException;
import java.util.ArrayList;

/**
 * this is the only controller in the app
 * all the calls are passed through this controller
 * it represents an interface between the view and
 * the backend logic of the program
 */

public class Controller {

    private final Printer printer;
    private final InventoryHandler inventoryHandler;
    private final AccountingHandler accountingHandler;
    private final DiscountHandler discountHandler;
    private LogHandler logHandler;
    private Sale sale;
    private ArrayList<SaleObserver> saleObservers = new ArrayList<>();

    /**
     * initializes a controller and associated connections
     */
    public Controller() throws IOException {
        printer = new Printer();
        inventoryHandler = InventoryHandler.getInventoryHandler();
        accountingHandler = new AccountingHandler();
        discountHandler = new DiscountHandler();
        LogHandler logHandler = new LogHandler();

    }

    /**
     * Starts a new sale.
     * Must be done to process a new purchase
     * represented as an instance of {@link Sale}
     */
    public void startSale() {
        sale = new Sale(accountingHandler, inventoryHandler, printer);
    }

    /**
     * gets the itemDTO from an item based off of the itemID
     * @param itemID is a unique item identifier
     * @return returns the specified itemDTO
     */
    public ItemDTO itemDTOFromItemID(int itemID) throws DatabaseConnectionErrorException, InvalidItemIDInDatabase, InvalidIDException, DatabaseConnectionErrorRePackagerException, IOException {
        ItemDTO itemDTO;
        try {
            itemDTO  = inventoryHandler.fetchItemDTOFromDatabaseAndReturnItemDataWithExceptionHandling(itemID);
            return itemDTO;
        } catch (InvalidItemIDInDatabase e){
            throw new InvalidIDException(itemID, e);
        } catch (DatabaseConnectionErrorException e){
            throw new DatabaseConnectionErrorRePackagerException(e);
        } catch (IOException e){
            throw new IOException();
        }
    }

    /**
     * adds a specified amount of an item to the {@link Sale}
     *
     * @param itemID unique item identifier
     * @param count  the quantity of items to be added
     */
    public ItemDTO addItemMultipleWithException(int itemID, int count) throws DatabaseConnectionErrorRePackagerException, InvalidIDException, IOException {
        try {
            ItemDTO itemDTO = inventoryHandler.fetchItemDTOFromDatabaseAndReturnItemDataWithExceptionHandling(itemID);
            sale.addItems(itemDTO, count);
            return itemDTO;
        } catch (InvalidItemIDInDatabase e) {
            throw new InvalidIDException(itemID, e);
        } catch (DatabaseConnectionErrorException e) {
            throw new DatabaseConnectionErrorRePackagerException(e);
        } catch (IOException e){
            throw new IOException();
        }
    }

    /**
     * fetches the information about the sale
     * @return a saleInfoDTO that contains the requested sale information
     */
    public SaleInfoDTO getSaleInfo(){
        return sale.getSaleInfo();
    }

    /**
     * returns the amount of change of a payment procedure
     * @param payment the payment received
     * @return the amount of change based off of the payment received
     */
    public void endSale(Payment payment){
        sale.addSaleObservers(saleObservers);
        sale.endSale(payment);
        sale = null;
    }
    public void addSaleObserver(SaleObserver obs){
        saleObservers.add(obs);
    }

    /**
     * is supposed to fetch discounts from a discount database but currently
     * holds no function due to not being part of the basic flow
     * @param customerID a unique number that identifies if a customer is eligible for a discount
     */
    public void requestDiscount(int customerID){
        //Discount isn't in the basic flow
    }

}
