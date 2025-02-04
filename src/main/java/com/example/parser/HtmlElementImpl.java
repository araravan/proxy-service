package com.example.parser;

import org.jsoup.nodes.Element;

public class HtmlElementImpl implements HtmlElement {
    private final Element element;

    public HtmlElementImpl(Element element) {
        this.element = element;
    }

    @Override
    public String getText() {
        return element.ownText();
    }

    @Override
    public void setText(String text) {
        element.text(text);
    }

    @Override
    public String getAttribute(String attribute) {
        return element.attr(attribute);
    }

    @Override
    public void setAttribute(String attribute, String value) {
        element.attr(attribute, value);
    }

    @Override
    public void removeElement() {
        element.remove();
    }
}
