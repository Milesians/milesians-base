package cn.milesians.provider.lemon.basic.item.dto;


import cn.milesians.provider.lemon.LemengBaseRequestDTO;
import lombok.Data;

/**
 * 乐檬商品档案查询
 *
 * @author soliddq
 * @date 2022-06-14
 */
@Data
public class PosItemCountDTO extends LemengBaseRequestDTO {

    private String keyword;

    private String lastDownloadTime;

    private String itemCategoryCode;

    private Integer manageTemplateNum;

}
