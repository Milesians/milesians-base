package cn.milesians.provider.lemon.basic.item.dto;


import cn.milesians.provider.lemon.LemengBaseRequestDTO;
import java.util.List;
import lombok.Data;

/**
 * 乐檬商品档案查询
 * @author soliddq
 * @date 2022-06-14
 */
@Data
public class PosItemListDTO extends LemengBaseRequestDTO {

    private List<Integer> itemNums;

    private List<String> itemCodes;

}
