package com.example.enricher;

import com.example.parser.HtmlContentHolder;
import com.example.parser.HtmlElement;
import jakarta.enterprise.context.ApplicationScoped;

import java.net.URI;
import java.util.List;

@ApplicationScoped
public class ScriptEnricher implements ContentEnricher{
    @Override
    public HtmlContentHolder enrich(HtmlContentHolder doc, String originalUrl) {
        List<HtmlElement> scripts = doc.selectElements("script[src]");
        for (HtmlElement script : scripts) {
            String src = script.getAttribute("src");
            if (src.startsWith("/")) {
                script.setAttribute("src", URI.create(originalUrl).resolve(src).toString());
            }
        }
        return doc;
    }
}
