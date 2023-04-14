package cn.milesians.provider.lemon.basic.item.dto;


import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 乐檬商品档案一品多码
 * @author soliddq
 * @date 2022-06-14
 */

@Data
public class PosItemBarDTO implements Serializable {

    private static final long serialVersionUID = 422945597434614222L;

    private String itemBarCode;

    private BigDecimal itemBarRate;
}
