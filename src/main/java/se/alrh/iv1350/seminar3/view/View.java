package se.alrh.iv1350.seminar3.view;

import se.alrh.iv1350.seminar3.controller.DatabaseConnectionErrorRePackagerException;
import se.alrh.iv1350.seminar3.controller.InvalidIDException;
import se.alrh.iv1350.seminar3.controller.Controller;
import se.alrh.iv1350.seminar3.integration.*;
import se.alrh.iv1350.seminar3.model.*;
import java.io.IOException;
/**
 * the view, represents the user interface of the program
 */
public class View {
    private final Controller contr;
    private TotalRevenueView totalRevenueView;
    private TotalRevenueFileOutput totalRevenueFileOutput;

    /**
     * creates a view with a {@link Controller} instance it uses to communicate with the program
     * @param contr is controller
     */
    public View(Controller contr) throws IOException {
        this.contr = contr;
        totalRevenueView = new TotalRevenueView();
        contr.addSaleObserver(totalRevenueView);
        totalRevenueFileOutput = new TotalRevenueFileOutput();
        contr.addSaleObserver(totalRevenueFileOutput);
    }

    /**
     * runs a simulated version of multiple sales after each other
     * throws exceptions that are caught in the view
     * @throws InvalidItemIDInDatabase thrown if the itemID isn't found in the database
     * @throws DatabaseConnectionErrorException thrown if connection to database can't be established
     */
    public void runFakeExecution() throws InvalidItemIDInDatabase, DatabaseConnectionErrorException {
        contr.startSale();
        System.out.println("a new sale has been started!");
        scanItem(12345, 2);
        scanItem(54321, 3);
        scanItem(123);
        scanItem(99999, 3);
        scanItem(1357);
        Payment payment = new Payment(1000);
        System.out.println("---------------------------");
        contr.endSale(payment);

        contr.startSale();
        System.out.println("a new sale has been started!");
        scanItem(12345, 3);
        scanItem(54321, 5);
        scanItem(1357, 2);
        payment = new Payment(800);
        System.out.println("---------------------------");
        contr.endSale(payment);
        contr.startSale();
        System.out.println("");
        System.out.println("A new sale has been started!");
        scanItem(12345, 5);
        scanItem(54321);
        scanItem(1357, 3);
        payment = new Payment(900);
        System.out.println("---------------------------");
        contr.endSale(payment);
    }
    private void scanItem(int itemID) throws DatabaseConnectionErrorException, InvalidItemIDInDatabase {
        scanItem(itemID, 1);
    }
    private void scanItem(int itemID, int amount) throws DatabaseConnectionErrorException, InvalidItemIDInDatabase {
        try {
            ItemDTO itemInfo = contr.itemDTOFromItemID(itemID);
            contr.addItemMultipleWithException(itemID, amount);
            System.out.println("------------------------");
            System.out.println("| " + amount + " x " + itemInfo.getDescription());
            double priceOfItem = (itemInfo.getPrice());
            double VATOfItem = itemInfo.getVAT();
            System.out.println("| Price per item : " + priceOfItem + " kr " + "VAT : " + (priceOfItem * VATOfItem));
            SaleInfoDTO saleInfoDTO = contr.getSaleInfo();
            System.out.println("------------------------");
            double runningTotal = (saleInfoDTO.getTotalPrice() + saleInfoDTO.getTotalVAT());
            System.out.println("| Running Total: " + runningTotal + " kr ");
        } catch (DatabaseConnectionErrorRePackagerException e) {
            ErrorMessageHandler.errorMessageToUser("Could not connect to database");
        } catch (InvalidIDException e) {
            ErrorMessageHandler.errorMessageToUser("Invalid item identifier entered: "+itemID);
        } catch (IOException e){
            System.out.println("IOException caught");
        }
    }
}
