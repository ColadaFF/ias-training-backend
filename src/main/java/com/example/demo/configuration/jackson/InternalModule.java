package com.example.demo.configuration.jackson;

import com.example.demo.configuration.jackson.codecs.PersonIdParser;
import com.example.demo.configuration.jackson.codecs.PersonNameParser;
import com.example.demo.configuration.jackson.codecs.ProductQuantityCodecs;
import com.example.demo.domain.PersonId;
import com.example.demo.domain.PersonName;
import com.example.demo.domain.ProductQuantity;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class InternalModule extends SimpleModule {

    private static final String NAME = "InternalModule";

    public InternalModule() {
        super(NAME, Version.unknownVersion());

        addSerializer(ProductQuantity.class, new ProductQuantityCodecs.Serializer());
        addDeserializer(ProductQuantity.class, new ProductQuantityCodecs.Deserializer());



        addSerializer(PersonId.class, new PersonIdParser.Serializer());
        addDeserializer(PersonId.class, new PersonIdParser.Deserializer());

        addSerializer(PersonName.class, new PersonNameParser.Serializer());
        addDeserializer(PersonName.class, new PersonNameParser.Deserializer());
    }
}
