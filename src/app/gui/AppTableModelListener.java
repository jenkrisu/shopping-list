package app.gui;

import javax.swing.event.TableModelEvent;
import static javax.swing.event.TableModelEvent.UPDATE;
import javax.swing.event.TableModelListener;

/**
 * @author Jenni Unkuri jenni.unkuri@cs.tamk.fi
 * @version 2016.1218
 * @since 1.7
 */
public class AppTableModelListener implements TableModelListener {

    /**
     * View object to listen to.
     */
    private View view;

    /**
     * Creates Listener with parameter View.
     * 
     * @param view View object of the listener
     */
    public AppTableModelListener(View view) {
        this.view = view;
    }

    /**
     * Listens for update changes in JTable.
     * 
     * @param event event to listen to 
     */
    @Override
    public void tableChanged(TableModelEvent event) {
        if (event.getType() == UPDATE) {
            int row = event.getFirstRow();
            String amount = (String) view.getTable().getValueAt(row, 0);
            String product = (String) view.getTable().getValueAt(row, 1);
            view.getController().updateIndex(row,
                    Integer.parseInt(amount), product);
        }
    }
}
