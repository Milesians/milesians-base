package cn.milesians.provider.lemon;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LemengBasePageRequestDTO extends LemengBaseRequestDTO {


    private Integer pageSize;

    private Integer pageNo;

}
