package cn.milesians.provider.lemon.basic.branch.dto;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class BranchRegionDTO implements Serializable {

    private static final long serialVersionUID = -5342918023334013824L;

    private Integer branchRegionNum;

    private String branchRegionCode;

    private String branchRegionName;

    private Integer parentRegionNum;

    public static BranchRegionDTO getDTO(List<BranchRegionDTO> branchRegionDTOS, Integer branchRegionNum) {

        for (BranchRegionDTO branchRegionDTO : branchRegionDTOS) {
            if (branchRegionNum.equals(branchRegionDTO.getBranchRegionNum())) {
                return branchRegionDTO;
            }
        }
        return null;
    }
}
