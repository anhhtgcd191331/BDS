package com.bds.thuebds.customClassException;

import com.bds.thuebds.constant.ErrorMessageConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = ErrorMessageConstants.WRONG_PASSWORD)
public class WrongPasswordException extends RuntimeException {
}
