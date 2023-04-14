package cn.milesians.provider.lemon;

import cn.milesians.provider.commons.exception.ProviderException;

/**
 * @author milesians
 * @date 2023/4/14
 * @since 1.0
 */
public class LemonProviderException extends ProviderException {

    public LemonProviderException(String errorCode, String msg) {
        super(errorCode, msg);
    }

    public LemonProviderException(String msg) {
        super(msg);
    }
}
