package cn.milesians.provider.lemon.basic.item.dto;

import cn.milesians.provider.lemon.LemengBaseRequestDTO;
import java.io.Serial;
import lombok.Data;

@Data
public class PosItemImageDetailSaveDTO extends LemengBaseRequestDTO {

    @Serial
    private static final long serialVersionUID = 7497016011959177605L;

    private byte[] posImageResource;

    private Boolean posImageDefault;

}