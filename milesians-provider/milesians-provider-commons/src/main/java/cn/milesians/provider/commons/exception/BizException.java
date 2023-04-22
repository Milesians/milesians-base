package cn.milesians.provider.commons.exception;

import lombok.Getter;

/**
 * 业务异常
 *
 * @author milesians
 * @date 2023/4/13
 * @since 1.0
 */
@Getter
public class BizException extends RuntimeException {

    private final String errorCode;

    private final String msg;

    public BizException(String errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
        this.msg = msg;
    }

    public BizException(String msg) {
        super(msg);
        this.errorCode = "1";
        this.msg = msg;
    }
}
