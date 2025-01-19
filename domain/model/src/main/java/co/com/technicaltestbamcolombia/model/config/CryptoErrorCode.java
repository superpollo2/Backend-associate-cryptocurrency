package co.com.technicaltestbamcolombia.model.config;

import lombok.Getter;

@Getter
public enum CryptoErrorCode {

        CRB000("CR-B000", "ERROR CONSULTA");

        private String errorCode;
        private String errorTitle;

    CryptoErrorCode(String errorCode, String errorTitle) {
        this.errorCode = errorCode;
        this.errorTitle = errorTitle;
    }
}
