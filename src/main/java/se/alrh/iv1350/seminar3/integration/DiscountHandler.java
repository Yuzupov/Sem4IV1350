package se.alrh.iv1350.seminar3.integration;

import se.alrh.iv1350.seminar3.model.Sale;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * this class is the interface between discount database and the rest of the program
 */
public class DiscountHandler {

    private Sale sale;
    private ArrayList itemList;
    private DiscountDTO discountDTO;
    private HashMap<Integer, String> customerIDDB;
    private int customerID;
    private String customerName;

    /**
     * fetches the discounts of a given customer from the discounts database
     */
    public DiscountHandler(){
        customerIDDB = new HashMap<>();
        placeHolderEntry(new DiscountDTO(123456789, "sven svensson"));
    }

    /**
     *
     * @param discountDTO
     */
    private void placeHolderEntry(DiscountDTO discountDTO){
        customerIDDB.put(discountDTO.getCustomerID(), customerName);
    }
    public boolean checkDiscountValidity(int customerID) {
        if (customerIDDB.containsKey(discountDTO.getCustomerID()));
        return true;
    }

    /*public void applyDiscount(double totalPrice){
        double discount = 0.2;
        double tempPrice;
        if(checkDiscountValidity(customerID)){
            tempPrice = sale.returnTotalPriceOfList(itemList);
            tempPrice = tempPrice * discount;
        }
    }*/
}
