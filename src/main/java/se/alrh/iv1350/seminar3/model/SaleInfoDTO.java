package se.alrh.iv1350.seminar3.model;

import java.time.Instant;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Iterator;

/**
 * collect the required information for accounting about a {@link Sale}
 */
public class SaleInfoDTO implements Iterable<Item> {
    private final double totalPrice;
    private final double totalVAT;
    private final Payment payment;
    private final Change change;
    private final Instant saleTime;
    private final AbstractMap<Item, Integer> items;

    /**
     * creates a SaleInfoDTO from the required information for accounting
     * @param totalPrice the total price of a sale
     * @param totalVAT the total VAT of a sale
     * @param payment the total amount paid by customer
     * @param change the amount of changed owed to the customer
     * @param saleTime records the time of a sale
     * @param cart a cart containing items
     */
    SaleInfoDTO(double totalPrice, double totalVAT, Payment payment, Change change, Instant saleTime, Cart cart){
        this.totalPrice = totalPrice;
        this.totalVAT = totalVAT;
        this.payment = payment;
        this.change = change;
        this.saleTime = saleTime;
        items = new HashMap<>();
        for(Item item : cart){
            items.put(item, cart.getItemCount(item));
        }
    }

    /**
     * copies the constructor, makes a copy of another {@link SaleInfoDTO}
     * @param saleInfoDTO the object that's coped
     */
    SaleInfoDTO(SaleInfoDTO saleInfoDTO){
        totalPrice = saleInfoDTO.totalPrice;
        totalVAT = saleInfoDTO.totalVAT;
        payment = saleInfoDTO.payment;
        change = saleInfoDTO.change;
        saleTime = saleInfoDTO.saleTime;
        items = new HashMap<>(saleInfoDTO.items);
    }

    /**
     * gets the total price without VAT
     * @return total price without VAT
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * gets the total VAT of the sale
     * @return total VAT of the sale
     */
    public double getTotalVAT() {
        return totalVAT;
    }

    /**
     * gets the payment to be made
     * @return payment to be made
     */
    public Payment getPayment(){
        return payment;
    }

    /**
     * gets the change owed to the customer
     * @return the change
     */
    public Change getChange(){
        return change;
    }

    /**
     * gets the time of the sale
     * @return the time of sale
     */
    public Instant getSaleTime() {
        return saleTime;
    }

    /**
     * Makes this class iterable, so we can use it to transfer a list
     * of {@link Item} and their associated amounts
     * @return the iterator
     */
    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>(){
            private final Iterator<Item> iter = items.keySet().iterator();

            @Override
            public boolean hasNext() {
                return iter.hasNext();
            }
            @Override
            public Item next() {
                return iter.next();
            }
        };
    }
}
