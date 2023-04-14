package cn.milesians.provider.lemon.basic.item.dto;


import cn.milesians.provider.lemon.LemengBaseRequestDTO;
import lombok.Data;

/**
 * 乐檬商品档案查询
 * @author soliddq
 * @date 2022-06-14
 */
@Data
public class PosItemFindAllDTO extends LemengBaseRequestDTO {

    private Boolean filterDelItem;
    private String lastDownloadTime;
}
