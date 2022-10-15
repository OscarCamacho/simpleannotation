package com.camacho.simpleannotation.model.descriptors;

import java.util.ArrayList;
import java.util.List;

public final class CodeBlockDescriptor {
    private final List<String> lines;
    private int indentationLevel;

    public CodeBlockDescriptor() {
        this.lines = new ArrayList<>();
    }

    public CodeBlockDescriptor addLine(String line) {
        this.lines.add(line);
        return this;
    }

    public CodeBlockDescriptor setCodeIndentationLevel(int i) {
        this.indentationLevel = i;
        return this;
    }

    public boolean isNotEmpty() {
        return !this.lines.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder newLine = new StringBuilder("\n");
        StringBuilder lastLine = new StringBuilder("\n");
        for (int i = 0; i < indentationLevel; i++) {
            newLine.append("\t");
            if (i < indentationLevel -1) {
                lastLine.append("\t");
            }
        }
        List<String> linesToPrint = new ArrayList<>();
        linesToPrint.add("{");
        linesToPrint.addAll(lines);
        return String.join(newLine, linesToPrint) + String.join("", lastLine, "}");
    }
}
