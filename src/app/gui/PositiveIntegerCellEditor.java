package app.gui;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 * @author Jenni Unkuri jenni.unkuri@cs.tamk.fi
 * @version 2016.1218
 * @since 1.7
 */
public class PositiveIntegerCellEditor extends DefaultCellEditor {

    /**
     * JTextField for validating input.
     */
    private JTextField textField;

    /**
     * Creates editor with parameter textField.
     * 
     * @param textField textField to edit
     */
    public PositiveIntegerCellEditor(JTextField textField) {
        super(textField);
        this.textField = textField;
        this.textField.setHorizontalAlignment(JTextField.RIGHT);
    }

    /**
     * Forwards message to prevent editing cell if input is invalid.
     * 
     * @return true if valid input, false if invalid input
     */
    @Override
    public boolean stopCellEditing() {
        try {
            int value = Integer.valueOf(textField.getText());
            
            if (value <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            Border redline = BorderFactory.createLineBorder(Color.RED);
            textField.setBorder(redline);
            return false;
        }
        
        return super.stopCellEditing();
    }

    /**
     * Sets border of cell back to black when valid input is given.
     * 
     * @param table table to edit
     * @param value value of cell
     * @param isSelected whether cell is selected
     * @param row row that is selected
     * @param column column that is selected
     * @return component
     */
    @Override
    public Component getTableCellEditorComponent(JTable table,
            Object value, boolean isSelected, int row, int column) {
        Border blackline = BorderFactory.createLineBorder(Color.BLACK);
        textField.setBorder(blackline);
        return super.getTableCellEditorComponent(
                table, value, isSelected, row, column);
    }
}
