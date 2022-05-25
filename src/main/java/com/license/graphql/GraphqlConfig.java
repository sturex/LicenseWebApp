package com.license.graphql;

import graphql.language.IntValue;
import graphql.language.StringValue;
import graphql.schema.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Configuration
public class GraphqlConfig {

    @Bean
    RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return (wiringBuilder) -> wiringBuilder
                .scalar(unixTimestampScalar());
    }

    private GraphQLScalarType unixTimestampScalar() {
        return GraphQLScalarType.newScalar()
                .name("UnixTimestamp")
                .coercing(new Coercing<Long, String>() {
                    @Override
                    public String serialize(Object dataFetcherResult) throws CoercingSerializeException {
                        if (dataFetcherResult instanceof Long) {
                            return Long.toString((Long) dataFetcherResult);
                        }
                        throw CoercingSerializeException.newCoercingSerializeException().build();
                    }

                    @Override
                    public Long parseValue(Object input) throws CoercingParseValueException {
                        if (input instanceof String) {
                            return Long.valueOf((String) input);
                        }
                        throw CoercingSerializeException.newCoercingSerializeException().build();
                    }

                    @Override
                    public Long parseLiteral(Object input) throws CoercingParseLiteralException {
                        if (input instanceof IntValue) {
                            return ((IntValue) input).getValue().longValue();
                        }
                        throw CoercingParseLiteralException.newCoercingParseLiteralException().build();
                    }
                })
                .build();
    }
}
