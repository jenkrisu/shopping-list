package app.list;

/**
 * @author Jenni Unkuri jenni.unkuri@cs.tamk.fi
 * @version 2016.1130
 * @since 1.7
 */
public class ShoppingItem {

    /**
     * Amount of product.
     */
    private int amount;

    /**
     * Product to be purchased.
     */
    private String product;

    /**
     * Creates ShoppingItem.
     *
     * @param amount amount of product
     * @param product product to be purchased
     */
    public ShoppingItem(int amount, String product) {
        this.amount = amount;
        this.product = product;
    }

    /**
     * Gets amount of product.
     *
     * @return amount of product
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets amount of product.
     *
     * @param amount amount of product
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Gets name of product.
     *
     * @return name of product
     */
    public String getProduct() {
        return product;
    }

    /**
     * Sets name of product.
     *
     * @param product name of product
     */
    public void setProduct(String product) {
        this.product = product;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return this.amount + " " + this.product;
    }
}
