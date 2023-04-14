package cn.milesians.provider.lemon.basic.item.dto;


import java.io.Serializable;
import lombok.Data;

/**
 * 乐檬商品档案图片
 * @author soliddq
 * @date 2022-06-14
 */
@Data
public class PosItemImageDTO implements Serializable {

    private static final long serialVersionUID = -5674931081000924965L;

    private String posImageId;

    private Integer itemNum;

    private Boolean posImageDefault;

    private String posImageCreationDate;

    private String posImageUrl;

}
