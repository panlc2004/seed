package com.czy.seed.mvc.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by 003914[panlc] on 2017-07-25.
 */
@ConfigurationProperties(prefix = "seed")
public class SeedConfigProperties {

    private String[] urlPermit;

    private String[] webSecurityIgnore;

    public String[] getUrlPermit() {
        return urlPermit;
    }

    public void setUrlPermit(String[] urlPermit) {
        this.urlPermit = urlPermit;
    }

    public String[] getWebSecurityIgnore() {
        return webSecurityIgnore;
    }

    public void setWebSecurityIgnore(String[] webSecurityIgnore) {
        this.webSecurityIgnore = webSecurityIgnore;
    }
}
