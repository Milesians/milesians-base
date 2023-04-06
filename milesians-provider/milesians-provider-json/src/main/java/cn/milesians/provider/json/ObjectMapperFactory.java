package cn.milesians.provider.json;


import com.arpnetworking.commons.jackson.databind.ImmutableObjectMapper;
import com.arpnetworking.commons.jackson.databind.module.BuilderModule;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Optional;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ObjectMapperFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectMapperFactory.class);
    private static final ObjectMapper UNMODIFIABLE_OBJECT_MAPPER = ImmutableObjectMapper.of(
        createModifiableObjectMapper());

    private ObjectMapperFactory() {
    }

    /**
     * 创建一个配置了标准设置的新 {@link ObjectMapper} ，调用方可以安全地定制新实例
     *
     * @return 可修改 {@link ObjectMapper} 实例
     */
    public static ObjectMapper createInstance() {
        return createModifiableObjectMapper();
    }

    /**
     * 创建一个配置了标准设置的新 {@link ObjectMapper} ，调用方可以安全地定制新实例
     *
     * @param jsonFactory {@link JsonFactory} 实例
     * @return 可修改 {@link ObjectMapper} 实例
     */
    public static ObjectMapper createInstance(JsonFactory jsonFactory) {
        return createModifiableObjectMapper(new ObjectMapper(jsonFactory));
    }

    /**
     * 获取使用标准设置配置的 ObjectMapper 实例。这些实例被认为是共享的并且是不可变的。
     *
     * @return 不可变 {@link ObjectMapper} 实例
     */
    public static ObjectMapper getInstance() {
        return UNMODIFIABLE_OBJECT_MAPPER;
    }

    private static ObjectMapper createModifiableObjectMapper() {
        return createModifiableObjectMapper(new ObjectMapper());
    }

    private static ObjectMapper createModifiableObjectMapper(final ObjectMapper objectMapper) {
        objectMapper.registerModule(new BuilderModule());
        registerModule(objectMapper, "com.fasterxml.jackson.datatype.guava.GuavaModule");
        registerModule(objectMapper, "com.fasterxml.jackson.datatype.jdk8.Jdk8Module");
        registerModule(objectMapper, "com.fasterxml.jackson.datatype.jsr310.JavaTimeModule");
        registerAdditionalModules(objectMapper, System::getProperty);
        objectMapper.configure(DeserializationFeature.FAIL_ON_TRAILING_TOKENS, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false);
        objectMapper.setDateFormat(new StdDateFormat());
        return objectMapper;
    }

    private static void registerAdditionalModules(
        final ObjectMapper objectMapper,
        final Function<String, String> propertyAccessor) {
        final String moduleClassNames = propertyAccessor.apply("commons.object-mapper-additional-module-class-names");
        if (moduleClassNames != null) {
            for (final String moduleClassName : moduleClassNames.split(",")) {
                registerModule(objectMapper, moduleClassName);
            }
        }
    }

   private static void registerModule(final ObjectMapper objectMapper, final String className) {
        final Optional<Class<? extends Module>> moduleClass = getClass(className);
        if (moduleClass.isPresent()) {
            try {
                final Module module = moduleClass.get().getDeclaredConstructor().newInstance();
                objectMapper.registerModule(module);
            } catch (final Exception e) {
                LOGGER.warn(
                    String.format(
                        "Unable to instantiate module; module=%s",
                        moduleClass.get()),
                    e);
            }
        }
    }

    private static <T> Optional<Class<? extends T>> getClass(final String className) {
        try {
            @SuppressWarnings("unchecked")
            final Optional<Class<? extends T>> clazz = Optional.<Class<? extends T>>of((Class<T>) Class.forName(className));
            return clazz;
        } catch (final ClassNotFoundException e) {
            return Optional.empty();
        }
    }
}
