package com.example.simpleannotation;

import com.example.simpleannotation.annotations.Builder;
import java.util.List;

@Builder
public class FakeAnnotatedClass {
    private Integer intAttribute;
    private String stringAttribute;
    private Boolean boolAttribute;
    private List<String> listOfStringsAttribute;
    public FakeAnnotatedClass() {}

    public Integer getIntAttribute() {
        return intAttribute;
    }

    public void setIntAttribute(Integer intAttribute) {
        this.intAttribute = intAttribute;
    }

    public String getStringAttribute() {
        return stringAttribute;
    }

    public void setStringAttribute(String stringAttribute) {
        this.stringAttribute = stringAttribute;
    }

    public Boolean getBoolAttribute() {
        return boolAttribute;
    }

    public void setBoolAttribute(Boolean boolAttribute) {
        this.boolAttribute = boolAttribute;
    }

    public List<String> getListOfStringsAttribute() {
        return listOfStringsAttribute;
    }

    public void setListOfStringsAttribute(List<String> listOfStringsAttribute) {
        this.listOfStringsAttribute = listOfStringsAttribute;
    }
}
