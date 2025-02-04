package com.example.enricher;

import com.example.parser.HtmlContentHolder;
import com.example.parser.HtmlElement;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TextEnricher implements ContentEnricher {

    @Override
    public HtmlContentHolder enrich(HtmlContentHolder doc, String originalUrl) {
        for (HtmlElement element : doc.selectElements("*")) {
            if (!element.getText().isEmpty()) {
                String modifiedText = element.getText().replaceAll("\\b(\\w{6})\\b", "$1™");
                element.setText(modifiedText);
            }
        }
        return doc;
    }
}
