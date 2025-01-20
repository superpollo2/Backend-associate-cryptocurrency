package co.com.technicaltestbamcolombia.api.providers;

import co.com.technicaltestbamcolombia.model.user.UserCryptocoinDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestBuilders {

    private static ObjectMapper mapper = new ObjectMapper();

    public static JsonNode getJsonRequest(String jsonString)throws JsonProcessingException {
        return mapper.readTree(jsonString);
    }

    public static String getUserCryptoRequest() {
        return """
                {
                    "userId": 1,
                    "cryptocoinId": 6,
                    "amount": 10
                }
                """;
    }

    public static UserCryptocoinDTO getResponseUserCryptocoin() {
        return UserCryptocoinDTO.builder()
                .userId(1)
                .cryptocoinId(6)
                .amount(10)
                .build();
    }
}
