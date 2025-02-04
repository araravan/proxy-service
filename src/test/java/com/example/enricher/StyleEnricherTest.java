package com.example.enricher;

import com.example.parser.HtmlContentHolder;
import com.example.parser.HtmlContentHolderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StyleEnricherTest {
    private static final String ORIGINAL_URL = "https://quarkus.io";
    private static final String LINK_HREF = "/assets/css/main.css?2021-07-29";
    private StyleEnricher underTest;

    @BeforeEach
    void setUp() {
        underTest = new StyleEnricher();
    }

    @Test
    void enrich() {
        HtmlContentHolder doc = new HtmlContentHolderImpl("<html><head><link rel=\"stylesheet\" href=\"" + LINK_HREF + "\"></script></head></html>");
        assertEquals(LINK_HREF, doc.selectFirstElement("link").getAttribute("href"));
        HtmlContentHolder result = underTest.enrich(doc, ORIGINAL_URL);
        assertEquals(ORIGINAL_URL + LINK_HREF, result.selectFirstElement("link").getAttribute("href"));
    }
}