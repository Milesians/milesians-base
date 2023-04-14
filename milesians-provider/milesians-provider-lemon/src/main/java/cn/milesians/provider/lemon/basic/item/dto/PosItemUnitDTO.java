package cn.milesians.provider.lemon.basic.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PosItemUnitDTO
 *
 * @author milesians
 * @date 2022/12/19
 **/
@NoArgsConstructor
@Data
public class PosItemUnitDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("group_name")
    private String groupName;

    @JsonProperty("item_units")
    private List<ItemUnitsDTO> itemUnits;

    @NoArgsConstructor
    @Data
    public static class ItemUnitsDTO {

        @JsonProperty("item_unit_code")
        private String itemUnitCode;

        @JsonProperty("item_unit_name")
        private String itemUnitName;

        @JsonProperty("item_unit_rate")
        private BigDecimal itemUnitRate;

        @JsonProperty("item_unit_type")
        private String itemUnitType;
    }
}
