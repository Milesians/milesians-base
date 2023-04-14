package cn.milesians.provider.lemon.basic.branch.dto;


import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class BranchGroupingDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 481097727371213283L;

    private String branchGroupingNum;

    private String branchGroupingCode;

    private String branchGroupingName;

    private String branchGroupingMemo;

    private List<BranchGroupingDetailDTO> branchGroupingDetails = new ArrayList<>();

}