package cn.milesians.provider.lemon;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LemengBaseRequestDTO implements Serializable {

    private static final long serialVersionUID = 3353645452820898302L;

    @ApiModelProperty(value = "账套号", hidden = true)
    private String systemBookCode;

}
