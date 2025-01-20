package co.com.technicaltestbamcolombia.usecase.providers;

import co.com.technicaltestbamcolombia.model.Cryptocoin.CryptocoinUserAmountDTO;
import co.com.technicaltestbamcolombia.model.user.UserCryptocoinDTO;

import java.util.ArrayList;
import java.util.List;

public class TestBuilders {

    public static CryptocoinUserAmountDTO getCryptocoinDTOOne() {
        return CryptocoinUserAmountDTO.builder()
                .cryptocoinId(1)
                .cryptocoinName("Bitcoin")
                .symbol("BTC")
                .exchangeRate(200.00)
                .build();
    }

    public static CryptocoinUserAmountDTO getCryptocoinDTOTwo() {
        return CryptocoinUserAmountDTO.builder()
                .cryptocoinId(2)
                .cryptocoinName("Ethereum")
                .symbol("ETH")
                .exchangeRate(300.00)
                .build();
    }

    public static List<CryptocoinUserAmountDTO> getCryptocoinDTOList() {
        List<CryptocoinUserAmountDTO> list = new ArrayList<CryptocoinUserAmountDTO>();
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
