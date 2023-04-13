package cn.milesians.provider.lemon;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 * 乐檬开放平台Response
 * @author soliddq
 * @date 2022-06-14
 */
@Data
public class ApiCloudResponse<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int code;

    private String msg;

    private T result;

    private Object meta;

}
