package cn.milesians.provider.lemon.basic.branch.dto;


import cn.milesians.provider.lemon.LemengBaseRequestDTO;
import lombok.Data;

@Data
public class BranchGroupingFindDTO extends LemengBaseRequestDTO {

    private static final long serialVersionUID = 481097727371213283L;

    private String keyword;

}