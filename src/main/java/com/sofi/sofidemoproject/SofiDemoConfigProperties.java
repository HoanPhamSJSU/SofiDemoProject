package com.sofi.sofidemoproject;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("api")
public record SofiDemoConfigProperties(String key, String limit, String offSet, String urlSearch, int maxReturn) {
}
