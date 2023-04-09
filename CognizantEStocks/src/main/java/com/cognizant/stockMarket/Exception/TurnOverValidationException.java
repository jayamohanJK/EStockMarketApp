package com.cognizant.stockMarket.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.CONFLICT, reason="Turnover should be equal or more than 10CRs")
public class TurnOverValidationException extends Exception {

}
