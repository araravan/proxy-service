package com.example.enricher;

import com.example.parser.HtmlContentHolder;
import com.example.parser.HtmlContentHolderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkEnricherTest {
    private static final String ORIGINAL_URL = "https://quarkus.io";
    private static final String LINK_HREF = "https://quarkus.io/extensions/";
    private LinkEnricher underTest;

    @BeforeEach
    void setUp() {
        underTest = new LinkEnricher();
    }

    @Test
    void enrich() {
        HtmlContentHolder doc = new HtmlContentHolderImpl("<html><body><a href=\"" + LINK_HREF + "\"></a></head></html>");
        assertEquals(LINK_HREF, doc.selectFirstElement("a").getAttribute("href"));
        HtmlContentHolder result = underTest.enrich(doc, ORIGINAL_URL);
        assertEquals(LINK_HREF.replace(ORIGINAL_URL, ""), result.selectFirstElement("a").getAttribute("href"));
    }
}