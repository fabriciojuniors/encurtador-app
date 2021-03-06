package com.encurtador.models;

import com.encurtador.models.dto.EncurtadorDto;
import com.encurtador.utils.Criptografia;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "encurtador")
public class Encurtador implements Serializable {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "url")
    @URL(message = "A URL informada não é valida")
    private String URL;

    @Column
    private String title;

    @Column(name = "encoded_url")
    private String encodedURL;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    private LocalDateTime updatedAt;

    @Column
    private long views;

    public Encurtador(long id, String URL, String title, String encodedURL, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.URL = URL;
        this.title = title;
        this.encodedURL = encodedURL;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Encurtador(long id, String URL, String title, String encodedURL) {
        this.id = id;
        this.URL = URL;
        this.title = title;
        this.encodedURL = encodedURL;
        this.createdAt = LocalDateTime.now();
        this.views = 0;
    }

    public Encurtador() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEncodedURL() {
        return encodedURL;
    }

    public void setEncodedURL(String encodedURL) {
        this.encodedURL = encodedURL;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public void incrementViews(){
        this.views++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Encurtador that = (Encurtador) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return title;
    }

    public static class Builder {

        public static Encurtador fromDto(EncurtadorDto dto) throws IOException {
            return new Encurtador(0, dto.getUrl(), getTitle(dto.getUrl()), encodeURL(dto.getUrl()));
        }

        private static String encodeURL(String url){
            String encodedURL = Criptografia.md5(url);
            System.out.println("EncodedURL: " + encodedURL);
            return encodedURL;
        }

        private static String getTitle(String url) throws IOException {
            HTMLEditorKit htmlKit = new HTMLEditorKit();
            HTMLDocument htmlDoc = (HTMLDocument) htmlKit.createDefaultDocument();
            HTMLEditorKit.Parser parser = new ParserDelegator();
            parser.parse(new InputStreamReader(new java.net.URL(url).openStream()),
                    htmlDoc.getReader(0), true);

            return (String) htmlDoc.getProperty("title");

        }

    }

}
