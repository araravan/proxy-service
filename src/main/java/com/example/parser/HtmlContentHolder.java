package com.example.parser;

import java.util.List;

public interface HtmlContentHolder {
    List<HtmlElement> selectElements(String selector);
    HtmlElement selectFirstElement(String selector);
    String getHtml();
}
