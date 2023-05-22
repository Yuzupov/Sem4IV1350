package se.alrh.iv1350.seminar3.utility;

/**
 * models a fixed amount of money
 */
public abstract class MonetaryAmount {
    private final double amount;

    /**
     * creates an object with a given amount of money
     * @param amount the amount of money
     */
    public MonetaryAmount(double amount){
        this.amount = amount;
    }

    /**
     * gets the amount
     * @return the amount
     */
    public double getAmount(){
        return amount;
    }
}
