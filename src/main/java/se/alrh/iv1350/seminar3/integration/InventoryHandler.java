package se.alrh.iv1350.seminar3.integration;

import javafx.event.EventDispatchChain;
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
        StringBuilder sb = new StringBuilder();
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
     * checks if an item exists in the database
     * @param itemID the unique item identifier
     * @return returns true if it exists
     */
    public boolean checkIfItemExistsInDatabase(int itemID) {
        return dataForItemsInStore.get(itemID) != null;
    }

    /**
     * fetches the itemDTO from an itemID
     *
     * @param itemID unique item identifier
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
    public ItemDTO fetchItemDTOFromDatabaseAndReturnItemData (int itemID){

        ItemDTO itemData;
        if (checkIfItemExistsInDatabase(itemID)){
            itemData = dataForItemsInStore.get(itemID);
        }
        else {
            return null;
        }
        return itemData;
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
