package cn.milesians.provider.commons;


import lombok.Getter;

@Getter
public class Response<T> {

    private final int code;

    private final String msg;

    private final T result;


    public Response(final Integer code, final T result, final String msg) {
        this.code = code;
        this.result = result;
        this.msg = msg;
    }

    /**
     * 成功.
     *
     * @param <T> T
     * @return 包装后的响应参数
     */
    public static <T> Response<T> success() {
        return success(null);
    }

    /**
     * 成功.
     *
     * @param data 响应参数
     * @param <T>  T
     * @return 包装后的响应参数
     */
    public static <T> Response<T> success(final T data) {
        return new Response<T>(0, data, "Success.");
    }


    /**
     * 错误.
     *
     * @param code    code
     * @param message msg
     * @param <T>     t
     * @return 包装后的响应参数
     */
    public static <T> Response<T> error(final int code, final String message) {
        return new Response<T>(code, null, message);
    }


    /**
     * 错误.
     *
     * @param code    code
     * @param message msg
     * @param <T>     t
     * @return 包装后的响应参数
     */
    public static <T> Response<T> error(final int code, final T t, final String message) {
        return new Response<T>(code, t, message);
    }
}
