package com.kosign.onshopapi.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.kosign.onshopapi.component.AbstractEnumConverter;
import com.kosign.onshopapi.component.GenericEnum;

public enum UserType implements GenericEnum<UserType, String> {

    NORMAL_USER("1"),
    API_USER("2"),
    ;

    private final String value;

    UserType(String value) {
        this.value = value;
    }

    /**
     * Method fromValue : Check Enum value
     *
     * @param value value that have to check
     * @return enum value
     */
    @JsonCreator
    public static UserType fromValue(String value) {
        for (UserType my : UserType.values()) {
            if (my.value.equals(value)) {
                return my;
            }
        }

        return null;
    }

    /**
     * Method getValue : Get Enum value
     *
     * @return Enum value
     */
    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * Method getLabel : Get Enum Label
     *
     * @return Enum Label
     */
    @Override
    public String getLabel() {
        String label = "(no label)";

        if ("1".equals(value)) label = "Normal User";
        else if ("2".equals(value)) label = "Api User";

        return label;
    }

    public static class Converter extends AbstractEnumConverter<UserType, String> {

        public Converter() {
            super(UserType.class);
        }

    }

}
