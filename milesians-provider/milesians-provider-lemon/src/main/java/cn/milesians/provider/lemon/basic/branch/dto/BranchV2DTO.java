package cn.milesians.provider.lemon.basic.branch.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class BranchV2DTO implements Serializable {

    @ApiModelProperty(value = "门店编码", position = 1)
    private Integer branchNum;

    @ApiModelProperty(value = "门店是否启用", position = 2)
    private Boolean branchActived;

    @ApiModelProperty(value = "门店区域编码", position = 3)
    private Integer branchRegionNum;

    @ApiModelProperty(value = "门店区域", position = 4)
    private String branchRegionName;

    @ApiModelProperty(value = "门店代码", position = 5)
    private String branchCode;

    @ApiModelProperty(value = "门店名称", position = 6)
    private String branchName;

    @ApiModelProperty(value = "门店地址", position = 7)
    private String branchAddr;

    @ApiModelProperty(value = "电话", position = 8)
    private String branchPhone;

    @ApiModelProperty(value = "门店类型", position = 9)
    private String branchType;

    @ApiModelProperty(value = "是否新店", position = 10)
    private Boolean newBranch;

    @ApiModelProperty(value = "邮箱", position = 11)
    private String branchEmail;

    @ApiModelProperty(value = "状态", position = 12)
    private Integer branchStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间", position = 13)
    private Date branchCreateTime;

    @ApiModelProperty(value = "备注", position = 14)
    private String branchMemo;

    @ApiModelProperty(value = "联系人", position = 15)
    private String branchLinkMan;

    @ApiModelProperty(value = "是否配送中心", position = 16)
    private Boolean branchRdc;

    @ApiModelProperty(value = "管理范围模板", position = 17)
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
