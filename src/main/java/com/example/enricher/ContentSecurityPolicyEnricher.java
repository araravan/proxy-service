package com.example.enricher;

import com.example.parser.HtmlContentHolder;
import com.example.parser.HtmlElement;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * TODO Instead of removing the meta tag find a better way to add original URL.
 * For example parse the content and add needed information.
 */
@ApplicationScoped
public class ContentSecurityPolicyEnricher implements ContentEnricher{
    @Override
    public HtmlContentHolder enrich(HtmlContentHolder doc, String originalUrl) {
        HtmlElement metaSecurity = doc.selectFirstElement("meta[http-equiv]");
        if (metaSecurity != null) {
            metaSecurity.removeElement();
        }
        return doc;
    }
}
