package app.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * @author Jenni Unkuri jenni.unkuri@cs.tamk.fi
 * @version 2016.1218
 * @since 1.7
 */
public class AppActionListener implements ActionListener {

    /**
     * View object to listen to.
     */
    private View view;

    /**
     * JFileChooser for actions.
     */
    private JFileChooser fileChooser;

    /**
     * Creates new Listener.
     *
     * @param view View object to listen to
     */
    public AppActionListener(View view) {
        this.view = view;
        fileChooser = new JFileChooser();
    }

    /**
     * Catches action events and modifies table or file accordingly.
     *
     * @param event event to catch
     */
    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == view.getAddButton()) {
            if (inputIsValid()) {
                addRowToTable();
            }
        } else if (event.getSource() == view.getRemoveSelectedMenuItem()) {
            removeSelectedRows();
        } else if (event.getSource() == view.getRemoveAllRowsMenuItem()) {
            removeAllRows();
        } else if (event.getSource() == view.getSaveMenuItem()) {
            writeTableToFile();
        } else if (event.getSource() == view.getOpenMenuItem()) {
            writeTableFromFile();
        } else if (event.getSource() == view.getCombineMenuItem()) {
            combineTableFromFile();
        }
    }

    /**
     * Checks that user input in fields is correct.
     *
     * @return true if correct, false if not
     */
    public boolean inputIsValid() {
        boolean isValid = false;

        int amount = (Integer) view.getAmountSpinner().getValue();
        String product = view.getProductField().getText();

        if (amount == 0) {
            amountZeroError();
        } else if (product.equals("")) {
            productError();
        } else if (!view.getController().amountIsValid(amount, product)) {
            amountNegativeError();
        } else {
            isValid = true;
        }

        return isValid;
    }

    /**
     * Creates JOptionPane error message with amount negative error message.
     */
    public void amountNegativeError() {
        JOptionPane.showMessageDialog(view,
                "Amount cannot be negative.",
                "Amount Error",
                JOptionPane.ERROR_MESSAGE);
        view.getAmountSpinner().setValue(0);
    }

    /**
     * Creates JOptionPane error message with input product error message.
     */
    public void productError() {
        JOptionPane.showMessageDialog(view,
                "Product field cannot be empty.",
                "Product Error",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Creates JOptionPane error message with amount zero error message.
     */
    public void amountZeroError() {
        JOptionPane.showMessageDialog(view,
                "Amount cannot be zero.",
                "Amount Error",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Checks that input is valid, adds to ShoppingList and refreshes table.
     */
    public void addRowToTable() {
        int amount = (Integer) view.getAmountSpinner().getValue();
        String product = view.getProductField().getText();

        updateTable(amount, product);
        view.getProductField().setText("");
        view.getAmountSpinner().setValue(0);
    }

    /**
     * Adds content to ShoppingList and refreshes table.
     *
     * @param amount amount of item to add
     * @param product product to add
     */
    public void updateTable(int amount, String product) {
        view.getController().addToList(amount, product);
        drawTable();
    }

    /**
     * Adds content from ShoppingList to table.
     */
    public void drawTable() {
        Object[][] object = view.getController().getArray();

        view.getModel().setRowCount(0);

        for (Object[] array : object) {
            view.getModel().addRow(array);
        }
    }

    /**
     * Removes selected rows from ShoppingList and refreshes table.
     */
    public void removeSelectedRows() {
        int[] indeces = view.getTable().getSelectedRows();
        view.getController().remove(indeces);
        drawTable();
    }

    /**
     * Removes all rows from ShoppingList and refreshes table.
     */
    public void removeAllRows() {
        view.getController().clear();
        view.getModel().setRowCount(0);
    }

    /**
     * Writes table to file.
     */
    public void writeTableToFile() {
        int action = fileChooser.showSaveDialog(view);

        if (action == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try (BufferedWriter writer
                    = new BufferedWriter(new FileWriter(file))) {

                int rows = view.getTable().getRowCount();
                int columns = view.getTable().getColumnCount();

                // Prints column names
                for (int i = 0; i < columns; i++) {
                    writer.write(view.getTable().getColumnName(i));

                    if (i + 1 < columns) {
                        writer.write("\t");
                    }
                }

                writer.newLine();

                // Prints rows
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        writer.write(view.getTable().getValueAt(i, j).
                                toString());

                        if (j + 1 < columns) {
                            writer.write("\t");
                        }
                    }

                    if (i + 1 < rows) {
                        writer.newLine();
                    }
                }
            } catch (IOException e) {
                System.err.println("IOException: " + e.getMessage());
            }
        }
    }

    /**
     * Adds rows from file to Shopping List.
     */
    public void addRowsFromFile() {

        int action = fileChooser.showOpenDialog(view);

        if (action == JFileChooser.APPROVE_OPTION) {

            File file = fileChooser.getSelectedFile();

            try (BufferedReader reader
                    = new BufferedReader(new FileReader(file))) {

                String line = null;

                while ((line = reader.readLine()) != null) {

                    if (view.getController().firstIsInteger(line)) {

                        line = line.trim();
                        line = line.replaceAll("(\\s){2,}", " ");
                        String[] array = new String[]{line};
                        view.getController().addToList(array);
                    }
                }
            } catch (IOException e) {
                System.err.println("IOException: " + e.getMessage());
            }
        }
    }

    /**
     * Combines existing JTable view with list from file.
     */
    public void combineTableFromFile() {
        addRowsFromFile();
        drawTable();
    }

    /**
     * Writes file contents to ShoppingList and refreshes table.
     */
    public void writeTableFromFile() {
        removeAllRows();
        addRowsFromFile();
        drawTable();
    }
}
