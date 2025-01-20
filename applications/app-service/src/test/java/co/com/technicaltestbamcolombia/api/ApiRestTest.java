package co.com.technicaltestbamcolombia.api;

import co.com.technicaltestbamcolombia.api.config.util.JsonSchemaValidator;
import co.com.technicaltestbamcolombia.api.service.ApiRestService;
import co.com.technicaltestbamcolombia.usecase.CryptocoinUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

@WebFluxTest(controllers = {ApiRest.class})
@ActiveProfiles("test")
@AutoConfigureWebTestClient(timeout = "36000")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApiRest.class, ApiRestService.class})
class ApiRestTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    @Qualifier("user_crypto_request")
    private JsonSchemaValidator jsonSchemaValidator;

    @MockitoBean
    private  CryptocoinUseCase cryptocoinUseCase;

    private ApiRestService service;

    @MockitoBean
    private ObjectMapper objectMapper;

    private ApiRest apiRest;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.service = new ApiRestService(cryptocoinUseCase, objectMapper);
        this.apiRest = new ApiRest(service, jsonSchemaValidator);
    }

   /* @Test
    void generateRequest() throws JsonProcessingException {

        var mapperObject = new ObjectMapper();
        var jsonBody = JsonNodeProvider.getRequest();
        var body = mapperObject.readTree(jsonBody);
        var responseQr = ResponseProvider.getRequestResponse();

        when(service.generateRequestWithFilters(body)).thenReturn(Mono.just(responseQr));

        webTestClient.post()
                .uri("/api/v1/picture-key-qr/qr-code-image")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .bodyValue(jsonBody)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(ResponsePictureQR.class)
                .value(response -> {
                    assertEquals(responseQr.getRequestId(), response.getRequestId());
                    assertEquals(responseQr.getPictureQR(), response.getPictureQR());
                });

    }*/


}