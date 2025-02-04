package com.example.enricher;

import com.example.parser.HtmlContentHolder;
import com.example.parser.HtmlContentHolderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TextEnricherTest {
    private static final String ORIGINAL_URL = "https://quarkus.io";
    private TextEnricher underTest;

    @BeforeEach
    void setUp() {
        underTest = new TextEnricher();
    }

    @Test
    void enrich() {
        HtmlContentHolder doc = new HtmlContentHolderImpl("<p>Built on a robust reactive core</p>");
        HtmlContentHolder result = underTest.enrich(doc, ORIGINAL_URL);
        assertTrue(result.getHtml().contains("robust™"));
    }
}