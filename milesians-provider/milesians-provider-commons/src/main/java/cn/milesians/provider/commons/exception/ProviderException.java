package cn.milesians.provider.commons.exception;

import lombok.Getter;

/**
 * @author milesians
 * @date 2023/4/13
 * @since 1.0
 */
@Getter
public class ProviderException extends RuntimeException {

    private final String errorCode;

    private final String msg;

    public ProviderException(String errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
        this.msg = msg;
    }

    public ProviderException(String msg) {
        super(msg);
        this.errorCode = "1";
        this.msg = msg;
    }
}
