package com.example.enricher;

import com.example.parser.HtmlContentHolder;
import com.example.parser.HtmlElement;
import jakarta.enterprise.context.ApplicationScoped;
import java.net.URI;
import java.util.List;

@ApplicationScoped
public class StyleEnricher implements ContentEnricher {

    @Override
    public HtmlContentHolder enrich(HtmlContentHolder doc, String originalUrl) {
        List<HtmlElement> styles = doc.selectElements("link[href]");
        for (HtmlElement style : styles) {
            String src = style.getAttribute("href");
            if (src.startsWith("/") || src.startsWith(originalUrl)) {
                style.setAttribute("href", URI.create(originalUrl).resolve(src).toString());
            }
        }
        return doc;
    }
}

