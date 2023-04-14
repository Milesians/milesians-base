package cn.milesians.provider.lemon.basic.item.dto;

import cn.milesians.provider.lemon.LemengBaseRequestDTO;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@AllArgsConstructor
@NoArgsConstructor
public class StoreItemSupplierFindDTO extends LemengBaseRequestDTO implements Serializable {
    private Integer branchNum;

}
