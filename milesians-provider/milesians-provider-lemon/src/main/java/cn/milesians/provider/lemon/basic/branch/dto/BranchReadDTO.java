package cn.milesians.provider.lemon.basic.branch.dto;


import cn.milesians.provider.lemon.LemengBaseRequestDTO;
import lombok.Data;

@Data
public class BranchReadDTO extends LemengBaseRequestDTO {

    private Integer branchNum;
}
