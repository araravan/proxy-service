package com.example.enricher;

import com.example.parser.HtmlContentHolder;
import com.example.parser.HtmlElement;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class LinkEnricher implements ContentEnricher {

    @Override
    public HtmlContentHolder enrich(HtmlContentHolder doc, String originalUrl) {
        List<HtmlElement> links = doc.selectElements("a[href]");
        for (HtmlElement link : links) {
            String src = link.getAttribute("href");
            if (src.startsWith(originalUrl)) {
                link.setAttribute("href", src.replace(originalUrl, ""));
            }
        }
        return doc;
    }
}

