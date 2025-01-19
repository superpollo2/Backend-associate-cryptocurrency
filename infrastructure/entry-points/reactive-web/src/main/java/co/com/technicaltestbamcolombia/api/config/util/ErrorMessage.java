package co.com.technicaltestbamcolombia.api.config.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorMessage {
    public static final String ERROR_INVALID_PATTERN="Error, el campo %s no tiene el formato esperado";
    public static final String ERROR_INVALID_REQUIRED_SCHEMA="Error, %s es requerido";
    public static final String ERROR_INVALID_ENUM= "Error, %s no es válido para %s, no se encuentra en el dominio de valores %s";
    public static final String ERROR_INVALID_REQUIRED="Error, %s es requerido en el objeto %s";
    public static final String ERROR_INVALID_ANY_OF_REQUIRED="Error, al menos uno de estos objetos %s es requerido en el objeto %s";
    public static final String ERROR_INVALID_TYPE="Error, el tipo de dato ingresado: %s es diferente al esperado %s para el campo %s";
    public static final String ERROR_API="Error Consulte Provedor de la API JsonSchema";
    public static final String ERROR_FIELD_NULL="Error, el valor de %s es obligatorio";
    public static final String ERROR_KEY_NULL="Error, hay claves vacías en la firma, no es permitido";
}
