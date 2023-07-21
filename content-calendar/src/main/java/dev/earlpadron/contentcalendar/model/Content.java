package dev.earlpadron.contentcalendar.model;

import dev.earlpadron.contentcalendar.model.enums.Status;
import dev.earlpadron.contentcalendar.model.enums.Type;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

//import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "CONTENT")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    //@NotBlank
    //@Pattern(regexp = "[abc]")
    @Column(name = "TITLE")
    String title;
    //@Value(value = "description")
    @Column(name = "DESCRIPTION")
    String desc;
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    Status status;
    @Column(name = "CONTENT_TYPE")
    @Enumerated(EnumType.STRING)
    Type contentType;
    @Column(name = "DATE_CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    LocalDateTime dateCreated;
    @Column(name = "DATE_UPDATED")
    @Temporal(TemporalType.TIMESTAMP)
    LocalDateTime dateUpdated;
    String url;

    public Content(){};//should not be used directly, this is required by Hibernate proxies

    //this constructor will be used when an end-user posts new content
    public Content(String title, String desc, Status status, Type contentType, LocalDateTime dateCreated, LocalDateTime dateUpdated, String url) {
        this.title = title;
        this.desc = desc;
        this.status = status;
        this.contentType = contentType;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Type getContentType() {
        return contentType;
    }

    public void setContentType(Type contentType) {
        this.contentType = contentType;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Content content = (Content) o;
        return Objects.equals(id, content.id) && Objects.equals(title, content.title) && Objects.equals(desc, content.desc) && status == content.status && contentType == content.contentType && Objects.equals(dateCreated, content.dateCreated) && Objects.equals(dateUpdated, content.dateUpdated) && Objects.equals(url, content.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, desc, status, contentType, dateCreated, dateUpdated, url);
    }

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", status=" + status +
                ", contentType=" + contentType +
                ", dateCreated=" + dateCreated +
                ", dateUpdated=" + dateUpdated +
                ", url='" + url + '\'' +
                '}';
    }
}
