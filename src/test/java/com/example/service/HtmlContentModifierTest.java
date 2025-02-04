package com.example.service;

import com.example.client.WebPageClient;
import com.example.config.AppConfig;
import com.example.enricher.ContentEnricher;
import com.example.parser.HtmlContentHolder;
import com.example.parser.HtmlContentHolderImpl;
import jakarta.enterprise.inject.Instance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

class HtmlContentModifierTest {
    private static final String ORIGINAL_URL = "https://quarkus.io/";
    private static final String PATH = "quarkus3";

    private Instance<ContentEnricher> mockContentEnrichers;
    private ContentEnricher mockEnricher1;
    private ContentEnricher mockEnricher2;
    private WebPageClient mockWebPageClient;
    private AppConfig mockAppConfig;
    private HtmlContentModifier underTest;

    @BeforeEach
    void setUp() {
        mockWebPageClient = mock(WebPageClient.class);
        mockAppConfig = mock(AppConfig.class);
        mockEnricher1 = mock(ContentEnricher.class);
        mockEnricher2 = mock(ContentEnricher.class);
        mockContentEnrichers = mockInstance(mockEnricher1, mockEnricher2);

        underTest = new HtmlContentModifier();
        underTest.appConfig = mockAppConfig;
        underTest.webPageClient = mockWebPageClient;
        underTest.contentEnrichers = mockContentEnrichers;

        when(mockAppConfig.getOriginalUrl()).thenReturn("https://quarkus.io/");
    }

    @Test
    void modifyContent() {
        String originalContent = "<html><body><p>Hello, Quarkus!</p></body></html>";
        String url = ORIGINAL_URL + PATH;
        when(mockWebPageClient.get(url)).thenReturn(originalContent);

        when(mockEnricher1.enrich(any(HtmlContentHolder.class), eq(url)))
                .thenAnswer(invocation -> {
                    HtmlContentHolder holder = invocation.getArgument(0);
                    return new HtmlContentHolderImpl(holder.getHtml());
                });

        when(mockEnricher2.enrich(any(HtmlContentHolder.class), eq(url)))
                .thenAnswer(invocation -> {
                    HtmlContentHolder holder = invocation.getArgument(0);
                    return new HtmlContentHolderImpl(holder.getHtml());
                });

        underTest.modifyContent(PATH);

        // Verify `getOriginalUrl` was called once
        verify(mockAppConfig).getOriginalUrl();

        // Verify `WebPageClient.get(url)` was called once
        verify(mockWebPageClient).get(url);

        // Verify each enricher was called once
        verify(mockEnricher1).enrich(any(HtmlContentHolder.class), eq(url));
        verify(mockEnricher2).enrich(any(HtmlContentHolder.class), eq(url));
    }

    @SafeVarargs
    private static <T> Instance<T> mockInstance(T... beans) {
        Instance<T> mockInstance = mock(Instance.class);
        when(mockInstance.iterator()).thenReturn(List.of(beans).iterator());
        return mockInstance;
    }
}