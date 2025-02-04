package com.example.enricher;

import com.example.parser.HtmlContentHolder;

public interface ContentEnricher {
    HtmlContentHolder enrich(HtmlContentHolder doc, String originalUrl);
}
