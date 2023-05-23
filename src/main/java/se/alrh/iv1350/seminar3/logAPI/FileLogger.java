package se.alrh.iv1350.seminar3.logAPI;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public final class FileLogger implements MessageLogger {
    static PrintWriter output;
    public FileLogger() throws IOException {
        try {
            FileWriter file = new FileWriter("log.txt", true);
            output = new PrintWriter(file, true);
        } catch (IOException ioe){
            System.out.println("Cannot log.");
            ioe.printStackTrace();
        }
    }
    @Override
    public void logMessage(String message) {
        output.println(message);
    }
}

