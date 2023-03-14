package com.bds.thuebds.customClassException;

import com.bds.thuebds.constant.ErrorMessageConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.IM_USED, reason = ErrorMessageConstants.USERNAME_EXISTS)
public class UserDuplicatedUserNameException extends RuntimeException{
}
