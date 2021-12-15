package com.example.demo.configuration.jackson.codecs;

import com.example.demo.domain.ProductQuantity;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ProductQuantityCodecs {

    public static class Serializer extends JsonSerializer<ProductQuantity> {

        @Override
        public void serialize(ProductQuantity value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            Integer number = value.asInteger();
            gen.writeNumber(number);
        }
    }

    public static class Deserializer extends JsonDeserializer<ProductQuantity> {

        @Override
        public ProductQuantity deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
            int valueAsInt = p.getValueAsInt();
            return new ProductQuantity(valueAsInt);
        }
    }
}
