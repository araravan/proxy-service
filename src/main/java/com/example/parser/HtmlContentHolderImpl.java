package com.example.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.List;
import java.util.stream.Collectors;

public class HtmlContentHolderImpl implements HtmlContentHolder {
    private final Document document;

    public HtmlContentHolderImpl(String html) {
        this.document = Jsoup.parse(html);
    }

    @Override
    public List<HtmlElement> selectElements(String selector) {
        Elements elements = document.select(selector);
        return elements.stream().map(HtmlElementImpl::new).collect(Collectors.toList());
    }

    @Override
    public HtmlElement selectFirstElement(String selector) {
        Element element = document.selectFirst(selector);
        if (element == null) {
            return null;
        }
        return new HtmlElementImpl(element);
    }

    @Override
    public String getHtml() {
        return document.html();
    }
}
