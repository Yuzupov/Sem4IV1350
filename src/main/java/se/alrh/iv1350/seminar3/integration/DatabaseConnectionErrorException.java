package se.alrh.iv1350.seminar3.integration;

import se.alrh.iv1350.seminar3.logAPI.LogHandler;
import se.alrh.iv1350.seminar3.utility.LogType;
import se.alrh.iv1350.seminar3.view.ErrorMessageHandler;

import java.io.IOException;
import java.lang.*;

public final class DatabaseConnectionErrorException extends Exception{
    LogHandler logHandler = new LogHandler();
    public DatabaseConnectionErrorException() throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("Exception thrown");
        logHandler.loggingClassLog(ErrorMessageHandler.errorMessageToDeveloper(), LogType.DATABASECONNECTIONERROR);
    }
}
