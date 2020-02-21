package com.lucoadam.hms.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Symbol Number or DOB is invalid.")
public class InvalidSymbolNoAndDobCombination  extends RuntimeException{
}
