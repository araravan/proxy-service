package com.example.service;

import com.example.client.WebPageClient;
import com.example.config.AppConfig;
import com.example.enricher.ContentEnricher;
import com.example.parser.HtmlContentHolder;
import com.example.parser.HtmlContentHolderImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class HtmlContentModifier {
    @Inject
    AppConfig appConfig;
    private static final String ORIGINAL_URL = "https://quarkus.io/";
    @Inject
    Instance<ContentEnricher> contentEnrichers;
    @RestClient
    WebPageClient webPageClient;

    public String modifyContent(String path) {
        String url = appConfig.getOriginalUrl() + path;
        String pageContent = webPageClient.get(url);
        HtmlContentHolder htmlContentHolder = new HtmlContentHolderImpl(pageContent);

        for (ContentEnricher contentEnricher : contentEnrichers) {
            htmlContentHolder = contentEnricher.enrich(htmlContentHolder, url);
        }
        return htmlContentHolder.getHtml();
    }
}
