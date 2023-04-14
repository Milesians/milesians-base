package cn.milesians.provider.lemon.basic.item.dto;


import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class StoreItemSupplierDTO implements Serializable {
    private Integer supplierNum;
    private Integer itemNum;
    private Integer branchNum;

    private Integer storeItemSupplierPri;
    private BigDecimal storeItemSupplierMin;
    private BigDecimal storeItemSupplierCost;
    private BigDecimal storeItemSupplierMaxPrice;
    private BigDecimal storeItemSupplierMinPrice;
    private BigDecimal storeItemSupplierLastestPrice;
    private Boolean storeItemSupplierDefault;
    private BigDecimal storeItemSupplierTax;
    private String managementStyleType;
    private BigDecimal grossRate;//扣率
    private BigDecimal specialGrossRate; //特价扣率
    private String supplierAgreementNo;
    private Boolean storeItemSupplierDelTag =false;
    private Integer storeItemSupplierPurchasePeriod;


}
