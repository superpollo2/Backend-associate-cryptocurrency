package co.com.technicaltestbamcolombia.usecase.builders;

import co.com.technicaltestbamcolombia.model.Cryptocoin.CryptocoinDTO;
import co.com.technicaltestbamcolombia.model.user.UserCryptocoinDTO;

import java.util.ArrayList;
import java.util.List;

public class TestBuilders {

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

    public static UserCryptocoinDTO getUserCryptocoinDTOWitAmount() {
        return UserCryptocoinDTO.builder()
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
