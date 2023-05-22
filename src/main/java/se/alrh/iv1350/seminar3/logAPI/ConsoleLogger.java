package se.alrh.iv1350.seminar3.logAPI;

public class ConsoleLogger implements MessageLogger {

    @Override
    public void logMessage(String message){
        System.out.println(message);
    }
}
