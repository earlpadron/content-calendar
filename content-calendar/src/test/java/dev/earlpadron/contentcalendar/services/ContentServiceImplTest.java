package dev.earlpadron.contentcalendar.services;
import dev.earlpadron.contentcalendar.model.Content;
import dev.earlpadron.contentcalendar.model.DTO.ContentDTO;
import dev.earlpadron.contentcalendar.model.enums.Status;
import dev.earlpadron.contentcalendar.model.enums.Type;
import dev.earlpadron.contentcalendar.repository.ContentRepository;
import dev.earlpadron.contentcalendar.service.ContentDTOMapper;
import dev.earlpadron.contentcalendar.service.ContentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;


/**
 * @SpringBootTest allows dependency in jection of required components that needs to be tested inside the application
 * > this annotation also enables mocking and stubbing using mockito
 */
@SpringBootTest
public class ContentServiceImplTest {

    @InjectMocks //this annotation creates all available mock objects that can be injected
    //must explicitly use "new" to create the object whose method is to be tested
    ContentServiceImpl contentService = new ContentServiceImpl();

    @Mock //creates the mock objects or the "dependent" objects of the class being primarily tested
    ContentRepository contentRepository;
    @Mock
    ContentDTOMapper contentDTOMapper;
    @Mock
    Environment environment;

    @Test
    void postContentValidTest(){
        //given - prepare data
        Content content = new Content();
        content.setTitle("test content title name");
        content.setDesc("test content description for a new content");
        content.setStatus(Status.PUBLISHED);
        content.setContentType(Type.ARTICLE);
        content.setDateCreated(LocalDateTime.now());
        content.setUrl("http://test.com");


        //when - call the methods
        Mockito.when(contentRepository.save(Mockito.any(Content.class))).thenReturn(content);
        Content newContent = contentRepository.save(content);

        //then - assert
        Assertions.assertNotNull(newContent);
        Assertions.assertEquals(newContent.getTitle(), content.getTitle());
        Mockito.verify(contentRepository, Mockito.times(1)).save(Mockito.any(Content.class));
    }

    @Test
    void getAllContentsValidTest(){
        //given - prepare data
        Content content = new Content();
        content.setId(1);
        content.setTitle("test content title name");
        content.setDesc("test content description for a new content");
        content.setStatus(Status.PUBLISHED);
        content.setContentType(Type.ARTICLE);
        content.setDateCreated(LocalDateTime.now());
        content.setDateUpdated(LocalDateTime.of(2099, Month.DECEMBER, 15,12,12));
        content.setUrl("http://test.com");

        Content content2 = new Content();
        content2.setId(2);
        content2.setTitle("test content title name2");
        content2.setDesc("test content description for a new content2");
        content2.setStatus(Status.PUBLISHED);
        content2.setContentType(Type.ARTICLE);
        content2.setDateCreated(LocalDateTime.now());
        content2.setDateUpdated(LocalDateTime.of(2099, Month.DECEMBER, 15,12,12));
        content2.setUrl("http://test2.com");

        ContentDTO contentDTO = new ContentDTO(
                content.getTitle(),
                content.getDesc(),
                content.getStatus(),
                content.getContentType(),
                content.getDateCreated(),
                content.getDateUpdated(),
                content.getUrl()
        );

        ContentDTO contentDTO2 = new ContentDTO(
                content2.getTitle(),
                content2.getDesc(),
                content2.getStatus(),
                content2.getContentType(),
                content2.getDateCreated(),
                content2.getDateUpdated(),
                content2.getUrl()
        );
        //when
        List<Content> contentList = new ArrayList<>();
        contentList.add(content);
        contentList.add(content2);

        Mockito.when(contentRepository.findAll()).thenReturn(contentList);

        List<ContentDTO> contentDTOList = contentList.stream().map(contentDTOMapper).collect(Collectors.toList());

        List<ContentDTO> actual = contentService.getAllContents();

        //then
        Assertions.assertEquals(actual,contentDTOList);
        Assertions.assertEquals(actual.size(), 2);
    }
//        ContentNotFoundException exception = Assertions.assertThrows(ContentNotFoundException.class, () -> contentService.postContent(contentDTO));
//        Assertions.assertEquals("SERVICE.CONTENT_ALREADY_EXISTS", exception.getMessage());

    @Test
    void getContentValidTest(){
        //given
        Content content = new Content();
        content.setId(1);
        content.setTitle("test content title name");
        content.setDesc("test content description for a new content");
        content.setStatus(Status.PUBLISHED);
        content.setContentType(Type.ARTICLE);
        content.setDateCreated(LocalDateTime.now());
        content.setDateUpdated(LocalDateTime.of(2099, Month.DECEMBER, 15,12,12));
        content.setUrl("http://test.com");
        //when
        Mockito.when(contentRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.of(content));


        ContentDTO expected = new ContentDTO(
                content.getTitle(),
                content.getDesc(),
                content.getStatus(),
                content.getContentType(),
                content.getDateCreated(),
                content.getDateUpdated(),
                content.getUrl());
        ContentDTO actual = contentService.getContent(1);
        //then
        Assertions.assertEquals(actual, expected);
    }
}
