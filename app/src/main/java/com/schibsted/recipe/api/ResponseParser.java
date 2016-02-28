package com.schibsted.recipe.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import retrofit.mime.TypedInput;

public class ResponseParser {

    private final ObjectMapper mObjectMapper;

    private ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        return objectMapper;
    }

    public ResponseParser() {
        mObjectMapper = createObjectMapper();
    }

    public <T> T getModel(TypedInput typedInput, Class<T> classDef) {
        try {
            return mObjectMapper.readValue(getStringFromTypedInput(typedInput.in()), classDef);
        } catch (Exception e) {
            return null;
        }
    }

    private String getStringFromTypedInput(InputStream in) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader buffer = new BufferedReader(new InputStreamReader(in));
        String read;

        while((read = buffer.readLine()) != null)
            stringBuilder.append(read);

        buffer.close();

        return stringBuilder.toString();
    }
}