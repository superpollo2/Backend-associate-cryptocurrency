package co.com.technicaltestbamcolombia.r2dbc.mapper;

import co.com.technicaltestbamcolombia.model.user.UserCryptocoin;
import co.com.technicaltestbamcolombia.r2dbc.entity.UserCryptocoinEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class MapperEntity {

    public UserCryptocoinEntity toEntity(UserCryptocoin userCryptocoin) {
        return UserCryptocoinEntity.builder()
                .cryptocoinId(userCryptocoin.getCryptocoinId())
                .userId(userCryptocoin.getUserId())
                .build();
    }

    public UserCryptocoin toDomain(UserCryptocoinEntity userCryptocoinEntity) {
        return UserCryptocoin.builder()
                .cryptocoinId(userCryptocoinEntity.getCryptocoinId())
                .userId(userCryptocoinEntity.getUserId())
                .build();
    }
}
