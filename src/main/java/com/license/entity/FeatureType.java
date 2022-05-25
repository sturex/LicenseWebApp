package com.license.entity;

import javax0.license3j.Feature;

import java.util.function.BiFunction;

public enum FeatureType {
    FLOAT(floatFeatureBuilder()), STRING(stringFeatureBuilder()), INTEGER(integerFeatureBuilder());

    //TODO try/catch/rethrow conversions from String

    private static BiFunction<String, String, Feature> floatFeatureBuilder() {
        return (name, value) -> Feature.Create.floatFeature(name, Float.valueOf(value));
    }

    private static BiFunction<String, String, Feature> integerFeatureBuilder() {
        return (name, value) -> Feature.Create.intFeature(name, Integer.valueOf(value));
    }

    private static BiFunction<String, String, Feature> stringFeatureBuilder() {
        return (name, value) -> Feature.Create.stringFeature(name, value);
    }

    private final BiFunction<String, String, Feature> featureBuilder;

    FeatureType(BiFunction<String, String, Feature> featureBuilder) {
        this.featureBuilder = featureBuilder;
    }

    public Feature create(String name, String value) {
        return featureBuilder.apply(name, value);
    }

}
