package cn.milesians.provider.lemon.basic.branch.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class BranchDTO implements Serializable {

    private Integer branchNum;

    private Boolean branchActived;

    private Integer branchRegionNum;

    private String branchRegionName;

    private String branchCode;

    private String branchName;

    private String branchAddr;

    private String branchPhone;

    private String branchType;

    private Boolean newBranch;

    private String branchEmail;

    private Integer branchStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date branchCreateTime;

    private String branchMemo;

    private String branchLinkMan;

    private Boolean branchRdc;

    private Integer managementTemplateNum;

    private BranchMatrixDTO branchMatrix;
    public static BranchDTO getBranchDTO(List<BranchDTO> branchDTOS, Integer branchNum) {
        for (BranchDTO branchDTO : branchDTOS) {
            if (branchDTO.getBranchNum().equals(branchNum)) {
                return branchDTO;
            }
        }
        return null;
    }
}
