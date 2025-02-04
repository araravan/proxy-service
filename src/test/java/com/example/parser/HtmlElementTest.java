package com.example.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HtmlElementTest {

    private HtmlElementImpl underTest;
    private Document document;
    private Element element;

    @BeforeEach
    void setUp() {
        document = Jsoup.parse("<html><body><p id='para1' class='text'>Hello World</p></body></html>");
        element = document.selectFirst("p");
        underTest = new HtmlElementImpl(element);
    }

    @Test
    void testGetText() {
        assertEquals("Hello World", underTest.getText());
    }

    @Test
    void testSetText() {
        underTest.setText("New Text");
        assertEquals("New Text", underTest.getText());
    }

    @Test
    void testGetAttribute() {
        assertEquals("text", underTest.getAttribute("class"));
    }

    @Test
    void testSetAttribute() {
        underTest.setAttribute("class", "new-class");
        assertEquals("new-class", underTest.getAttribute("class"));
    }

    @Test
    void testRemoveElement() {
        underTest.removeElement();
        assertNull(document.selectFirst("p"));
    }
}