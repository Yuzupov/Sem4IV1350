package se.alrh.iv1350.seminar3.view;

public class ErrorMessageHandler {


    static void errorMessageToUser(String message){

        String sb = java.time.LocalDate.now() +
                " | " +
                java.time.LocalTime.now() +
                " Error occurred " +
                message;
        System.out.println(sb);
    }
    public static StringBuilder errorMessageToDeveloper(){
        StringBuilder sb = new StringBuilder();
        sb.append(java.time.LocalDate.now());
        sb.append(" | ");
        sb.append(java.time.LocalTime.now());
        sb.append(" Error occurred ");
        return sb;
    }
}
