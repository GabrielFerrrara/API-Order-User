package com.serasa.order.presentation.errors;

public enum GenericErrorsEnum {
    
    BAD_REQUEST("400", "Incorrect Request"),
    NOT_FOUND("404", "URI not mapped to resource"),
    METHOD_NOT_ALLOWED("405", "HTTP method is not supported"), 
    ERROR_GENERIC("500", "Error Generic");

    private String code;
    private String reason;

    GenericErrorsEnum(String code, String reason){
        this.code = code;
        this.reason = reason;
    }

    public String getCode(){return code;}
    public void setCode(String code){this.code = code;}

    public String getReason(){return reason;}
    public void setReason(String reason){this.reason = reason;}

}
