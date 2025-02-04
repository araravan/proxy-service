package com.example.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HtmlContentHolderTest {

    private static final String TEST_HTML = """
            <html>
                <body>
                    <p class='text'>First Paragraph</p>
                    <p class='text'>Second Paragraph</p>
                    <div id='container'>Some content</div>
                </body>
            </html>
            """;
    private HtmlContentHolderImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new HtmlContentHolderImpl(TEST_HTML);
    }

    @Test
    void testSelectElements() {
        List<HtmlElement> elements = underTest.selectElements("p.text");

        assertEquals(2, elements.size());
        assertEquals("First Paragraph", elements.get(0).getText());
        assertEquals("Second Paragraph", elements.get(1).getText());
    }

    @Test
    void testSelectFirstElement() {
        HtmlElement firstElement = underTest.selectFirstElement("p.text");

        assertNotNull(firstElement);
        assertEquals("First Paragraph", firstElement.getText());
    }

    @Test
    void testSelectFirstElement_NotFound() {
        HtmlElement nonExistentElement = underTest.selectFirstElement(".not-exist");

        assertNull(nonExistentElement);
    }

    @Test
    void testGetHtml() {
        String htmlOutput = underTest.getHtml();
        assertNotNull(htmlOutput);
        assertNotNull(underTest.selectFirstElement("div#container"));
    }
}