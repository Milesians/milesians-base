package cn.milesians.provider.lemon;

import cn.milesians.provider.json.ObjectMapperFactory;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.TimeZone;

/**
 * feign 使用的序列化配置
 * @author soliddq
 * @date 2022-06-14
 */
public class LemonJacksonEncoder implements Encoder {

    private volatile static LemonJacksonEncoder SINGLETON;
    private final ObjectMapper mapper;

    private LemonJacksonEncoder() {
        ObjectMapper instance = ObjectMapperFactory.createInstance();
        instance.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        this.mapper = instance;
    }


    public static LemonJacksonEncoder getInstance() {
        if (null == SINGLETON) {
            synchronized (LemonJacksonDecoder.class) {
                if (null == SINGLETON) {
                    SINGLETON = new LemonJacksonEncoder();
                }
            }
        }
        return SINGLETON;

    }
    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) {
        try {
            JavaType javaType = mapper.getTypeFactory().constructType(bodyType);
            template.body(mapper.writerFor(javaType).writeValueAsString(object));
        } catch (JsonProcessingException e) {
            throw new EncodeException(e.getMessage(), e);
        }
    }
}