package se.alrh.iv1350.seminar3.integration;
import java.lang.String;

/**
 * a DTO for transferring data of an item from the integration layer to the model layer
 */
public class ItemDTO {
    private final int itemID;
    private final String description;
    private final double price;
    private final double VAT;

    /**
     * creates an ItemDTO with data from a fantasy inventory database
     * @param itemID unique item identifier
     * @param description a description of specified item
     * @param price price of a specified item
     * @param VAT VAT of a specified item
     */
    public ItemDTO(int itemID,String description, double price, double VAT){
        this.itemID = itemID;
        this.description = description;
        this.price = price;
        this.VAT = VAT;
    }

    /**
     * gets itemID
     * @return the specified itemID
     */
    public int getItemID() { return itemID; }

    /**
     * gets the description
     * @return the description of an item
     */
    public String getDescription() { return this.description; }

    /**
     * gets the price of an item
     * @return the price of an item
     */
    public double getPrice() { return price; }

    /**
     * gets the VAT of an item
     * @return the VAT of an item
     */
    public double getVAT() { return VAT; }

}

