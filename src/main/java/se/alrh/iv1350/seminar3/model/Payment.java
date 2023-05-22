package se.alrh.iv1350.seminar3.model;

/**
 * model of a payment procedure
 */
public class Payment{
    private final double payment;
    /**
     * constructor that initializes a Payment with an amount of money
     * @param payment the amount that is paid by the customer
     */
    public Payment(double payment){
        this.payment = payment;
    }

    /**
     * gets the amount to be paid by the customer
     * @return specific payment
     */
    public double getPayment(){
        return payment;
    }
}


