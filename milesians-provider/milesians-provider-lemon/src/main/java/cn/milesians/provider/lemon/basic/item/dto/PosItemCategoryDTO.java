package cn.milesians.provider.lemon.basic.item.dto;


import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * 乐檬商品档案类别
 * @author soliddq
 * @date 2022-06-14
 */
@Data
public class PosItemCategoryDTO implements Serializable {

    private static final long serialVersionUID = 6267455917664329932L;

    private String categoryCode;

    private String categoryName;

    private String categoryShortName;

    private String parentCategoryCode;

    private Boolean canNotSaleNoStoreItem;

    private Boolean notShowInPos;

    public static PosItemCategoryDTO getDTO(List<PosItemCategoryDTO> posItemCategoryDTOS, String posItemCategoryName) {
        for (PosItemCategoryDTO posItemCategoryDTO : posItemCategoryDTOS) {
            if (posItemCategoryName.equals(posItemCategoryDTO.getCategoryName())) {
                return posItemCategoryDTO;
            }
        }
        return null;
    }
}
