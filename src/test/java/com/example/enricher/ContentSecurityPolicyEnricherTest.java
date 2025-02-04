package com.example.enricher;

import com.example.parser.HtmlContentHolder;
import com.example.parser.HtmlContentHolderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContentSecurityPolicyEnricherTest {

    private ContentSecurityPolicyEnricher underTest;

    @BeforeEach
    void setUp() {
        underTest = new ContentSecurityPolicyEnricher();
    }

    @Test
    void enrich() {
        HtmlContentHolder doc = new HtmlContentHolderImpl("<html><head><meta http-equiv=\"Content-Security-Policy\" /></head></html>");
        assertNotNull(doc.selectFirstElement("meta[http-equiv]"));
        HtmlContentHolder result = underTest.enrich(doc, null);
        assertNull(result.selectFirstElement("meta[http-equiv]"));
    }

    @Test
    void enrichMetaTagDoesNotExist() {
        HtmlContentHolder doc = new HtmlContentHolderImpl("<html><head></head></html>");
        assertNull(doc.selectFirstElement("meta[http-equiv]"));
        HtmlContentHolder result = underTest.enrich(doc, null);
        assertNull(result.selectFirstElement("meta[http-equiv]"));
    }
}