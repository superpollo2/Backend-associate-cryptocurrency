package co.com.technicaltestbamcolombia.config.app;

import co.com.technicaltestbamcolombia.config.pojoerror.Errors;
import co.com.technicaltestbamcolombia.model.config.CryptoException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RequiredArgsConstructor
@Configuration
@Log4j2
@Order(-2)
public class GlobalErrorHandler implements ErrorWebExceptionHandler {
    private final ObjectMapper objectMapper;
    DataBuffer dataBuffer = null;

    @SneakyThrows
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        var resquest = exchange.getRequest();
        DataBufferFactory bufferFactory = exchange.getResponse().bufferFactory();
        try {
            throw ex;
        }catch (CryptoException exception){
            exchange.getResponse().setStatusCode(HttpStatusCode.valueOf(exception.getStatus()));
            var errors = Errors.builder()
                    .href(resquest.getURI().toString())
                    .status(exception.getStatus())
                    .code(exception.getCode())
                    .title(exception.getTitle())
                    .detail(exception.getMessage())
                    .id(UUID.randomUUID())
                    .build();
            dataBuffer = bufferFactory.wrap(objectMapper.writeValueAsBytes(errors));
            log.info(errors.getId() + " Error en la solicitud " + exchange.getRequest().toString() );
        }
        return exchange.getResponse().writeWith(Mono.just(dataBuffer));
    }

}
