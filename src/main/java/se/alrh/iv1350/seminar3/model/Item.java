package se.alrh.iv1350.seminar3.model;

import se.alrh.iv1350.seminar3.integration.ItemDTO;

/**
 * models an item sold in a store
 */
public class Item {

    private final int itemID;
    private final String description;
    private final double price;
    private final double VAT;
    private double discount;

    /**
     * gets an items specific ID
     * @return the specific ID
     */
    public int getItemID() {
        return itemID;
    }

    /**
     * gets an items specific description
     * @return the specific description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the specific price of an item
     * @return the specific price
     */
    public double getPrice() {
        return price;
    }

    /**
     * gets the specific VAT of an item
     * @return the specific VAT
     */
    public double getVAT() {
        return VAT;
    }

    /**
     * gets a potential discount if one existed
     * @return the discount
     */
    public double getDiscount() {
        return discount;
    }

    /**
     * sets a specific discount of an item
     * @param discount the discount to be set
     */
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    /**
     * constructor of an item from an {@link ItemDTO}
     * @param itemDTO specific itemDTO
     */
    public Item(ItemDTO itemDTO) {
        this.itemID = itemDTO.getItemID();
        this.description = itemDTO.getDescription();
        this.price = itemDTO.getPrice();
        this.VAT = itemDTO.getVAT();
        this.discount = 0.00;
    }
}