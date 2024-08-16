package kz.diploma.exchange_rate.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import kz.diploma.exchange_rate.util.ObjectMapperUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static kz.diploma.exchange_rate.util.Constants.*;

@Converter(autoApply = true)
public class MapToStringConverter implements AttributeConverter<Map<String, Object>, String> {

    @Override
    public String convertToDatabaseColumn(Map<String, Object> map) {
        if (map == null) {
            return "{}";
        }
        try {
            return ObjectMapperUtil.attributeConverterObjectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(CONVERTING_MAP_TO_JSON_ERROR, e);
        }
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String json) {
        if (json == null || json.isEmpty()) {
            return new HashMap<>();
        }
        try {
            return ObjectMapperUtil.attributeConverterObjectMapper.readValue(json, HashMap.class);
        } catch (IOException e) {
            throw new IllegalArgumentException(CONVERTING_JSON_TO_MAP_ERROR, e);
        }
    }
}