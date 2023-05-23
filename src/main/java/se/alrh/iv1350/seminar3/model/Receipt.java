package se.alrh.iv1350.seminar3.model;

/**
 * models a receipt for a sale
 * is currently a wrapped SaleInfoDTO
 */
public class Receipt extends SaleInfoDTO{
    public Receipt(SaleInfoDTO saleInfoDTO){
        super(saleInfoDTO);
    }

}
