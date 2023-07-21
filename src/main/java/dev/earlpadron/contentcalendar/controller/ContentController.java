package dev.earlpadron.contentcalendar.controller;

import dev.earlpadron.contentcalendar.exception.ContentAlreadyExistsException;
import dev.earlpadron.contentcalendar.exception.ContentNotFoundException;
import dev.earlpadron.contentcalendar.model.DTO.ContentDTO;
import dev.earlpadron.contentcalendar.model.DTO.ContentUpdateRequestDTO;
import dev.earlpadron.contentcalendar.model.enums.Status;
import dev.earlpadron.contentcalendar.service.ContentService;
import dev.earlpadron.contentcalendar.service.ContentServiceImpl;
import jakarta.validation.Valid;

import jakarta.validation.constraints.Min;
//import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/content")
@Validated
@CrossOrigin
public class ContentController {

    /**
     * controllers take requests and send responses
     *  crud operations will be found here
     *
     *
     *  // private final ContentCollectionRepository repository;
     * //    private final ContentRepository repository;
     * //
     * //    //constructor dependency injection
     * //    public ContentController(ContentRepository repository) {
     * //        this.repository = repository;
     * //    }
     * //
     */
    @Autowired
    private ContentService contentService;

   @Autowired
   Environment environment; //should produce messages in each response ex. String message = environment.getProperty("API.ALLOCATION_SUCCESS") + ": " projectId;

    @GetMapping("/{id}")
    public ResponseEntity<ContentDTO> getContent(@PathVariable
                                                 @Min(value = 1,
                                                      message = "{content.id.invalid}")
                                                      Integer id) throws ContentNotFoundException {
        ContentDTO content = contentService.getContent(id);
        return new ResponseEntity<ContentDTO>(content, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ContentDTO>> getAllContents(){
        List<ContentDTO> contentList = contentService.getAllContents();
        return new ResponseEntity<List<ContentDTO>>(contentList, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteContent(@PathVariable Integer id) throws ContentNotFoundException{
        contentService.deleteContent(id);
        return new ResponseEntity<String>(environment.getProperty("API.DELETE_SUCCESS") + " : " + id, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> postContent(@RequestBody @Valid ContentDTO contentDTO) throws ContentAlreadyExistsException {
        Integer id = contentService.postContent(contentDTO);
        String message = environment.getProperty("API.POST_SUCCESS");
        return new ResponseEntity<String>(message, HttpStatus.CREATED);
    }

    @GetMapping("/find/title/{keyword}")
    public ResponseEntity<List<ContentDTO>> getAllContentsContainingTitleKeyword(@PathVariable String keyword) throws ContentNotFoundException{
        List<ContentDTO> contentList = contentService.findAllContentsContainingTitleKeyword(keyword);
        return new ResponseEntity<List<ContentDTO>>(contentList, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateContent(@RequestBody @Valid ContentUpdateRequestDTO contentUpdateRequestDTO,
                                                @PathVariable(name = "id")
                                                 @Min(value = 1, message = "{content.valid.id}")
                                                Integer id)
                                                throws ContentNotFoundException{
        if(contentService.getContent(id) != null) {
            contentService.updateContent(id, contentUpdateRequestDTO);
            return new ResponseEntity<String>(environment.getProperty("API.SUCCESSFULLY_UPDATED") + " : " + id, HttpStatus.ACCEPTED); //should be handling these exceptions in Aspect Class w/ advices and logged
        }
        return new ResponseEntity<String>(environment.getProperty("API.FAILED_UPDATE") + " : " + id, HttpStatus.BAD_REQUEST);// and should be an environment property
    }

    @GetMapping("/find/status/{status}")
    public ResponseEntity<List<ContentDTO>> findByStatus(@PathVariable Status status){
        List<ContentDTO> contentDTOList = contentService.findByStatus(status);
        return new ResponseEntity<List<ContentDTO>>(contentDTOList, HttpStatus.OK);
    }


//
////    @ResponseStatus(HttpStatus.NO_CONTENT)
////    @PutMapping("/{id}")
////    public void update(@RequestBody @Valid Content content, @PathVariable Integer id){
////        if(!repository.existsById(id)){
////            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Content not found!");
////        }
////        repository.save(content);
////    }
//

//
//
//    @GetMapping("/filter/status/{status}")
//    public List<Content> findByStatus(@PathVariable Status status){
//        return repository.listByStatus(status);
//    }
}
