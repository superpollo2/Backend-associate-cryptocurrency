package co.com.technicaltestbamcolombia.api.config.util;


import co.com.technicaltestbamcolombia.model.config.CryptoException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;


import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
public class JsonSchemaValidator {

    private final JsonSchema jsonSchema;

    public void validate(JsonNode body, ValidationErrorsJson errors) {
        try {
            ProcessingReport report = jsonSchema.validate(body);
            log.info(report.toString());
            if (!report.isSuccess()) {
                report.iterator().forEachRemaining(m -> errors.addMessage(m.asJson()));
            }
        } catch (ProcessingException e) {
            log.info(e.toString());
            errors.addMessage();
        }
    }

    public void validateWithJsonSchema(JsonNode jsonNode) {
        ValidationErrorsJson errors = new ValidationErrorsJson();

        this.validate(jsonNode, errors);
        if (errors.isInError()) {
            Optional<String> value = errors.getMessages().stream().reduce((s, s2) -> s + ", " + s2);
            var error = errors.getErrorCode();

            if (value.isPresent()) {
                log.info(value.toString());
                throw new CryptoException(
                        HttpStatus.BAD_REQUEST.value(),
                        error.getErrorCode(),
                        error.getErrorTitle(), value.get());
            }
        }
    }

}
