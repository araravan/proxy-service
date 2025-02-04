package com.example.parser;

public interface HtmlElement {
    String getText();
    void setText(String text);
    String getAttribute(String attribute);
    void setAttribute(String attribute, String value);
    void removeElement();
}
