package dev.earlpadron.contentcalendar.service;

import dev.earlpadron.contentcalendar.exception.ContentAlreadyExistsException;
import dev.earlpadron.contentcalendar.exception.ContentNotFoundException;
import dev.earlpadron.contentcalendar.model.Content;
import dev.earlpadron.contentcalendar.model.DTO.ContentDTO;
import dev.earlpadron.contentcalendar.model.DTO.ContentUpdateRequestDTO;
import dev.earlpadron.contentcalendar.model.enums.Status;

import java.util.List;

public interface ContentService {

    Content getContent(Integer id) throws ContentNotFoundException;
    List<Content> getAllContents();
    Integer postContent(Content content) throws ContentAlreadyExistsException;

    Content updateContent(Integer id, ContentUpdateRequestDTO updateRequest) throws ContentNotFoundException;
    List<Content> findAllContentsContainingTitleKeyword(String keyword) throws ContentNotFoundException;
    void deleteContent(Integer id) throws ContentNotFoundException;

    List<Content> findByStatus(Status status);

}
