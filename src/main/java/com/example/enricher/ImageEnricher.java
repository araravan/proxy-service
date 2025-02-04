package com.example.enricher;

import com.example.parser.HtmlContentHolder;
import com.example.parser.HtmlElement;
import jakarta.enterprise.context.ApplicationScoped;

import java.net.URI;
import java.util.List;

@ApplicationScoped
public class ImageEnricher implements ContentEnricher{
    @Override
    public HtmlContentHolder enrich(HtmlContentHolder doc, String originalUrl) {
        List<HtmlElement> links = doc.selectElements("img[src]");
        for (HtmlElement link : links) {
            String src = link.getAttribute("src");
            if (src.startsWith("/")) {
                link.setAttribute("src", URI.create(originalUrl).resolve(src).toString());
            }
        }
        return doc;
    }
}
