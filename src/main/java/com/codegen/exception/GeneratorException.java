package com.codegen.exception;

import com.codegen.enums.ErrorCodeEnum;

/**
 * <p>自定义异常</p>
 * @author chenxiaojun
 * @version $Id: GeneratorException.java, v 0.1 2015-11-13 上午10:03:57 chenxiaojun Exp $
 */
public class GeneratorException extends Exception {
    private static final long serialVersionUID = 4852957385720871802L;
    /**
     * 应答码
     */
    private ErrorCodeEnum code;

    /**
     * 构造方法
     * @param code
     */
    public GeneratorException(ErrorCodeEnum code) {
        super(code.getMessage());
        this.code = code;
    }

    /**
     * 构造方法
     * @param code
     * @param message
     */
    public GeneratorException(ErrorCodeEnum code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 构造方法
     * @param code
     * @param message
     * @param cause
     */
    public GeneratorException(ErrorCodeEnum code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ErrorCodeEnum getCode() {
        return code;
    }

    public String getCodeStr() {
        return code.getCode();
    }

    public void setCode(ErrorCodeEnum code) {
        this.code = code;
    }

}
