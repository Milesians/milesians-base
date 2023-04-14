package cn.milesians.provider.lemon.basic.item.dto;


import java.io.Serializable;
import lombok.Data;

@Data
public class PosItemLabelDTO implements Serializable {

    private Long itemLabelNum;

    private String itemLabelName;
}
