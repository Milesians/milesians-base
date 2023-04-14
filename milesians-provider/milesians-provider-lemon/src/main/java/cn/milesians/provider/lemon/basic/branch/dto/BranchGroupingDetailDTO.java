package cn.milesians.provider.lemon.basic.branch.dto;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@Data
public class BranchGroupingDetailDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 481097727371213283L;

    private Integer branchNum;

    private String branchName;

}