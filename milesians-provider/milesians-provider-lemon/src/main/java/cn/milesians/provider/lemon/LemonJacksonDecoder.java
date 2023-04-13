package cn.milesians.provider.lemon;


import cn.milesians.provider.commons.exception.ProviderException;
import cn.milesians.provider.json.Jackson;
import cn.milesians.provider.json.ObjectMapperFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import feign.Response;
import feign.Util;
import feign.codec.Decoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import lombok.extern.slf4j.Slf4j;

/**
 * feign 使用的序列化配置
 *
 * @author soliddq
 * @date 2022-06-14
 */
@Slf4j
public class LemonJacksonDecoder implements Decoder {

    private volatile static LemonJacksonDecoder SINGLETON;
    private final ObjectMapper mapper;

    private LemonJacksonDecoder() {
        ObjectMapper instance = ObjectMapperFactory.createInstance();
        instance.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        this.mapper = instance;
    }


    public static LemonJacksonDecoder getInstance() {
        if (null == SINGLETON) {
            synchronized (LemonJacksonDecoder.class) {
                if (null == SINGLETON) {
                    SINGLETON = new LemonJacksonDecoder();
                }
            }
        }
        return SINGLETON;

    }

    @Override
    public Object decode(Response response, Type type) throws IOException {
        if (response.status() == 404) {
            return Util.emptyValueOf(type);
        }
        if (response.body() == null) {
            return null;
        }
        Reader reader = response.body().asReader();
        if (!reader.markSupported()) {
            reader = new BufferedReader(reader, 1);
        }
        try {
            reader.mark(1);
            if (reader.read() == -1) {
                return null;
            }
            reader.reset();

            if (type.getTypeName().equals(ApiCloudResponse.class.getTypeName())) {
                return mapper.readValue(reader, mapper.constructType(ApiCloudResponse.class));
            }

            if (type.getTypeName().equals(TokenResponseDTO.class.getTypeName())) {
                return mapper.readValue(reader, mapper.constructType(TokenResponseDTO.class));
            }

            ApiCloudResponse<?> apiCloudResponse = mapper.readValue(reader,
                mapper.constructType(ApiCloudResponse.class));
            if (apiCloudResponse.getCode() != 0) {
                throw new ProviderException(Jackson.serialize(apiCloudResponse));
            }

            return mapper.readValue(mapper.writeValueAsString(apiCloudResponse.getResult()),
                mapper.constructType(type));
        } catch (RuntimeJsonMappingException e) {
            if (e.getCause() != null && e.getCause() instanceof IOException) {
                throw (IOException) e.getCause();
            }
            throw e;
        }
    }
}