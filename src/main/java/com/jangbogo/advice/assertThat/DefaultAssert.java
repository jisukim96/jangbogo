package com.jangbogo.advice.assertThat;

import java.util.List;
import java.util.Optional;

import org.springframework.util.Assert;
import org.springframework.validation.Errors;

import com.jangbogo.advice.error.DefaultAuthenticationException;
import com.jangbogo.advice.error.DefaultException;
import com.jangbogo.advice.error.DefaultNullPointerException;
import com.jangbogo.advice.error.InvalidParameterException;
import com.jangbogo.advice.payload.ErrorCode;

public class DefaultAssert extends Assert {

	
	//유효하지 않은 값
    public static void isTrue(boolean value){
        if(!value){
            throw new DefaultException(ErrorCode.INVALID_CHECK);
        }
    }

    //유효하지 않은 값
    public static void isTrue(boolean value, String message){
        if(!value){
            throw new DefaultException(ErrorCode.INVALID_CHECK, message);
        }
    }

    //메서드에 유효하지 않은 매개변수를 건네받으면 예외발생 
    public static void isValidParameter(Errors errors){
        if(errors.hasErrors()){
            throw new InvalidParameterException(errors);
        }
    }

    //입력받은 객체가 NULL이면 예외발생
    public static void isObjectNull(Object object){
        if(object == null){
            throw new DefaultNullPointerException(ErrorCode.INVALID_CHECK);
        }
    }

    //객체 리스트가 NULL이면 예외발생
    public static void isListNull(List<Object> values){
        if(values.isEmpty()){
            throw new DefaultException(ErrorCode.INVALID_FILE_PATH);
        }
    }

    //파일 경로가 NULL이면 예외 발생
    public static void isListNull(Object[] values){
        if(values == null){
            throw new DefaultException(ErrorCode.INVALID_FILE_PATH);
        }
    }

    //메서드에 입력받은 매개 변수가 올바르지 않으면 예외발생 
    public static void isOptionalPresent(Optional<?> value){
        if(!value.isPresent()){
            throw new DefaultException(ErrorCode.INVALID_PARAMETER);
        }
    }

    //인증 데이터 포멧이 유효하지 않으면 예외발생
    public static void isAuthentication(String message){
        throw new DefaultAuthenticationException(message);
    }

    //제공된 인증키가 유효하지 않으면 예외발생
    public static void isAuthentication(boolean value){
        if(!value){
            throw new DefaultAuthenticationException(ErrorCode.INVALID_AUTHENTICATION);
        }
    }


}
