package com.camacho.simpleannotation.builders;

import com.camacho.simpleannotation.annotations.Builder;
import java.util.List;
import java.util.Objects;

@Builder
public class FakeClassNoDefaultConstructor {
    private int i;
    private double d;
    private String s;
    private List<String> strings;
    private Object object;

    public FakeClassNoDefaultConstructor(int i, double d, String s, List<String> strings) {
        this.i = i;
        this.d = d;
        this.s = s;
        this.strings = strings;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public List<String> getStrings() {
        return strings;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FakeClassNoDefaultConstructor that = (FakeClassNoDefaultConstructor) o;
        return i == that.i 
            && Double.compare(that.d, d) == 0 
            && Objects.equals(s, that.s) 
            && Objects.equals(strings, that.strings) 
            && Objects.equals(object, that.object);
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, d, s, strings, object);
    }
}
