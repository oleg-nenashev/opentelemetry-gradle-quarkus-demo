package org.acme;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HackerNewsItem {
    String id;
    String title;
    String url;
    String by;
    Date time;
    Integer descendants;
}
