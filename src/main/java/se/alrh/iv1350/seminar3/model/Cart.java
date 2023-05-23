package se.alrh.iv1350.seminar3.model;

import se.alrh.iv1350.seminar3.integration.*;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * representation of the customer cart of items that the customer wants to purchase
 */
public class Cart implements Iterable<Item>{
    private final AbstractMap<Integer, CartEntry> items;
    private final double cartDiscount;

    /**
     * creates a Cart as a map, i.e. a collection of items
     */
    Cart() {
        items = new HashMap<>();
        cartDiscount = 1;
    }

    /**
     * adds one or multiple items with the same itemDTO to the {@link Cart}
     * @param itemDTO a DTO with item information
     * @param count quantity of items to be added to the {@link Cart}
     */
    public void addItemsToCart(ItemDTO itemDTO, int count){
        int itemID = itemDTO.getItemID();
        if(items.containsKey(itemID)) {
            items.get(itemID).increaseCount(count);
        }
        else{
            CartEntry cartEntry = new CartEntry(new Item(itemDTO), count);
            items.put(itemID, cartEntry);
        }
    }

    /**
     * gets the count of the item in {@link Cart}
     * @param item the item to count
     * @return the quantity of items
     */
    public int getItemCount(Item item){
        return getItemCount(item.getItemID());
    }

    public int getItemCount(ItemDTO itemDTO){
        return getItemCount(itemDTO.getItemID());
    }

    public int getItemCount(int itemID){
        return items.get(itemID).getCount();
    }

    /**
     * gets the total price of the entire cart including a potential discount
     * but for now no such discount exists
     * @return the total cost of a cart
     */
    public double getTotalPriceIncludingCartDiscount(){
        double totalPrice = 0;
        for( Map.Entry<Integer, CartEntry> entry : items.entrySet())
            totalPrice += (entry.getValue().getTotalPrice());
        return totalPrice*cartDiscount;
    }

    /**
     * gets the total price of the entire cart including a potential discount
     * but for now no such discount exists
     * @return the total cost of a cart
     */
    public double getTotalVAT(){
        double totalVAT = 0;
        for (Map.Entry<Integer, CartEntry> entry: items.entrySet()) {
            totalVAT = (totalVAT+entry.getValue().getTotalVAT());
        }
        return totalVAT*(cartDiscount);
    }

    /**
     * for outside classes to use {@link Cart} as a collection of items,
     * we implement this iterator,
     * this allows for {@link CartEntry} to remain private
     * @return the iterator
     */
    public Iterator<Item> iterator(){
        return new Iterator<Item>() {
            private final Iterator<CartEntry> iter = items.values().iterator();

            @Override
            public boolean hasNext() {
                return iter.hasNext();
            }

            @Override
            public Item next() {
                return iter.next().getItem();
            }
        };
    }

    /**
     * represents an entry into the {@link Cart}, it contains an
     * {@link Item} and its amount
     */
    private static class CartEntry {
        private final Item item;
        private int count;
        public CartEntry(Item item, int count){
            this.item = item;
            this.count = count;
        }

        /**
         * calculates and returns the total price of the items in this {@link CartEntry}
         * @return total price without VAT
         */
        public double getTotalPrice(){
            double count = this.count;
            return (item.getPrice()*count);
        }

        /**
         * calculates and returns the total VAT of the items in this{@link CartEntry}
         * @return total VAT
         */
        public double getTotalVAT(){
            double VAT = item.getVAT();
            return getTotalPrice()*VAT;
        }

        /**
         * increments the counter
         *
         * @param increment just an incrementer
         */
        public void increaseCount(int increment){
            count += increment;
        }
        public int getCount(){
            return count;
        }
        public Item getItem(){
            return item;
        }
    }
}
