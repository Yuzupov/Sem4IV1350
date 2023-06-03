package se.alrh.iv1350.seminar3.view;

import java.lang.*;
import se.alrh.iv1350.seminar3.model.SaleInfoDTO;
import se.alrh.iv1350.seminar3.model.SaleObserver;

public abstract class Template implements SaleObserver {
    private double totalRevenueFromSales;
    @Override
    public void newSale(SaleInfoDTO saleInfoDTO) {
        calculateTotalRevenue(saleInfoDTO);
        showTotalIncome();
    }

    private void showTotalIncome(){
        try{
            doShowTotalIncome(totalRevenueFromSales);
        } catch (Exception e){
            handleExceptions(e);
        }
    }
    private void calculateTotalRevenue(SaleInfoDTO saleInfoDTO){
        double sumOfSale = saleInfoDTO.getTotalPrice()+ saleInfoDTO.getTotalVAT();
        this.totalRevenueFromSales += sumOfSale;
    }
    protected abstract void doShowTotalIncome(double revenue);
    abstract void handleExceptions(Exception e);
}
