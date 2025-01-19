package co.com.technicaltestbamcolombia.r2dbc.mapper;

import co.com.technicaltestbamcolombia.model.user.UserCryptocoinDTO;
import co.com.technicaltestbamcolombia.r2dbc.entity.UserCryptocoinEntity;
import org.springframework.stereotype.Service;

@Service
public class MapperEntity {

    public UserCryptocoinEntity toEntity(UserCryptocoinDTO userCryptocoinDTO) {
        return UserCryptocoinEntity.builder()
                .cryptocoinId(userCryptocoinDTO.getCryptocoinId())
                .userId(userCryptocoinDTO.getUserId())
                .amount(userCryptocoinDTO.getAmount())
                .build();
    }

    public UserCryptocoinDTO toDomain(UserCryptocoinEntity userCryptocoinEntity) {
        return UserCryptocoinDTO.builder()
                .cryptocoinId(userCryptocoinEntity.getCryptocoinId())
                .userId(userCryptocoinEntity.getUserId())
                .amount(userCryptocoinEntity.getAmount())
                .build();
    }
}
