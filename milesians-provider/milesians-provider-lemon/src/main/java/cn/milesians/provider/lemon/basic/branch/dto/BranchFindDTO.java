package cn.milesians.provider.lemon.basic.branch.dto;


import cn.milesians.provider.lemon.LemengBaseRequestDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchFindDTO extends LemengBaseRequestDTO {


    private String keyword;

    private List<Integer> branchNums;

    private List<Integer> regionNums;

}


