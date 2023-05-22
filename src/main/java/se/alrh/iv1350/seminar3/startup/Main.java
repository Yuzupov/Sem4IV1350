package se.alrh.iv1350.seminar3.startup;

import se.alrh.iv1350.seminar3.controller.Controller;
import se.alrh.iv1350.seminar3.controller.DatabaseConnectionErrorRePackagerException;
import se.alrh.iv1350.seminar3.controller.InvalidIDException;
import se.alrh.iv1350.seminar3.integration.*;
import se.alrh.iv1350.seminar3.view.View;
import se.alrh.iv1350.seminar3.logAPI.*;

import java.io.IOException;

/**
 * Starts the entire application, contains the main method
 */

public class Main {
    /**
     * the main method used to start the application
     *
     * @param args the application does not take any command line parameters
     */
    public static void main(String[] args) throws InvalidItemIDInDatabase, DatabaseConnectionErrorException, IOException, DatabaseConnectionErrorRePackagerException, InvalidIDException {
        /**
         * entry point, initializes the program
         */
        Controller contr = new Controller();
        View view = new View(contr);
        FileLogger output = new FileLogger();
        view.runFakeExecution();
    }
}

