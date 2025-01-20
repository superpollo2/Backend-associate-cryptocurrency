package co.com.technicaltestbamcolombia.r2dbc.service.builders;

import co.com.technicaltestbamcolombia.model.Cryptocoin.CryptocoinDTO;
import co.com.technicaltestbamcolombia.model.user.UserCryptocoinDTO;
import co.com.technicaltestbamcolombia.model.user.UserDTO;
import co.com.technicaltestbamcolombia.r2dbc.entity.UserCryptocoinEntity;
import co.com.technicaltestbamcolombia.r2dbc.entity.UsersEntity;

import java.util.ArrayList;
import java.util.List;

public class TestBuilders {

    public static UserDTO getUserDTO() {
        return UserDTO.builder()
                .userId(1)
                .countryId(2)
                .password("password")
                .username("username")
                .build();
    }

    public static UsersEntity getUserEntities() {
        return UsersEntity.builder()
                .userId(1)
                .countryId(7)
                .password("password")
                .username("username")
                .build();
    }

    public static CryptocoinDTO getCryptocoinDTOOne() {
        return CryptocoinDTO.builder()
                .cryptocoinId(1)
                .cryptocoinName("Bitcoin")
                .symbol("BTC")
                .exchangeRate(200.00)
                .build();
    }

    public static CryptocoinDTO getCryptocoinDTOTwo() {
        return CryptocoinDTO.builder()
                .cryptocoinId(2)
                .cryptocoinName("Ethereum")
                .symbol("ETH")
                .exchangeRate(300.00)
                .build();
    }

    public static List<CryptocoinDTO> getCryptocoinDTOList() {
        List<CryptocoinDTO> list = new ArrayList<CryptocoinDTO>();
        list.add(getCryptocoinDTOOne());
        list.add(getCryptocoinDTOTwo());
        return list;
    }

    public static UserCryptocoinDTO getUserCryptocoinDTOWithoutAmount() {
        return UserCryptocoinDTO.builder()
                .userId(1)
                .cryptocoinId(1)
                .build();
    }
    public static UserCryptocoinEntity getUserCryptocoinEntityWithoutAmount() {
        return UserCryptocoinEntity.builder()
                .userId(1)
                .cryptocoinId(1)
                .build();
    }

    public static UserCryptocoinDTO getUserCryptocoinDTOWitAmount() {
        return UserCryptocoinDTO.builder()
                .userId(1)
                .cryptocoinId(1)
                .amount(50)
                .build();
    }

    public static UserCryptocoinEntity getUserCryptocoinEntityWitAmount() {
        return UserCryptocoinEntity.builder()
                .userId(1)
                .cryptocoinId(1)
                .amount(50)
                .build();
    }


    public static UserCryptocoinDTO getUserCryptocoinDTOWitAmountTwo() {
        return UserCryptocoinDTO.builder()
                .userId(2)
                .cryptocoinId(1)
                .amount(50)
                .build();
    }
}
