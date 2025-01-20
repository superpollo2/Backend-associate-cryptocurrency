package co.com.technicaltestbamcolombia.r2dbc.service;

import co.com.technicaltestbamcolombia.model.country.CountryDTO;
import co.com.technicaltestbamcolombia.model.country.gateways.CountryGateway;
import co.com.technicaltestbamcolombia.r2dbc.mapper.Mapper;
import co.com.technicaltestbamcolombia.r2dbc.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CountryService implements CountryGateway {

    private final CountryRepository countryRepository;
    private final Mapper mapper;

    @Override
    public Mono<CountryDTO> getCountry(Integer countryId) {
        return countryRepository.getCountryEntityByCountryId(countryId)
                .map(mapper::toDomainCountry);
    }
}
