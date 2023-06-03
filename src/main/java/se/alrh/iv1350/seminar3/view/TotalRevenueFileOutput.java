package se.alrh.iv1350.seminar3.view;

import se.alrh.iv1350.seminar3.model.SaleInfoDTO;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TotalRevenueFileOutput extends Template{
    private double totalRevenueFromSales;
    PrintWriter revenueToFile;
    public TotalRevenueFileOutput() throws IOException {
        try {
            FileWriter file = new FileWriter("totalrevenue.txt", true);
            revenueToFile = new PrintWriter(file, true);
        } catch (IOException ioe) {
            System.out.println("Cannot print to file.");
            ioe.printStackTrace();
        }
    }
    @Override
    protected void doShowTotalIncome(double revenue) {
        this.revenueToFile.println("Total revenue from sales since start of program: " + revenue);
    }
    @Override
    void handleExceptions(Exception e) {
        System.out.println("Some error occurred");
    }
}
