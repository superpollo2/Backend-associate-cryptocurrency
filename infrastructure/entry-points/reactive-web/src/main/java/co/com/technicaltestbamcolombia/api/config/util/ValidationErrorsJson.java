package co.com.technicaltestbamcolombia.api.config.util;


import co.com.technicaltestbamcolombia.model.config.CryptoErrorCode;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Log4j2
public class ValidationErrorsJson {
    private final
    List<String> messages;
    private CryptoErrorCode errorCode;

    public ValidationErrorsJson() {
        messages = new ArrayList<>();
    }

    public void addMessage(JsonNode json) {
        if (Objects.isNull(errorCode)) {
            if ("".equals(json.at("/schema/pointer").asText())) {
                errorCode = CryptoErrorCode.CRB01;
                messages.add(getMessageErrorPointerNull(json));
            } else {
                errorCode = CryptoErrorCode.CRB01;
                messages.add(getMessageError(json));
            }
        }
    }

    public void addMessage() {
        messages.add(ErrorMessage.ERROR_API);
        errorCode = CryptoErrorCode.CRI00;

    }

    public boolean isInError() {
        return !messages.isEmpty();
    }

    public String getMessageErrorPointerNull(JsonNode jsonNode) {
        String keyword = jsonNode.get("keyword").asText();
        return switch (keyword) {
            case "additionalProperties" -> getMessageAdditionalProperties();
            default -> getMessageSchemaRequired(jsonNode);
        };
    }

    public String getMessageError(JsonNode jsonNode) {
        String keyword = jsonNode.get("keyword").asText();
        var properties = jsonNode.at("/instance/pointer").asText().split("/");
        return switch (keyword) {
            case "required" -> getMessageRequired(jsonNode, properties);
            case "minLength" -> fieldEmpty(properties[1]);
            case "mAAXIM" -> fieldEmpty(properties[1]);
            case "type" -> getMessageType(jsonNode, properties);
            default -> getMessageAdditionalProperties();
        };

    }

    public String getMessageType(JsonNode jsonNode, String[] properties) {
        errorCode = CryptoErrorCode.CRB01;
        var found = jsonNode.get("found").asText();
        var expected = jsonNode.get(" expected").asText();
        return String.format(ErrorMessage.ERROR_INVALID_TYPE, found, expected, properties[1]);
    }


    private String fieldEmpty(String field) {
        errorCode = CryptoErrorCode.CRB02;
        return String.format(ErrorMessage.ERROR_FIELD_NULL, field);
    }


    public String getMessageSchemaRequired(JsonNode jsonNode) {
        var missingNode = jsonNode.get("missing").toString().replace("\"", " ");
        return String.format(ErrorMessage.ERROR_INVALID_REQUIRED_SCHEMA, missingNode);
    }

    private String getMessageRequired(JsonNode jsonNode, String[] properties) {
        errorCode = CryptoErrorCode.CRB01;
        var missingNode = jsonNode.get("missing").toString().replace("\"", " ");
        int index = properties.length == 2 ? 1 : 2;
        return String.format(ErrorMessage.ERROR_INVALID_REQUIRED, missingNode, properties[index]);
    }

    private String getMessageAdditionalProperties() {
        errorCode = CryptoErrorCode.CRB01;
        return ErrorMessage.ERROR_KEY_NULL;
    }



}
