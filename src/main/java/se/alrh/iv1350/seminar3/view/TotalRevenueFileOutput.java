package se.alrh.iv1350.seminar3.view;

import se.alrh.iv1350.seminar3.model.SaleInfoDTO;
import se.alrh.iv1350.seminar3.model.SaleObserver;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TotalRevenueFileOutput implements SaleObserver {
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
    public void newSale(SaleInfoDTO saleInfoDTO) {
        double sumOfSale = saleInfoDTO.getTotalPrice()+ saleInfoDTO.getTotalVAT();
        this.totalRevenueFromSales += sumOfSale;
        this.revenueToFile.println("Total revenue from sales since start of program: "+this.totalRevenueFromSales);
    }
}
