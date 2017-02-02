package app.gui;

import app.list.ShoppingItem;
import app.list.ShoppingList;

/**
 * @author Jenni Unkuri jenni.unkuri@cs.tamk.fi
 * @version 2016.1130
 * @since 1.7
 */
public class Controller {

    /**
     * ShoppingList object.
     */
    private ShoppingList<ShoppingItem> list;

    /**
     * Constructs Controller object and creates shopping list.
     *
     * @param list ShoppingList with ShoppingItems
     */
    public Controller(ShoppingList<ShoppingItem> list) {
        this.list = list;
    }

    /**
     * Adds item to ShoppingList.
     *
     * @param amount amount of shopping item
     * @param product product of shopping item
     */
    public void addToList(int amount, String product) {
        list.add(new ShoppingItem(amount, product));
    }

    /**
     * Adds string array to list.
     * 
     * @param array array to add
     */
    public void addToList(String[] array) {
        list.addArray(array);
    }

    /**
     * Checks if input is valid.
     *
     * @param amount amount of product
     * @param product name of product
     * @return true is data is valid, false if not
     */
    public boolean arrayIsValid(int amount, String product) {
        return amount != 0 && !product.equals("");
    }

    /**
     * Checks if amount of product is correct to add to list.
     *
     * @param amount amount of shopping item
     * @param product product of shopping item
     * @return true if amount is valid, false if not
     */
    public boolean amountIsValid(int amount, String product) {
        boolean isValid = false;
        ShoppingItem item = new ShoppingItem(amount, product);

        // Return true, if contains product and sum is valid
        if (list.containsProduct(item) && list.sumIsValid(item)) {
            isValid = true;
            // Return true, if does not contain product and amount is positive
        } else if (!list.containsProduct(item) && item.getAmount() > 0) {
            isValid = true;
        }

        return isValid;
    }

    /**
     * Checks if list contains shopping item.
     *
     * @param amount amount of shopping item
     * @param product product of shopping item
     * @return true if list contains item, false if not
     */
    public boolean contains(int amount, String product) {
        return list.containsProduct(new ShoppingItem(amount, product));
    }

    /**
     * Returns Object[][] array presentation of ShoppingList.
     *
     * @return Object[][] ShoppingList
     */
    Object[][] getArray() {
        Object[][] object = new Object[list.getSize()][];

        for (int i = 0; i < list.getSize(); i++) {

            ShoppingItem item = (ShoppingItem) list.get(i);
            int amount = item.getAmount();
            String product = item.getProduct();

            object[i] = new String[]{String.valueOf(amount), product};
        }

        return object;
    }

    /**
     * Clears ShoppingList.
     */
    public void clear() {
        list.clear();
    }

    /**
     * Removes rows from ShoppingList.
     *
     * @param indeces indeces of items to remove
     */
    public void remove(int[] indeces) {
        sortDescending(indeces);

        for (int index : indeces) {
            list.remove(index);
        }
    }

    /**
     * Sorts indeces to descending order.
     *
     * @param array integer array to sort
     * @return array sorted
     */
    public int[] sortDescending(int[] array) {
        int temp = 0;

        for (int i = 0; i < array.length; i++) {

            for (int j = i + 1; j < array.length; j++) {

                if (array[i] < array[j]) {
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }

        return array;
    }

    /**
     * Checks if first word in string is integer.
     *
     * @param string string to check
     * @return true if first word is integer false if not
     */
    public boolean firstIsInteger(String string) {
        string = string.trim();
        String[] words = string.split("\\s+");;
        try {
            Integer.parseInt(words[0]);
        } catch (NumberFormatException e) {
            return false;
        }
        
        return true;
    }

    /**
     * Gets ShoppingList object.
     *
     * @return ShoppingList
     */
    public ShoppingList<ShoppingItem> getList() {
        return list;
    }

    /**
     * Sets ShoppingList object.
     *
     * @param list ShoppingList
     */
    public void setList(ShoppingList<ShoppingItem> list) {
        this.list = list;
    }
    
    /**
     * Adds item to index in Shopping List.
     * 
     * @param index index to add to
     * @param amount amount of item
     * @param product name of item
     */
    public void updateIndex(int index, int amount, String product) {
        ShoppingItem item = new ShoppingItem(amount, product);
        list.updateIndex(index, item);
    }
}
