package kz.diploma.exchange_rate.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

public class ObjectMapperUtil {

    public static final ObjectMapper attributeConverterObjectMapper;
    public static final ObjectMapper globalObjectMapper;

    static {
        attributeConverterObjectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())  // Регистрация модуля для правильной сериализации и десериализации java.time
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)  // Настройка для игнорирования неизвестных свойств при десериализации
                .configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true)
                .configure(DeserializationFeature.ACCEPT_FLOAT_AS_INT, false)
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)  // Включение/отключение обертки корневого элемента
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)  // Включение отображения дат и времени в виде timestamp
                .setSerializationInclusion(JsonInclude.Include.ALWAYS);

        globalObjectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())  // Регистрация модуля для правильной сериализации и десериализации java.time
                .registerModule(new ParameterNamesModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false); // Включение отображения дат и времени в виде timestamp
    }

    // Конструктор по умолчанию закрыт для предотвращения создания экземпляров этого класса
    private ObjectMapperUtil() {}
}
