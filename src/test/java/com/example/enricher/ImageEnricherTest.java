package com.example.enricher;

import com.example.parser.HtmlContentHolder;
import com.example.parser.HtmlContentHolderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImageEnricherTest {
    private static final String IMG_SRC = "/assets/images/quarkus_logo_horizontal_rgb_600px_reverse.png";
    private static final String ORIGINAL_URL = "https://quarkus.io";
    private ImageEnricher underTest;

    @BeforeEach
    void setUp() {
        underTest = new ImageEnricher();
    }

    @Test
    void enrich() {
        HtmlContentHolder doc = new HtmlContentHolderImpl("<html><body><img src=\"" + IMG_SRC + "\" /></body></html>");
        assertEquals(IMG_SRC, doc.selectFirstElement("img").getAttribute("src"));
        HtmlContentHolder result = underTest.enrich(doc, ORIGINAL_URL);
        assertEquals(ORIGINAL_URL + IMG_SRC, result.selectFirstElement("img").getAttribute("src"));
    }
}