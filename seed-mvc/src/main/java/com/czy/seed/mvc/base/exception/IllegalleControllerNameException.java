package com.czy.seed.mvc.base.exception;

public class IllegalleControllerNameException extends RuntimeException {

    public IllegalleControllerNameException() {
        super("Controller名称必须符合ModelNameController规则");
    }

}
