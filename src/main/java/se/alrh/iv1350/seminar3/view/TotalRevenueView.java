package se.alrh.iv1350.seminar3.view;

import se.alrh.iv1350.seminar3.model.SaleObserver;
import se.alrh.iv1350.seminar3.model.*;
public class TotalRevenueView implements SaleObserver {
    double totalRevenueFromSales;
    @Override
    public void newSale(SaleInfoDTO saleInfoDTO) {
        double sumOfSale = saleInfoDTO.getTotalPrice()+ saleInfoDTO.getTotalVAT();
        this.totalRevenueFromSales += sumOfSale;
        System.out.println("Total revenue of sale since start of program: "+this.totalRevenueFromSales);
    }
}




