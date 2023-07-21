package dev.earlpadron.contentcalendar.service;

import dev.earlpadron.contentcalendar.exception.ContentAlreadyExistsException;
import dev.earlpadron.contentcalendar.exception.ContentNotFoundException;
import dev.earlpadron.contentcalendar.model.DTO.ContentDTO;
import dev.earlpadron.contentcalendar.model.DTO.ContentUpdateRequestDTO;
import dev.earlpadron.contentcalendar.model.enums.Status;

import java.util.List;

public interface ContentService {

    ContentDTO getContent(Integer id) throws ContentNotFoundException;
    List<ContentDTO> getAllContents();
    Integer postContent(ContentDTO contentDTO) throws ContentAlreadyExistsException;

    void updateContent(Integer id, ContentUpdateRequestDTO updateRequest) throws ContentNotFoundException;
    List<ContentDTO> findAllContentsContainingTitleKeyword(String keyword) throws ContentNotFoundException;
    void deleteContent(Integer id) throws ContentNotFoundException;

    List<ContentDTO> findByStatus(Status status);

}
