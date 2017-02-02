package app.list;

/**
 * Is used in storing ShoppingItems in a linked list.
 *
 * @author Jenni Unkuri jenni.unkuri@cs.tamk.fi
 * @version 2016.1130
 * @since 1.7
 *
 * @param <T> the type of the value being added
 */
public class ShoppingList<T> {

    /**
     * First Element in ShoppingList.
     */
    private Element first;

    /**
     * Amount of Elements in ShoppingList.
     */
    private int size;

    /**
     * Adds the shopping item to the list.
     *
     * If product in shopping item is already in the list,
     *
     * @param item object to add
     */
    public void add(ShoppingItem item) {

        int amount = item.getAmount();

        if (size == 0 && amount > 0) {
            addToBeginning(item);
        } else if (!containsProduct(item) && amount > 0) {
            addToEnd(item);
        } else if (containsProduct(item) && amount != 0) {
            updateElement(item);
        } else {
            printErrorMessage(item);
        }
    }

    /**
     * Adds shopping item to beginning of list.
     *
     * @param item item to add
     */
    public void addToBeginning(ShoppingItem item) {
        first = new Element();
        first.setContent(item);
        size++;
    }

    /**
     * Adds shopping item to end of list.
     *
     * @param item item to add
     */
    public void addToEnd(ShoppingItem item) {
        Element element = first;

        for (int i = 0; i < size - 1; i++) {
            element = element.getNext();
        }

        Element last = new Element();
        element.setNext(last);
        last.setContent(item);
        size++;
    }

    /**
     * Updates or removes element from list depending on sum of amounts.
     *
     * If sum is zero, element is removed.
     *
     * @param item item to be added to list
     */
    public void updateElement(ShoppingItem item) {
        Element element = first;

        for (int i = 0; i < size; i++) {

            ShoppingItem iterable = (ShoppingItem) element.getContent();

            if (item.getProduct().equals(iterable.getProduct())) {

                int sum = iterable.getAmount() + item.getAmount();

                if (sum > 0) {
                    iterable.setAmount(sum);
                    element.setContent(iterable);
                } else if (sum == 0) {
                    remove(i);
                } else {
                    printErrorMessage(item);
                }

                break;
            }

            if (i < size - 1) {
                element = element.getNext();
            }
        }
    }

    /**
     * Updates element in index.
     *
     * @param index index to update
     * @param item item to add to index
     */
    public void updateIndex(int index, ShoppingItem item) {
        Element element = first;

        for (int i = 0; i <= index; i++) {
            if (i == index) {
                element.setContent(item);
            }
            
            if (i < size - 1) {
                element = element.getNext();
            }
        }
    }

    /**
     * Searches the shopping list for product in shopping item.
     *
     * @param item shopping item to compare
     * @return true if product is already in shopping list, false if not
     */
    public boolean containsProduct(ShoppingItem item) {
        boolean hasProduct = false;

        Element element = first;

        for (int i = 0; i < size; i++) {

            ShoppingItem iterable = (ShoppingItem) element.getContent();

            if (item.getProduct().equals(iterable.getProduct())) {
                hasProduct = true;
                break;
            }

            if (i < size - 1) {
                element = element.getNext();
            }
        }

        return hasProduct;
    }

    /**
     * Validates sum of product amounts.
     *
     * Sum must be at least zero.
     *
     * @param item item to add
     * @return true if amount is valid, false if not
     */
    public boolean sumIsValid(ShoppingItem item) {
        Element element = first;

        for (int i = 0; i < size; i++) {

            ShoppingItem iterable = (ShoppingItem) element.getContent();

            if (item.getProduct().equals(iterable.getProduct())) {

                int sum = iterable.getAmount() + item.getAmount();

                if (item.getAmount() != 0 && sum >= 0) {
                    return true;
                }
            }

            if (i < size - 1) {
                element = element.getNext();
            }
        }

        return false;
    }

    /**
     * Prints error message to command line interface.
     *
     * @param item item that causes error
     */
    public void printErrorMessage(ShoppingItem item) {
        System.err.println("Incorrect input \"" + item.getAmount()
                + " " + item.getProduct() + "\", item not added");
    }

    /**
     * Removes all of the elements from the list.
     */
    public void clear() {
        first = null;
        size = 0;
    }

    /**
     * Returns the element at the specified position in the list.
     *
     * @param index specified position
     * @return object in the element
     */
    public Object get(int index) {
        Element element = first;

        for (int i = 0; i < index; i++) {
            element = element.getNext();
        }

        return element.getContent();
    }

    /**
     * Returns true if the list contains no elements.
     *
     * @return true if empty, false if not
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Removes the element at the specified position in the list.
     *
     * @param index specified position
     * @return removed object
     * @throws IndexOutOfBoundsException if array index out of bounds
     */
    public Object remove(int index) {
        Object removed = null;

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            removed = get(0);
            removeFirstElement();
        } else if (index == size - 1) {
            removed = get(size - 1);
            removeLastElement();
        } else {
            removed = get(index);
            removeMiddleElement(index);
        }

        return removed;
    }

    /**
     * Removes first element from ShoppingList.
     */
    public void removeFirstElement() {

        if (size == 1) {
            clear();
        } else {
            first = first.getNext();
            size--;
        }
    }

    /**
     * Removes last element from ShoppingList.
     */
    public void removeLastElement() {
        // Removing reference to last element from second to last element
        Element element = first;

        for (int i = 0; i < size - 2; i++) {
            element = element.getNext();
        }

        element.setNext(null);

        size--;
    }

    /**
     * Removes first element from ShoppingList.
     *
     * @param index index of element to remove
     */
    public void removeMiddleElement(int index) {

        // Iterates to element before removable element
        Element element = first;

        for (int i = 0; i < index - 1; i++) {
            element = element.getNext();
        }

        // Updates reference to the element before removable element
        Element next = element.getNext();
        element.setNext(next.getNext());

        size--;
    }

    /**
     * Removes the first occurrence of the specified element from the list.
     *
     * @param o object to remove
     * @return true if object removed, false if not
     */
    public boolean remove(Object o) {
        boolean objectRemoved = false;

        Element element = first;
        int index = -1;

        for (int i = 0; i < size; i++) {

            if (element.getContent() == o) {
                index = i;
                objectRemoved = true;
                break;
            }

            if (i < size) {
                element = element.getNext();
            }
        }

        remove(index);
        return objectRemoved;
    }

    /**
     * Returns size of the list.
     *
     * @return size of the list
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return string representation of the object
     */
    @Override
    public String toString() {
        Element element = first;
        String string = "";

        System.out.println("Your Shopping List now:");

        if (size > 0) {

            for (int i = 0; i < size; i++) {
                string = string + "  " + element.getContent() + "\n";

                if (i < size - 1) {
                    element = element.getNext();
                }
            }
        } else {
            System.out.println("   (empty)");
        }

        return string;
    }

    /**
     * Adds String[] array strings to ShoppingList as Shopping Items.
     *
     * @param array array to add
     */
    public void addArray(String[] array) {

        for (String string : array) {
            String[] words = string.split("\\s+", 2);
            try {
                int amount = Integer.parseInt(words[0]);
                add(new ShoppingItem(amount, words[1]));
            } catch (NumberFormatException e) {
                System.err.println("NumberFormatException: " + e.getMessage());
            }
        }
    }
}
