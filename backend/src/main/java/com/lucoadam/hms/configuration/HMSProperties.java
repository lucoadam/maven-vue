package com.lucoadam.hms.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("hms")
@Setter
@Getter
@Component
public class HMSProperties {
    private Integer currentYear = 78;
}
