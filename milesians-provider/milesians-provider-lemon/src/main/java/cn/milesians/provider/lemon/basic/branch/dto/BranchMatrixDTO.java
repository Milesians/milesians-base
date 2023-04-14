package cn.milesians.provider.lemon.basic.branch.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

@Data
@ApiModel
public class BranchMatrixDTO implements Serializable {

    private static final long serialVersionUID = -8084553008278681704L;

    @ApiModelProperty(value = "扩展属性value1", example = "value1")
    private String branch_extend1;

    @ApiModelProperty(value = "扩展属性value2", example = "value2")
    private String branch_extend2;

    @ApiModelProperty(value = "扩展属性value3", example = "value3")
    private String branch_extend3;

    @ApiModelProperty(value = "扩展属性value4", example = "value4")
    private String branch_extend4;

    @ApiModelProperty(value = "扩展属性value5", example = "value5")
    private String branch_extend5;

    @ApiModelProperty(value = "扩展属性value6", example = "value6")
    private String branch_extend6;

    @ApiModelProperty(value = "扩展属性value7", example = "value7")
    private String branch_extend7;

    @ApiModelProperty(value = "扩展属性value8", example = "value8")
    private String branch_extend8;

    @ApiModelProperty(value = "扩展属性value9", example = "value9")
    private String branch_extend9;

    @ApiModelProperty(value = "扩展属性value10", example = "value10")
    private String branch_extend10;
}
