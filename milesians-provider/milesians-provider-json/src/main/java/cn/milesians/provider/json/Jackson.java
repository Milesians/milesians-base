package cn.milesians.provider.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

/**
 * @author milesians
 * @date 2023/4/4
 * @since 1.0
 */
public class Jackson {

    private static ObjectMapper MAPPER = ObjectMapperFactory.getInstance();

    public static ObjectMapper getObjectMapper() {
        return MAPPER;
    }
    
    public static void setObjectMapper(final ObjectMapper mapper) {
        MAPPER = mapper;
    }


    public static <T> String serialize(T t) throws JacksonException {
        return serialize(t, true);
    }

    public static JsonNode getJsonNode(String json) {
        try {
            return MAPPER.readTree(json);
        } catch (JsonProcessingException e) {
            throw new JacksonException(e);
        }
    }

    public static <T> String serialize(T t, boolean throwEx) throws JacksonException {
        try {
            return MAPPER.writeValueAsString(t);
        } catch (JsonProcessingException var3) {
            if (throwEx) {
                throw new JacksonException(var3);
            } else {
                return null;
            }
        }
    }

    public static <T> String serialize(T t, Class<?> view) {
        return serialize(t, view, true);
    }

    public static <T> String serialize(T t, Class<?> view, boolean throwEx) throws JacksonException {
        try {
            return MAPPER.writerWithView(view).writeValueAsString(t);
        } catch (JsonProcessingException var4) {
            if (throwEx) {
                throw new JacksonException(var4);
            } else {
                return null;
            }
        }
    }

    public static <T> T deserialize(String s, Class<T> type) throws JacksonException {
        return deserialize(s, type, true);
    }

    public static <T> T deserialize(String s, Class<T> type, boolean throwEx) throws JacksonException {
        try {
            return MAPPER.readValue(s, type);
        } catch (IOException var4) {
            if (throwEx) {
                throw var4 instanceof JsonMappingException ? new JacksonException(
                    ((JsonMappingException) var4).getOriginalMessage()) : new JacksonException(var4);
            } else {
                return null;
            }
        }
    }

    public static <T> T deserialize(JsonNode s, Class<T> type) throws JacksonException {
        return MAPPER.convertValue(s, type);
    }

    public static <T> T deserialize(String s, Class<T> type, Class<?> view) throws JacksonException {
        return deserialize(s, type, view, true);
    }

    public static <T> T deserialize(String s, Class<T> type, Class<?> view, boolean throwEx)
        throws JacksonException {
        try {
            return MAPPER.readerWithView(view).readValue(s, type);
        } catch (IOException var5) {
            if (throwEx) {
                throw var5 instanceof JsonMappingException ? new JacksonException(
                    ((JsonMappingException) var5).getOriginalMessage()) : new JacksonException(var5);
            } else {
                return null;
            }
        }
    }

    public static <T> T deserialize(String s, TypeReference<T> typeReference) throws JacksonException {
        return deserialize(s, typeReference, true);
    }

    public static <T> T deserialize(String s, TypeReference<T> typeReference, boolean throwEx)
        throws JacksonException {
        try {
            return MAPPER.readValue(s, typeReference);
        } catch (IOException var4) {
            if (throwEx) {
                throw new JacksonException(var4);
            } else {
                return null;
            }
        }
    }
}
