package se.alrh.iv1350.seminar3.model;

import se.alrh.iv1350.seminar3.model.SaleInfoDTO;

public interface SaleObserver {
    void newSale(SaleInfoDTO saleInfoDTO);
}
