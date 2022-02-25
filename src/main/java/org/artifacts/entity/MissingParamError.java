package org.artifacts.entity;

public class MissingParamError {
    private String paramName;
    private String message;

    public MissingParamError(String paramName, String message)
    {
        this.message = message;
        this.paramName = paramName;
    }

    public MissingParamError(String paramName)
    {
        this.message = "Check the submitted data!";
        this.paramName = paramName;
    }

}
