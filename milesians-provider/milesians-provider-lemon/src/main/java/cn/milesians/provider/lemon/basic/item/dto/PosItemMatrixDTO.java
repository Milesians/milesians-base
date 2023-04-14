package cn.milesians.provider.lemon.basic.item.dto;


import java.io.Serializable;
import lombok.Data;

/**
 * 乐檬商品档案扩展属性
 * @author soliddq
 * @date 2022-06-14
 */
@Data
public class PosItemMatrixDTO implements Serializable {

    private static final long serialVersionUID = -7919274840659657343L;

    private String itemExtend1;

    private String itemExtend2;

    private String itemExtend3;

    private String itemExtend4;

    private String itemExtend5;

    private String itemExtend6;
}