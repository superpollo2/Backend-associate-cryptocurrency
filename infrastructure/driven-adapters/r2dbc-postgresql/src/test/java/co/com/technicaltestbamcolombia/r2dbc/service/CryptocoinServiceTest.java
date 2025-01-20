package co.com.technicaltestbamcolombia.r2dbc.service;

import co.com.technicaltestbamcolombia.model.config.CryptoException;
import co.com.technicaltestbamcolombia.r2dbc.mapper.Mapper;
import co.com.technicaltestbamcolombia.r2dbc.repository.CryptocoinCustomRepository;
import co.com.technicaltestbamcolombia.r2dbc.repository.UserCryptocoinRepository;
import co.com.technicaltestbamcolombia.r2dbc.providers.TestBuilders;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CryptocoinServiceTest {

    @Mock
    private CryptocoinCustomRepository cryptocoinCustomRepository;

    @Mock
    private UserCryptocoinRepository userCryptocoinRepository;

    @Mock
    private Mapper mapper;

    @Mock
    private UserService userService;

    @Mock
    private CountryService countryService;

    @InjectMocks
    private CryptocoinService cryptocoinService;

    @Test
    void findCryptocoinByUserId_ShouldReturnCryptocoins() {

        Integer userId = 1;
        var list = TestBuilders.getCryptocoinDTOList();

        when(cryptocoinCustomRepository.findCryptocoinsByUserId(userId))
                .thenReturn(Flux.just(list.get(0), list.get(1)));

        var result = cryptocoinService.findCryptocoinByUserId(userId);


        StepVerifier.create(result)
                .expectNext(list.get(0))
                .expectNext(list.get(1))
                .verifyComplete();
        verify(cryptocoinCustomRepository).findCryptocoinsByUserId(userId);
    }

    @Test
    void findCryptocoinByUserId_ShouldThrowExceptionWhenNoCryptocoinsFound() {

        Integer userId = 999;
        var errorTitle = "ERROR CONSULTA, NO EXISTE O NO ESTA DISPONIBLE";
        when(cryptocoinCustomRepository.findCryptocoinsByUserId(userId))
                .thenReturn(Flux.empty());

        var result = cryptocoinService.findCryptocoinByUserId(userId);

        StepVerifier.create(result)
                .expectErrorMatches(throwable ->
                        throwable instanceof CryptoException &&
                                ((CryptoException) throwable).getStatus() == 404 &&
                                "CR-B000".equals(((CryptoException) throwable).getCode()) &&
                                errorTitle.equals(((CryptoException) throwable).getTitle()) &&
                                "No hay Cryptomonedas para el usuario: 999".equals((throwable.getMessage()))
                )
                .verify();
        verify(cryptocoinCustomRepository).findCryptocoinsByUserId(userId);
    }

   /* @Test
    void findCryptocoinByCountryId_ShouldReturnCryptocoins() {

        Integer countryId = 1;
        var list = TestBuilders.getCryptocoinDTOList();

        when(cryptocoinCustomRepository.findCryptocoinByCountryId(countryId))
                .thenReturn(Flux.just(list.get(0), list.get(1)));

        var result = cryptocoinService.findCryptocoinByCountryId(countryId);

        StepVerifier.create(result)
                .expectNext(list.get(0))
                .expectNext(list.get(1))
                .verifyComplete();
        verify(cryptocoinCustomRepository).findCryptocoinByCountryId(countryId);
    }*/


    @Test
    void deleteCoinFromUserShouldDeleteCoinSuccessfully() {

        var request = TestBuilders.getUserCryptocoinDTOWitAmount();
        var userCryptocoinEntity = TestBuilders.getUserCryptocoinEntityWitAmount();
        when(userCryptocoinRepository.findByCryptocoinIdAndUserId(request.getCryptocoinId(), request.getUserId()))
                .thenReturn(Mono.just(userCryptocoinEntity));
        when(userCryptocoinRepository.deleteUserCryptocoinEntityByCryptocoinIdAndUserId(request.getCryptocoinId(), request.getUserId()))
                .thenReturn(Mono.empty());

        var result = cryptocoinService.deleteCoinFromUser(request);

        StepVerifier.create(result)
                .verifyComplete();
        verify(userCryptocoinRepository).findByCryptocoinIdAndUserId(request.getCryptocoinId(), request.getUserId());
        verify(userCryptocoinRepository).deleteUserCryptocoinEntityByCryptocoinIdAndUserId(request.getCryptocoinId(), request.getUserId());
    }

    @Test
    void deleteCoinFromUserShouldThrowExceptionWhenNotFound() {
        var errorTitle = "ERROR CONSULTA, NO EXISTE O NO ESTA DISPONIBLE";
        var request = TestBuilders.getUserCryptocoinDTOWithoutAmount();
        when(userCryptocoinRepository.findByCryptocoinIdAndUserId(request.getCryptocoinId(), request.getUserId()))
                .thenReturn(Mono.empty());

        var result = cryptocoinService.deleteCoinFromUser(request);

        StepVerifier.create(result)
                .expectErrorMatches(throwable ->
                        throwable instanceof CryptoException &&
                                ((CryptoException) throwable).getStatus() == 404 &&
                                "CR-B000".equals(((CryptoException) throwable).getCode()) &&
                                errorTitle.equals(((CryptoException) throwable).getTitle()) &&
                                "No se encontró la información".equals((throwable.getMessage()))
                )
                .verify();
        verify(userCryptocoinRepository).findByCryptocoinIdAndUserId(request.getCryptocoinId(), request.getUserId());
        verify(userCryptocoinRepository, never()).deleteUserCryptocoinEntityByCryptocoinIdAndUserId(anyInt(), anyInt());
    }

    @Test
    void editAmountCoinFromUser_ShouldEditAmountSuccessfully() {
        var request = TestBuilders.getUserCryptocoinDTOWitAmount();
        var userCryptocoinEntity = TestBuilders.getUserCryptocoinEntityWitAmount();

        when(cryptocoinCustomRepository.updateAmountCointUser(request))
                .thenReturn(Mono.just(1L));
        when(userCryptocoinRepository.findByCryptocoinIdAndUserId(request.getCryptocoinId(), request.getUserId()))
                .thenReturn(Mono.just(userCryptocoinEntity));
        when(mapper.toDomain(userCryptocoinEntity))
                .thenReturn(request);

        var result = cryptocoinService.editAmountCoinFromUser(request);


        StepVerifier.create(result)
                .expectNext(request)
                .verifyComplete();
        verify(cryptocoinCustomRepository).updateAmountCointUser(request);
        verify(userCryptocoinRepository).findByCryptocoinIdAndUserId(request.getCryptocoinId(), request.getUserId());
        verify(mapper).toDomain(userCryptocoinEntity);
    }

    @Test
    void editAmountCoinFromUserShouldThrowExceptionWhenUpdateFails() {
        var request = TestBuilders.getUserCryptocoinDTOWitAmount();

        var errorTitle = "ERROR CONSULTA, NO EXISTE O NO ESTA DISPONIBLE";
        when(cryptocoinCustomRepository.updateAmountCointUser(request))
                .thenReturn(Mono.just(0L));

        var result = cryptocoinService.editAmountCoinFromUser(request);

        StepVerifier.create(result)
                .expectErrorMatches(throwable ->
                        throwable instanceof CryptoException &&
                                ((CryptoException) throwable).getStatus() == 404 &&
                                "CR-B000".equals(((CryptoException) throwable).getCode()) &&
                                errorTitle.equals(((CryptoException) throwable).getTitle()) &&
                                "No existe información o el valor del amount da un saldo negativo".equals((throwable.getMessage()))
                )
                .verify();
        verify(cryptocoinCustomRepository).updateAmountCointUser(request);
        verify(userCryptocoinRepository, never()).findByCryptocoinIdAndUserId(anyInt(), anyInt());
        verify(mapper, never()).toDomain(any());
    }

    /*@Test
    void saveAssociateCoinShouldReturnExistingAssociation() {
        var request = TestBuilders.getUserCryptocoinDTOWithoutAmount();
        Integer countryId = 1;

        when(userService.findCountryByUserId(request.getUserId()))
                .thenReturn(Mono.just(countryId));
        when(cryptocoinCustomRepository.findCryptocoinByCountryId(countryId))
                .thenReturn(Flux.just(new CryptocoinDTO(1, "Bitcoin", "BTC", 30000.0)));
        when(userCryptocoinRepository.findByCryptocoinIdAndUserId(request.getCryptocoinId(), request.getUserId()))
                .thenReturn(Mono.just(new UserCryptocoinEntity(1, 1, 100)));

        var result = cryptocoinService.saveAssociateCoin(request);

        StepVerifier.create(result)
                .expectNext(request)
                .verifyComplete();

        verify(userService).findCountryByUserId(request.getUserId());
        verify(cryptocoinCustomRepository).findCryptocoinByCountryId(countryId);
        verify(userCryptocoinRepository).findByCryptocoinIdAndUserId(request.getCryptocoinId(), request.getUserId());
        verifyNoInteractions(mapper);
    }*/


}