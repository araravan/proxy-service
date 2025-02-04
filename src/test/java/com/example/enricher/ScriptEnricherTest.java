package com.example.enricher;

import com.example.parser.HtmlContentHolder;
import com.example.parser.HtmlContentHolderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScriptEnricherTest {
    private static final String ORIGINAL_URL = "https://quarkus.io";
    private static final String SCRIPT_SRC = "/assets/javascript/hl.js";
    private ScriptEnricher underTest;

    @BeforeEach
    void setUp() {
        underTest = new ScriptEnricher();
    }

    @Test
    void enrich() {
        HtmlContentHolder doc = new HtmlContentHolderImpl("<html><head><script src=\"" + SCRIPT_SRC + "\"></script></head></html>");
        assertEquals(SCRIPT_SRC, doc.selectFirstElement("script").getAttribute("src"));
        HtmlContentHolder result = underTest.enrich(doc, ORIGINAL_URL);
        assertEquals(ORIGINAL_URL + SCRIPT_SRC, result.selectFirstElement("script").getAttribute("src"));
    }
}