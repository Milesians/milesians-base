package cn.milesians.provider.lemon;

import cn.milesians.provider.json.Jackson;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import feign.Param;
import feign.QueryMapEncoder;
import feign.codec.EncodeException;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * to
 *
 * @author nhsoft.busl
 * @date 2022/11/14 20:16
 */
public class JacksonQueryMapEncoder implements QueryMapEncoder {

    private final Map<Class<?>, ObjectParamMetadata> classToMetadata =
        new HashMap<Class<?>, ObjectParamMetadata>();

    @Override
    public Map<String, Object> encode(Object object) throws EncodeException {
        try {
            ObjectParamMetadata metadata = getMetadata(object.getClass());
            Map<String, Object> propertyNameToValue = new HashMap<String, Object>();
            for (PropertyDescriptor pd : metadata.objectProperties) {
                Method method = pd.getReadMethod();
                Object value = method.invoke(object);

                if (value instanceof LocalDateTime || value instanceof LocalDate) {
                    value = Jackson.serialize(value).replace("\"", "");
                }

                if (value != null && value != object) {
                    Param alias = method.getAnnotation(Param.class);
                    String name = alias != null ? alias.value() : pd.getName();
                    PropertyNamingStrategy.SnakeCaseStrategy snakeCaseStrategy = new PropertyNamingStrategy.SnakeCaseStrategy();
                    propertyNameToValue.put(snakeCaseStrategy.translate(name), value);
                }
            }
            return propertyNameToValue;
        } catch (IllegalAccessException | IntrospectionException | InvocationTargetException e) {
            throw new EncodeException("Failure encoding object into query map", e);
        }
    }

    private ObjectParamMetadata getMetadata(Class<?> objectType) throws IntrospectionException {
        ObjectParamMetadata metadata = classToMetadata.get(objectType);
        if (metadata == null) {
            metadata = ObjectParamMetadata.parseObjectType(objectType);
            classToMetadata.put(objectType, metadata);
        }
        return metadata;
    }

    private static class ObjectParamMetadata {

        private final List<PropertyDescriptor> objectProperties;

        private ObjectParamMetadata(List<PropertyDescriptor> objectProperties) {
            this.objectProperties = Collections.unmodifiableList(objectProperties);
        }

        private static ObjectParamMetadata parseObjectType(Class<?> type)
            throws IntrospectionException {
            List<PropertyDescriptor> properties = new ArrayList<PropertyDescriptor>();

            for (PropertyDescriptor pd : Introspector.getBeanInfo(type).getPropertyDescriptors()) {
                boolean isGetterMethod = pd.getReadMethod() != null && !"class".equals(pd.getName());
                if (isGetterMethod) {
                    properties.add(pd);
                }
            }

            return new ObjectParamMetadata(properties);
        }
    }
}
