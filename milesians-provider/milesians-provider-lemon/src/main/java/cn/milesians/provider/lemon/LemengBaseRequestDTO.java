package cn.milesians.provider.lemon;

import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LemengBaseRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 3353645452820898302L;

    private String systemBookCode;

}
