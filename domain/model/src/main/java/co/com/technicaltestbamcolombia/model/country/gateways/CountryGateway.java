package co.com.technicaltestbamcolombia.model.country.gateways;

import co.com.technicaltestbamcolombia.model.country.CountryDTO;
import reactor.core.publisher.Mono;

public interface CountryGateway {
    Mono<CountryDTO> getCountry(Integer countryId);

}
