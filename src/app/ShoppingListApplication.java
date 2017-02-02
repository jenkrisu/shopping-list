package app;

import app.gui.Controller;
import app.gui.View;
import app.list.ShoppingItem;
import app.list.ShoppingList;
import java.util.Scanner;

/**
 * @author Jenni Unkuri jenni.unkuri@cs.tamk.fi
 * @version 2016.1218
 * @since 1.8
 */
public class ShoppingListApplication {
    
    /**
     * Starts the Shopping List application.
     */
    public void start() {
        createAndRunGUI();
        runCommandLineInterface();
    }
    
    /**
     * Creates and runs graphical user interface.
     */
    public void createAndRunGUI() {
        ShoppingList<ShoppingItem> list = new ShoppingList<>();
        Controller controller = new Controller(list);
        View gui = new View("Shopping List", controller);
        gui.addIcon("/images/icon.png");
        gui.addComponents();
        gui.setVisible(true);
    }

    /**
     * Runs the command line version of application.
     */
    public void runCommandLineInterface() {
        System.out.println("SHOPPING LIST\n"
                + "Tampere University of Applied Sciences");

        String input = "";
        Scanner scanner = new Scanner(System.in);
        InputParser parser = new InputParser();
        ShoppingList<ShoppingItem> list = new ShoppingList<>();

        while (true) {

            input = askInput(scanner);

            if (input.equals("exit")) {
                break;
            }

            String[] array = parser.inputToArray(input, ';');

            if (parser.firstIsInteger(array)) {
                list.addArray(array);
            }

            System.out.println(list);
        }
    }

    /**
     * Asks user for input.
     *
     * @param scanner scanner that scans input
     * @return input
     */
    public String askInput(Scanner scanner) {
        System.out.println("Give shopping list "
                + "(example: 1 milk;2 tomato;3 carrot;)");

        return scanner.nextLine();
    }
}
