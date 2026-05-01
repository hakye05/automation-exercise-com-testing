package com.automationexercise.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.util.Map;

/**
 * Utility class for mapping Java objects to different formats.
 * */
public class DataMapper {

    private static final ObjectMapper mapper = JsonMapper.builder()
            .defaultPropertyInclusion(JsonInclude.Value.construct(
                    JsonInclude.Include.NON_EMPTY,
                    JsonInclude.Include.ALWAYS))
            .build();

    /**
     * Converts object to a {@link Map} for unmodifiable data manipulations.
     * @param object the POJO or record instance needed to convert
     * @return A {@link Map} representation of object
     * @throws IllegalArgumentException if the object cannot be converted
     * */
    public static Map<String, Object> convertToMap(Object object) {
        return mapper.convertValue(object, new TypeReference<Map<String, Object>>() {});
    }

}
