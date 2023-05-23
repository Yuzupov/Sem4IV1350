package se.alrh.iv1350.seminar3.integration;

import java.io.IOException;
import java.lang.*;

public final class DatabaseConnectionErrorException extends Exception{
    public DatabaseConnectionErrorException(String msg) throws IOException {
        super(msg);

    }
}
