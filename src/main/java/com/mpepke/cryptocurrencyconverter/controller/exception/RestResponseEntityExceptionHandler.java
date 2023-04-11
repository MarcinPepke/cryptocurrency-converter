package com.mpepke.cryptocurrencyconverter.controller.exception;

import com.mpepke.cryptocurrencyconverter.model.CryptocurrencyEnum;
import com.mpepke.cryptocurrencyconverter.model.CurrencyEnum;
import com.mpepke.cryptocurrencyconverter.service.exception.CryptocurrencyNotSupportedException;
import com.mpepke.cryptocurrencyconverter.service.exception.CurrencyNotSupportedException;
import com.mpepke.cryptocurrencyconverter.service.exception.ExternalApiResponseInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({CryptocurrencyNotSupportedException.class})
    public ResponseEntity<?> handlerCryptocurrencyNotSupportedException() {
        return new ResponseEntity<>("conversion from this cryptocurrency is not supported yet, supported - " + Arrays.toString(CryptocurrencyEnum.values()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({CurrencyNotSupportedException.class})
    public ResponseEntity<?> handlerCurrencyNotSupportedException() {
        return new ResponseEntity<>("conversion to this currency is not supported yet, supported -  " + Arrays.toString(CurrencyEnum.values()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({RequestBodyIsNullException.class})
    public ResponseEntity<?> handlerRequestBodyIsNullException() {
        return new ResponseEntity<>("request body is invalid", HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler({ExternalApiResponseInvalidException.class})
    public ResponseEntity<?> handlerExternalApiResponseInvalidException() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
