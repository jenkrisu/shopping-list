package app.gui;

import javax.swing.table.DefaultTableModel;

/**
 * @author Jenni Unkuri jenni.unkuri@cs.tamk.fi
 * @version 2016.1218
 * @since 1.7
 */
public class ShoppingListTableModel extends DefaultTableModel {

    /**
     * Sets first column to be of integer class.
     * 
     * @param column column to check
     * @return class of column
     */
    @Override
    public Class getColumnClass(int column) {
        if (column == 0) {
            return Integer.class;
        } else {
            return String.class;
        }
    }
}
