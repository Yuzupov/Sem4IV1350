package se.alrh.iv1350.seminar3.integration;

import se.alrh.iv1350.seminar3.model.*;
import java.util.ArrayList;

/**
 * this class acts as an interface between an accounting database that logs sale information
 * and the rest of the program
 */

public class AccountingHandler {

    private SaleInfoDTO saleInfo;
    private Sale sale;
    private ArrayList itemList;

    public AccountingHandler(){
    }

    /**
     * logs a sale in the accounting database if one existed, which it does not
     * @param saleInfo a DTO that contains the required information for accounting
     */
    public void logSale(SaleInfoDTO saleInfo){
        //Dummy function, there is no database to log to
    }
}
