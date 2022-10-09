package com.example.simpleannotation.model.descriptors;

import java.util.ArrayList;
import java.util.List;

public final class CodeBlockDescriptor {
    private final List<String> lines;

    public CodeBlockDescriptor () {
        this.lines = new ArrayList<>();
    }

    public CodeBlockDescriptor addLine(String line) {
        this.lines.add(line);
        return this;
    }

    public boolean isNotEmpty () {
        return !this.lines.isEmpty();
    }

    @Override
    public String toString() {
        return String.join("\n", lines);
    }
}
