package cn.milesians.provider.lemon.basic.item.dto;


import cn.milesians.provider.lemon.LemengBaseRequestDTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 乐檬商品档案查询
 *
 * @author soliddq
 * @date 2022-06-14
 */
@Data
public class PosItemPageDTO extends LemengBaseRequestDTO {

    private String keyword;

    private String lastDownloadTime;

    private String itemCategoryCode;

    private Integer manageTemplateNum;

    @NotNull
    @Min(1)
    private Integer pageNo;

    @NotNull
    @Max(100)
    private Integer pageSize;
}
