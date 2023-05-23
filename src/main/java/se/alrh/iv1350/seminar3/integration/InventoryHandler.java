package se.alrh.iv1350.seminar3.integration;

import se.alrh.iv1350.seminar3.logAPI.LogHandler;
import se.alrh.iv1350.seminar3.utility.LogType;
import se.alrh.iv1350.seminar3.view.ErrorMessageHandler;
import java.io.IOException;
import java.util.HashMap;
/**
 * this class is the interface between an inventory database and the rest of the program
 */

public class InventoryHandler {
    LogHandler logHandler = new LogHandler();
    private static final InventoryHandler INVENTORY_HANDLER;

    static {
        try {
            INVENTORY_HANDLER = new InventoryHandler();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * for now this is a placeholder for data for items in the store
     */
    private final HashMap<Integer, ItemDTO> dataForItemsInStore;

    /**
     * placeholder constructor
     * this creates a temporary placeholder database that acts in the role of an actual database
     */
    private InventoryHandler() throws IOException {
        dataForItemsInStore = new HashMap<>();
        createEntryForItemWithData(new ItemDTO(12345, "this is itemOne", 40, 0.2 ));
        createEntryForItemWithData(new ItemDTO(54321, "this is itemTwo", 80, 0.1));
        createEntryForItemWithData(new ItemDTO(1357, "this is itemThree", 100, 0.25));
    }

    public static InventoryHandler getInventoryHandler(){
        return INVENTORY_HANDLER;
    }

    /**
     * adds an item to the placeholder database
     * @param itemDTO the itemDTO to add
     */
    private void createEntryForItemWithData(ItemDTO itemDTO){
        dataForItemsInStore.put(itemDTO.getItemID(), itemDTO);
    }

    /**
     * fetches an itemDTO from a database using an itemID and throws an exception
     * if they're not found in the database
     * or if they can't connect to the database
     * @param itemID the itemID that is given to the method
     * @return ItemDTO that is returned
     * @throws InvalidItemIDInDatabase thrown if the itemID doesn't exist in the database
     * @throws DatabaseConnectionErrorException thrown if it can't connect to database
     * @throws IOException thrown if it can't create or write to the file
     */
    public ItemDTO fetchItemDTOFromDatabaseAndReturnItemDataWithExceptionHandling(int itemID) throws InvalidItemIDInDatabase, DatabaseConnectionErrorException, IOException {
        if (itemID == 99999) {
            logHandler.loggingClassLog(ErrorMessageHandler.errorMessageToDeveloper(), LogType.DATABASECONNECTIONERROR);
            DatabaseConnectionErrorException e = new DatabaseConnectionErrorException("unable to connect to database");
            throw e;
        }
        else if (dataForItemsInStore.containsKey(itemID)) {
            return  dataForItemsInStore.get(itemID);
        }
        throw new InvalidItemIDInDatabase(itemID);
    }
    /**
     * gets the price from a specific itemDTO
     * @param itemDTO specific itemDTO
     * @return the {@link "price"} of the item
     */
    public double getPriceFromDTO(ItemDTO itemDTO){
        return itemDTO.getPrice();
    }

    /**
     * gets the VAT from a specific itemDTO
     * @param itemDTO specific itemDTO
     * @return the {@link "VAT"} of an item
     */
    public double getVATFromDTO(ItemDTO itemDTO){
        return itemDTO.getVAT();
    }

    /**
     * updates the inventory with the items that were sold
     * @param itemID unique itemID
     * @param count the quantity of item sold
     */
    public void updateInventory(int itemID, int count){
        //Dummy function, there is no inventory database to update
    }
}
