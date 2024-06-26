package com.kosign.onshopapi.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "file")
public class FileInfoConfig {

    private String serverPath;

    private String clientPath;

    private String baseUrl;

}
