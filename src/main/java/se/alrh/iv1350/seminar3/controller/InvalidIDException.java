package se.alrh.iv1350.seminar3.controller;

import java.lang.*;

public class InvalidIDException extends Exception {
    public InvalidIDException(int itemID, Exception e){
    }
    public String getMessage(){
        return ("Invalid item Identifier: ");
    }
}
