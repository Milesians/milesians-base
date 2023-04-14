package cn.milesians.provider.lemon.basic.item.dto;


import cn.milesians.provider.lemon.LemengBaseRequestDTO;
import java.io.Serial;
import java.util.List;
import lombok.Data;

@Data
public class PosItemImageSaveDTO extends LemengBaseRequestDTO {

    @Serial
    private static final long serialVersionUID = 7497016011959177605L;

    private Integer itemNum;

    private List<PosItemImageDetailSaveDTO> posImages;
}
