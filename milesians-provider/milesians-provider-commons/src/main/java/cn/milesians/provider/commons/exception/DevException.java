package cn.milesians.provider.commons.exception;

import lombok.Getter;

/**
 * 开发调用逻辑错误
 *
 * @author milesians
 * @date 2023/4/13
 * @since 1.0
 */
@Getter
public class DevException extends RuntimeException {

    private final String errorCode;

    private final String msg;

    public DevException(String errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
        this.msg = msg;
    }

    public DevException(String msg) {
        super(msg);
        this.errorCode = "1";
        this.msg = msg;
    }
}
