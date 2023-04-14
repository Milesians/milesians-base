package cn.milesians.provider.lemon.basic.branch.dto;


import cn.milesians.provider.lemon.LemengBaseRequestDTO;
import java.io.Serial;
import lombok.Data;

@Data
public class BranchRegionFindDTO extends LemengBaseRequestDTO {

    @Serial
    private static final long serialVersionUID = -5342918023334013824L;

    private String keyword;

    private Integer branchRegionNum;

}
