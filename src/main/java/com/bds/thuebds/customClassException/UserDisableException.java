package com.bds.thuebds.customClassException;

import com.bds.thuebds.constant.ErrorMessageConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = ErrorMessageConstants.USER_DISABLE)
public class UserDisableException extends RuntimeException {
}
