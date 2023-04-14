package cn.milesians.provider.lemon.basic.item.dto;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 * 乐檬商品档案
 * @author soliddq
 * @date 2022-06-14
 */
@Data
public class PosItemDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -8245264979153093214L;

    private Integer itemNum;

    private String itemCode;

    private String itemName;

    private String itemBarcode;

    private String itemSpec;

    private Integer itemType;

    private String itemBrand;

    private List<String> itemLabelNames;

    private String itemUnit;

    private String itemCategory;

    private String itemCategoryCode;

    private String storeItemPinyin;

    private String itemDepartment;

    private Integer itemValidPeriod;

    private String itemCostMode;

    private String itemPlace;

    private String itemMethod;

    private String itemPurchaseScope;

    private String itemNoteInfo;

    private String itemStorePlace;

    private BigDecimal itemOutTax;

    private BigDecimal itemInTax;

    private Integer itemStatus;

    private Integer itemDelTag;

    private BigDecimal itemRegularPrice;

    private BigDecimal itemLevel2Price;

    private BigDecimal itemCostPrice;

    private BigDecimal itemTransferPrice;

    private BigDecimal itemWholesalePrice;

    private BigDecimal itemLevel2Wholesale;

    private String itemPurchaseUnit;

    private BigDecimal itemPurchaseRate;

    private String itemInventoryUnit;

    private BigDecimal itemInventoryRate;

    private String itemTransferUnit;

    private BigDecimal itemTransferRate;

    private String itemWholesaleUnit;

    private BigDecimal itemWholesaleRate;

    private String itemAssistUnit;

    private BigDecimal itemAssistRate;

    private Boolean itemWeightFlag;

    private Boolean itemStockCeaseFlag;

    private Boolean itemSaleCeaseFlag;

    private Integer itemEliminativeFlag;

    private Boolean itemPosChangePriceFlag;

    private Boolean itemPointActived;

    private Integer itemWholesaleFlag;

    private Integer itemPriceTagFlag;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date itemCreateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date itemLastEditTime;

    private PosItemMatrixDTO posItemMatrix;

    private PosItemBarDTO posItemBar;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date newItemDeadline;

    public static PosItemDTO getPosItemDTO(List<PosItemDTO> posItemDTOS, Integer itemNum) {
        for (PosItemDTO posItemDTO : posItemDTOS) {
            if (posItemDTO.getItemNum().equals(itemNum)) {
                return posItemDTO;
            }
        }
        return null;
    }

    public static PosItemDTO getPosItemDTOByBarcode(List<PosItemDTO> posItemDTOS, String itemBarcode) {
        for (PosItemDTO posItemDTO : posItemDTOS) {
            if (StrUtil.isEmpty(posItemDTO.getItemBarcode())) {
                continue;
            }

            if (posItemDTO.getItemBarcode().equals(itemBarcode)) {
                return posItemDTO;
            }
        }
        return null;
    }

    public static PosItemDTO getPosItemDTOByItemCode(List<PosItemDTO> posItemDTOS, String itemCode) {
        for (PosItemDTO posItemDTO : posItemDTOS) {
            if (posItemDTO.getItemCode().equals(itemCode)) {
                return posItemDTO;
            }
        }
        return null;
    }


}
