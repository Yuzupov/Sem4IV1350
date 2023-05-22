package se.alrh.iv1350.seminar3.integration;

import se.alrh.iv1350.seminar3.model.Receipt;
/**
 * acts as a virtual version of a physical printer
 */
public class Printer {
    public Printer() {
    }

    /**
     * prints the receipt with the total price, total VAT, and the sale time
     * @param receipt the receipt that is to be printed to System.out
     */
    public void printReceipt(Receipt receipt){
        System.out.println("Receipt:");
        System.out.println("total price ex VAT: " + receipt.getTotalPrice() + " kr");
        System.out.println("total VAT: " + receipt.getTotalVAT() + " kr");
        System.out.println("time of sale: " + receipt.getSaleTime());
        System.out.println("you paid: "+ receipt.getPayment().getPayment() + " kr");
        System.out.println("your change: " + (receipt.getPayment().getPayment()-receipt.getTotalPrice()-receipt.getTotalVAT() + " kr"));
    }
}
