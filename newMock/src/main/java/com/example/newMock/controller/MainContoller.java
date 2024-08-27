package com.example.newMock.controller;


import com.example.newMock.model.RequestDTO;
import com.example.newMock.model.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class MainContoller {

    private Logger log = LoggerFactory.getLogger(MainContoller.class);

    ObjectMapper mapper = new ObjectMapper();

    @PostMapping(
            value = "/info/postBalances",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Object postBalances(@RequestBody RequestDTO requestDTO) {
        try {
            String clientId = requestDTO.getClientId();
            char firstDigit = clientId.charAt(0);

            String currency;
            BigDecimal maxlimit;
            String rquid = requestDTO.getRqUID();

            if (firstDigit == '8') {
                currency = new String("USD");
                maxlimit = new BigDecimal(2000);
            } else if (firstDigit == '9') {
                currency = new String("EU");
                maxlimit = new BigDecimal(1000);
            } else {
                currency = new String("RUB");
                maxlimit = new BigDecimal(10000);
            }
            ResponseDTO responseDTO = new ResponseDTO();

            responseDTO.setRqUID(rquid);
            responseDTO.setClientId(clientId);
            responseDTO.setAccount(requestDTO.getAccount());
            responseDTO.setCurrency(currency);
            responseDTO.setBalance(new BigDecimal(777));
            responseDTO.setMaxLimit(maxlimit);

            log.error("********** RequestDTO *********" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestDTO));
            log.error("********** ResponseDTO *********" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseDTO));

            return responseDTO;

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
