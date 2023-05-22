package se.alrh.iv1350.seminar3.logAPI;

import com.sun.istack.internal.NotNull;
import se.alrh.iv1350.seminar3.utility.LogType;
import java.io.IOException;

public class LogHandler {
    FileLogger fileLogger = new FileLogger();
    ConsoleLogger consoleLogger = new ConsoleLogger();
    public LogHandler() throws IOException {
    }
    @NotNull
    public void loggingClassLog(StringBuilder message, LogType type) {
        switch (type) {
            case INVALIDITEMIDERROR:
                consoleLogger.logMessage(String.valueOf(message));
                break;
            case DATABASECONNECTIONERROR:
                fileLogger.logMessage(message.toString()+" Unable to establish connection to inventory database");
                break;
        }
    }
}

