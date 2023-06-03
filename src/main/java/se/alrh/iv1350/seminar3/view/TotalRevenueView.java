package se.alrh.iv1350.seminar3.view;

import se.alrh.iv1350.seminar3.model.SaleObserver;
import se.alrh.iv1350.seminar3.model.*;
public class TotalRevenueView extends Template{

    @Override
    protected void doShowTotalIncome(double revenue) {
        System.out.println("Total revenue of sale since start of program: "+ revenue);
    }

    @Override
    void handleExceptions(Exception e) {
        System.out.println("Some error occurred");
    }
}




