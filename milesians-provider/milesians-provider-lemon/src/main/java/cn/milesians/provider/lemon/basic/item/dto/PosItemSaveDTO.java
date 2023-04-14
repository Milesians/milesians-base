package cn.milesians.provider.lemon.basic.item.dto;


import cn.milesians.provider.lemon.LemengBaseRequestDTO;
import java.io.Serial;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

/**
 * 乐檬商品档案保存
 * @author soliddq
 * @date 2022-06-14
 */
@Data
public class PosItemSaveDTO extends LemengBaseRequestDTO {

    @Serial
    private static final long serialVersionUID = 251179544798563334L;

    private String itemName;

    private String itemSpec;

    private String itemCode;

    private String itemBarcode;

    private String itemMethod;

    private String storeItemPinyin;

    private Integer itemType;

    private String itemUnit;

    private String itemCategory;

    private BigDecimal itemMinPrice;

    private BigDecimal itemMaxPrice;

    private String itemCategoryCode;

    private String itemDepartment;

    private Boolean itemPosChangePriceFlag;

    private Boolean itemSaleCeaseFlag;

    private Boolean itemStockCeaseFlag;

    private Boolean itemPointActived;

    private BigDecimal itemPointValue;

    private String itemBrand;

    private String itemPurchaseScope;

    private String itemCostMode;

    private Integer itemValidPeriod;

    private BigDecimal itemCostPrice;

    private BigDecimal itemTransferPrice;

    private String itemAssistUnit;

    private BigDecimal itemAssistRate;

    private Integer itemStatus;

    private Boolean itemDiscounted;

    private String itemPlace;

    private Integer itemRemindPeriod;

    private String itemStorePlace;

    private BigDecimal itemInTax;

    private BigDecimal itemOutTax;

    private Boolean itemWeightFlag;

    private List<String> itemLabelNames;

    private BigDecimal itemRegularPrice;

    private BigDecimal itemLevel2Price;

    private BigDecimal itemLevel3Price;

    private BigDecimal itemLevel4Price;

    private BigDecimal itemWholesalePrice;

    private BigDecimal itemLevel2Wholesale;

    private BigDecimal itemLevel3Wholesale;

    private BigDecimal itemLevel4Wholesale;

    private String itemPurchaseUnit;

    private BigDecimal itemPurchaseRate;

    private String itemInventoryUnit;

    private BigDecimal itemInventoryRate;

    private String itemTransferUnit;

    private BigDecimal itemTransferRate;

    private String itemWholesaleUnit;

    private BigDecimal itemWholesaleRate;

    private Integer itemPriceTagFlag;

    private Integer itemPrintLabelFlag;

    private Integer itemWholesaleFlag;

    private Integer itemHasMatrix;

    private String appUserName;

    private Integer itemPriceAdj;

    private Boolean itemSaleNoStore;

    private String itemTaxCode;
}
