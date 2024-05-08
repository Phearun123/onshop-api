package com.kosign.onshopapi.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.kosign.onshopapi.component.AbstractEnumConverter;
import com.kosign.onshopapi.component.GenericEnum;

/**
 * A class can be used for getting YesOrNo enum
 */
public enum ResultCode implements GenericEnum<ResultCode, String> {

    LOGIN("LOGIN"),
    FAIL("FAIL"),
    LOGOUT("LOGOUT"),
    ;

    private final String value;

    ResultCode(String value) {
        this.value = value;
    }

    /**
     * Method fromValue : Check Enum value
     *
     * @param value value that have to check
     * @return enum value
     */
    @JsonCreator
    public static ResultCode fromValue(String value) {
        for(ResultCode my: ResultCode.values()) {
            if(my.value.equals(value)) {
                return my;
            }
        }
        return null;
    }

    /**
     * Method getValue : Get Enum value
     * @return Enum value
     */
    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * Method getLabel : Get Enum Label
     * @return Enum Label
     */
    @Override
    public String getLabel() {

        String label = "(no label)";

        if("LOGIN".equals(value)) label = "RESULT_CODE_LOGIN";
        else if("FAIL".equals(value)) label = "RESULT_CODE_FAIL";
        else if("LOGOUT".equals(value)) label = "RESULT_CODE_LOGOUT";

        return label;
    }

    public static class Converter extends AbstractEnumConverter<ResultCode, String> {
        public Converter() {
            super(ResultCode.class);
        }
    }

}