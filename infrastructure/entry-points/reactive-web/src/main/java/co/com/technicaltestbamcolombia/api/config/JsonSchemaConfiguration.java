package co.com.technicaltestbamcolombia.api.config;

import co.com.technicaltestbamcolombia.api.config.util.JsonSchemaValidator;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

@Log4j2
@Configuration
public class JsonSchemaConfiguration {
    public static JsonSchema jsonSchema(String schemaFileName) {
        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        try {
            JsonNode jsonSchemaNode = JsonLoader.fromFile(new File(schemaFileName));
            return factory.getJsonSchema(jsonSchemaNode);
        } catch (IOException | ProcessingException e1) {
            log.error(e1.getMessage());
            throw new BeanCreationException("Ensure that the json schema resource is in: " + schemaFileName);
        }
    }
    @Bean
    public JsonSchemaValidator jsonSchemaResponse(@Value("${routes.schemas}") String route) {
        return new JsonSchemaValidator(jsonSchema(route + "user_crypto_request.json"));
    }
}
