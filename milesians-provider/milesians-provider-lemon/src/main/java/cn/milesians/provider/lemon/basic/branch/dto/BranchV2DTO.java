package cn.milesians.provider.lemon.basic.branch.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class BranchV2DTO implements Serializable {

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

    /**
     * 门店扩展属性
     */
    private Map<String, String> branchMatrix;

    public static BranchV2DTO getBranchDTO(List<BranchV2DTO> branchDTOS, Integer branchNum) {
        for (BranchV2DTO branchDTO : branchDTOS) {
            if (branchDTO.getBranchNum().equals(branchNum)) {
                return branchDTO;
            }
        }
        return null;
    }
}
