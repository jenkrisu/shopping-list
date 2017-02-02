package app.list;

/**
 * Contains content and references to other Elements in MyLinkedList object.
 *
 * @author Jenni Unkuri jenni.unkuri@cs.tamk.fi
 * @version 2016.1126
 * @since 1.7
 *
 * @param <T> the type of the value being added
 */
public class Element<T> {

    /**
     * Content of the Element.
     */
    private T content;

    /**
     * Reference to the next Element in MyLinkedList.
     */
    private Element<T> next;

    /**
     * Returns content of the Element.
     *
     * @return content of the Element
     */
    public T getContent() {
        return content;
    }

    /**
     * Sets content of the Element.
     *
     * @param content content of the Element
     */
    public void setContent(T content) {
        this.content = content;
    }

    /**
     * Returns the next Element.
     *
     * @return next Element
     */
    public Element getNext() {
        return next;
    }

    /**
     * Sets the Element.
     *
     * @param next Element to set
     */
    public void setNext(Element next) {
        this.next = next;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return string representation of the object
     */
    @Override
    public String toString() {
        return "" + content;
    }
}
