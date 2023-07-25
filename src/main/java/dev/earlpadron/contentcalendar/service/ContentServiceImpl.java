package dev.earlpadron.contentcalendar.service;

import dev.earlpadron.contentcalendar.exception.ContentAlreadyExistsException;
import dev.earlpadron.contentcalendar.exception.ContentNotFoundException;
import dev.earlpadron.contentcalendar.model.Content;
import dev.earlpadron.contentcalendar.model.DTO.ContentUpdateRequestDTO;
import dev.earlpadron.contentcalendar.model.enums.Status;
import dev.earlpadron.contentcalendar.repository.ContentRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentRepository repository;

    @Autowired
    private Environment environment;

    @Override
    public Content getContent(Integer id) throws ContentNotFoundException {
        return  repository.findById(id)
                .orElseThrow(
                        () -> new ContentNotFoundException(
                                environment.getProperty("SERVICE.CONTENT_NOT_FOUND") + " : " + id)
                );
    }

    @Override
    public List<Content> getAllContents() {
        return repository.findAll()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public Integer postContent(Content content) throws ContentAlreadyExistsException {
        if(repository.findByTitleContains(content.getTitle()) == null){
            return repository.save(content).getId();
        } else {
            throw new ContentAlreadyExistsException(environment.getProperty("SERVICE.CONTENT_ALREADY_EXISTS"));
        }
    }

//    @Override
//    public Integer postContent(ContentDTO contentDTO) throws ContentAlreadyExistsException {
//        //must validate before saving new content -- DON'T FORGET
//        if(repository.findByTitleContains(contentDTO.title()) == null){
//            Content content = new Content(
//                    contentDTO.title(),
//                    contentDTO.desc(),
//                    contentDTO.status(),
//                    contentDTO.contentType(),
//                    contentDTO.dateCreated(),
//                    contentDTO.dateUpdated(),
//                    contentDTO.url());
//             return repository.save(content).getId();
//
//        }
//        else {
//            throw new ContentNotFoundException(environment.getProperty("SERVICE.CONTENT_ALREADY_EXISTS"));
//        }
//    }

    @Override
    public Content updateContent(Integer id, ContentUpdateRequestDTO updateRequest) throws ContentNotFoundException {
        Content contentToUpdate = repository.findById(id)
                .orElseThrow(() -> new ContentNotFoundException(environment.getProperty("SERVICE.CONTENT_NOT_FOUND")));

        //should validate this updateRequestDTO first
        if(contentToUpdate.getTitle().equals(updateRequest.title())){
            contentToUpdate.setTitle((updateRequest.title()));
            contentToUpdate.setDesc(updateRequest.desc());
            contentToUpdate.setStatus((updateRequest.status()));
            contentToUpdate.setContentType(updateRequest.contentType());
            contentToUpdate.setDateCreated(updateRequest.dateCreated());
            contentToUpdate.setDateUpdated(updateRequest.dateUpdated());
            contentToUpdate.setUrl(updateRequest.url());
            return repository.save(contentToUpdate);
        } else {
            throw new ContentNotFoundException(environment.getProperty("SERVICE.CONTENT_NOT_FOUND"));
        }
    }

    @Override
    public List<Content> findAllContentsContainingTitleKeyword(String keyword) {
        return repository.findAllByTitleContains(keyword)
                .stream()
                .collect(Collectors.toList());
    }

    /**
     * remember when deleting, if there are any child objects, set them to null first to avoid deleting them along with the parent object
     * @param id
     * @throws ContentNotFoundException
     */
    @Override
    public void deleteContent(Integer id) throws ContentNotFoundException {
        Content content = repository.findById(id)
                                    .orElseThrow(() -> new ContentNotFoundException(environment.getProperty("SERVICE.CONTENT_DETAILS_NOT_FOUND")));
         repository.delete(content);
    }

    @Override
    public List<Content> findByStatus(Status status){
        return  repository.listByStatus(status).stream().collect(Collectors.toList());
    }

    /**
     * Service Layer method work with Entities by receiving them from Repository Layer and send DTOs of those entities to the Controller Layer
     * Remember to use a mapper class/library to translate entities into DTOs
     */
}
