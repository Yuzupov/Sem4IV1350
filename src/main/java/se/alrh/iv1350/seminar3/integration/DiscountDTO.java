package se.alrh.iv1350.seminar3.integration;

/**
 * a DTO for discount data from a database to be usable by the backend logic
 * of the program
 * should contain some information such as item discounts and percentage discounts for an entire purchase
 * but does not because it was not required by the basic flow
 */

public class DiscountDTO {

    private final int customerID;
    private final String customerName;

    /**
     * creates a {@link discountDTO} if we had one but since discounts are not part of
     * basic flow it is not implemented
     * @param customerID unique identifier for a customer
     * @param customerName name of said customer
     */
    public DiscountDTO(int customerID, String customerName){
        this.customerID = customerID;
        this.customerName = customerName;
    }

    /**
     * function should return the ID of a customer.
     * @return returns the customerID
     */
    public int getCustomerID(){
        return customerID;
    }

    /**
     * function should return the customer name
     * @return the customer name
     */
    public String getCustomerName(){
        return customerName;
    }

}
