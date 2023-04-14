package cn.milesians.provider.lemon.basic.branch.dto;

import cn.milesians.provider.lemon.LemengBaseRequestDTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.List;

import lombok.Data;

/**
 * @author nhsoft.yex
 * @description 开放平台v2版本
 */
@Data
public class BranchListV2DTO extends LemengBaseRequestDTO {

    private Boolean isopen;


    private String timeFrom;


    private String timeTo;

    private String branchLabel;

    private List<Integer> branchNums;

    @NotNull
    @Min(1)
    private Integer pageNo;

    @NotNull
    @Min(1)
    @Max(1000)
    private Integer pageSize;

    private Integer offset;

    private Integer limit;

    public void setOffLimit() {
        this.limit = this.pageSize;
        if (this.pageNo <= 1) {
            this.offset = 0;
        } else {
            this.offset = (this.pageNo - 1) * this.pageSize;
        }
    }
}
