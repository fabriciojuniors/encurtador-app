package com.encurtador.models.dto;

import org.hibernate.validator.constraints.URL;

public class EncurtadorDto {

    @URL(message = "A URL informada não é válida")
    private String url;

    public EncurtadorDto(String url) {
        this.url = url;
    }

    public EncurtadorDto() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
