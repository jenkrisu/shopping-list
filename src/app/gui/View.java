package app.gui;

import java.awt.*;
import java.net.URL;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author Jenni Unkuri jenni.unkuri@cs.tamk.fi
 * @version 2016.1130
 * @since 1.8
 */
public class View extends JFrame {

    /**
     * JMenuItem openMenuItem which for opening a list.
     */
    private JMenuItem openMenuItem;

    /**
     * JMenuItem openMenuItem for saving a list.
     */
    private JMenuItem saveMenuItem;

    /**
     * JMenuItem combineMenuItem for combining a list.
     */
    private JMenuItem combineMenuItem;

    /**
     * JMenuItem which AppActionListener listens for removing selected rows.
     */
    private JMenuItem removeSelectedMenuItem;

    /**
     * JMenuItem which AppActionListener listens for removing all rows.
     */
    private JMenuItem removeAllRowsMenuItem;

    /**
     * Model for JTable.
     */
    private DefaultTableModel model;

    /**
     * JTable for showing data.
     */
    private JTable table;

    /**
     * Field for adding integer number amounts.
     */
    private JSpinner amountSpinner;

    /**
     * Field for adding string product name.
     */
    private JTextField productField;

    /**
     * JButton addButton which AppActionListener listens to for adding a row.
     */
    private JButton addButton;

    /**
     * Controller which handles adding and showing data.
     */
    private Controller controller;

    /**
     * AppActionListener which catches button and menu events.
     */
    private AppActionListener listener;

    /**
     * Creates new JFrame window.
     *
     * @param title title of the window
     * @param controller controller for view
     */
    public View(String title, Controller controller) {
        setTitle(title);
        setSize(400, 400);
        setMinimumSize(new Dimension(400, 300));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(View.EXIT_ON_CLOSE);
        this.controller = controller;
        listener = new AppActionListener(this);
    }

    /**
     * Adds components to View.
     */
    public void addComponents() {
        addMenuBar();
        addTable();
        addPanel();
        getRootPane().setDefaultButton(addButton);
        listener.drawTable();
    }

    /**
     * Adds icon to application.
     *
     * @param address address of icon
     */
    public void addIcon(String address) {
        URL imageURL = this.getClass().getResource(address);

        if (imageURL != null) {
            ImageIcon icon = new ImageIcon(imageURL);
            setIconImage(icon.getImage());
        }
    }

    /**
     * Adds menu to window.
     */
    public void addMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");

        openMenuItem = new JMenuItem("Open List...");
        openMenuItem.addActionListener(listener);
        saveMenuItem = new JMenuItem("Save As...");
        saveMenuItem.addActionListener(listener);
        combineMenuItem = new JMenuItem("Combine With...");
        combineMenuItem.addActionListener(listener);
        removeSelectedMenuItem = new JMenuItem("Remove Selected");
        removeSelectedMenuItem.addActionListener(listener);
        removeAllRowsMenuItem = new JMenuItem("Remove All Rows");
        removeAllRowsMenuItem.addActionListener(listener);

        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(combineMenuItem);
        editMenu.add(removeSelectedMenuItem);
        editMenu.add(removeAllRowsMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        add(menuBar, BorderLayout.PAGE_START);
    }

    /**
     * Adds a JTable that shows the Shopping List.
     */
    public void addTable() {
        model = new ShoppingListTableModel();

        // Adds names for columns and proportional column widths
        model.addColumn("Amount");
        model.addColumn("Product");
        model.addTableModelListener(new AppTableModelListener(this));

        table = new JTable(model);
        TableColumn colOne = table.getColumnModel().getColumn(0);
        TableColumn colTwo = table.getColumnModel().getColumn(1);
        table.getTableHeader().setReorderingAllowed(false);

        colOne.setPreferredWidth(50);
        colOne.setCellEditor(new PositiveIntegerCellEditor(new JTextField()));
        colTwo.setPreferredWidth(250);

        colOne.setResizable(false);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Adds input JSpinner, JTextField and JButton.
     */
    public void addPanel() {
        JPanel panel = new JPanel();

        JLabel amountLabel = new JLabel("Amount");
        amountSpinner = new JSpinner();
        JComponent editor = amountSpinner.getEditor();
        JFormattedTextField amountField
                = ((JSpinner.DefaultEditor) editor).getTextField();
        amountField.setColumns(3);

        JLabel productLabel = new JLabel("Product");
        productField = new JTextField(13);

        addButton = new JButton("Add");
        addButton.addActionListener(listener);

        panel.add(amountLabel);
        panel.add(amountSpinner);
        panel.add(productLabel);
        panel.add(productField);
        panel.add(addButton);

        add(panel, BorderLayout.PAGE_END);
    }

    /**
     * Gets menuItem openMenuItem.
     *
     * @return menuItem openMenuItem
     */
    public JMenuItem getOpenMenuItem() {
        return openMenuItem;
    }

    /**
     * Gets menuItem saveMenuItem.
     *
     * @return menuItem saveMenuItem
     */
    public JMenuItem getSaveMenuItem() {
        return saveMenuItem;
    }

    /**
     * Gets menuItem combineMenuItem.
     *
     * @return menuItem combineMenuItem
     */
    public JMenuItem getCombineMenuItem() {
        return combineMenuItem;
    }

    /**
     * Gets menuItem removeSelectedMenuItem.
     *
     * @return menuItem removeSelectedMenuItem
     */
    public JMenuItem getRemoveSelectedMenuItem() {
        return removeSelectedMenuItem;
    }

    /**
     * Gets menuItem removeAllRowsMenuItem.
     *
     * @return menuItem removeAllRowsMenuItem
     */
    public JMenuItem getRemoveAllRowsMenuItem() {
        return removeAllRowsMenuItem;
    }

    /**
     * Gets table model.
     *
     * @return model
     */
    public DefaultTableModel getModel() {
        return model;
    }

    /**
     * Gets amountSpinner.
     *
     * @return amountSpinner
     */
    public JSpinner getAmountSpinner() {
        return amountSpinner;
    }

    /**
     * Gets productField.
     *
     * @return productField
     */
    public JTextField getProductField() {
        return productField;
    }

    /**
     * Gets addButton.
     *
     * @return addButton
     */
    public JButton getAddButton() {
        return addButton;
    }

    /**
     * Gets controller.
     *
     * @return controller
     */
    public Controller getController() {
        return controller;
    }

    /**
     * Gets table.
     *
     * @return table
     */
    public JTable getTable() {
        return table;
    }
}
