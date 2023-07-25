package dev.earlpadron.contentcalendar.service;

import dev.earlpadron.contentcalendar.exception.ContentNotFoundException;
import dev.earlpadron.contentcalendar.model.Content;
import dev.earlpadron.contentcalendar.model.DTO.ContentUpdateRequestDTO;
import dev.earlpadron.contentcalendar.model.enums.Status;
import dev.earlpadron.contentcalendar.model.enums.Type;
import dev.earlpadron.contentcalendar.repository.ContentRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


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
    Environment environment;
    private Content content;
    private Content content2;

    @BeforeEach
    void init(){
        content = new Content();
        content.setId(1);
        content.setTitle("test content title name");
        content.setDesc("test content description for a new content");
        content.setStatus(Status.PUBLISHED);
        content.setContentType(Type.ARTICLE);
        content.setDateCreated(LocalDateTime.now());
        content.setUrl("http://test.com");

        content2 = new Content();
        content2.setId(2);
        content2.setTitle("second piece of content with a title");
        content2.setDesc("test content description for a new content 2");
        content2.setStatus(Status.PUBLISHED);
        content2.setContentType(Type.ARTICLE);
        content2.setDateCreated(LocalDateTime.now());
        content2.setUrl("http://test2.com");
    }

    @Test
    void getContentValidTest(){

        //when
        Mockito.when(contentRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(content));
        //then
        Content actual = contentService.getContent(Mockito.anyInt());
        Assertions.assertThat(actual).isNotNull()
                .isEqualTo(content);
    }

    @Test
    void getContentInvalidTest(){
        //when
        Mockito.when(contentRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        //then
        Assertions.assertThatThrownBy( () -> {
            contentService.getContent(Mockito.anyInt());
        }).isInstanceOf(ContentNotFoundException.class);

    }
    @Test
    void getAllContentsValidTest(){
        //given
        List<Content> contentList = new ArrayList<>();
        contentList.add(content);
        contentList.add(content2);

        //when
        Mockito.when(contentRepository.findAll()).thenReturn(contentList);

        //then
        List<Content> actual = contentService.getAllContents();
        Assertions.assertThat(actual).isEqualTo(contentList).hasSize(2);
    }

    @Test
    void postValidContent(){

        Mockito.when(contentRepository.findByTitleContains(Mockito.anyString())).thenReturn(null);
        Mockito.when(contentRepository.save(content)).thenReturn(content);

        //then
        Integer actual = contentService.postContent(content);
        Assertions.assertThat(actual).isEqualTo(content.getId());
    }

    @Test
    void updateContentValidTest(){
        //given
        ContentUpdateRequestDTO updateRequestDTO = new ContentUpdateRequestDTO(
                "test content title name",
                "updated Content for test",
                Status.COMPLETED,
                Type.CONFERENCE_TALK,
                LocalDateTime.now(),
                LocalDateTime.of(2099, Month.DECEMBER,21,1,1),
                "http://testIsUpdated.com"
        );

        Mockito.when(contentRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(content));

        //update content
        content.setTitle(updateRequestDTO.title());
        content.setDesc(updateRequestDTO.desc());
        content.setStatus(updateRequestDTO.status());
        content.setContentType(updateRequestDTO.contentType());
        content.setDateCreated(updateRequestDTO.dateCreated());
        content.setDateUpdated(updateRequestDTO.dateUpdated());
        content.setUrl(updateRequestDTO.url());

        Mockito.when(contentRepository.save(Mockito.any(Content.class))).thenReturn(content);

        Content actual = contentService.updateContent(content.getId(), updateRequestDTO);
        Assertions.assertThat(content).isNotNull();
    }

    @Test
    void findAllContentsContainingTitleKeywordValidTest(){
        //given

        List<Content> contentList = new ArrayList<>();
        contentList.add(content);

        //when
        Mockito.when(contentRepository.findAllByTitleContains(Mockito.anyString())).thenReturn(contentList);

        //then
        List<Content> actual = contentService.findAllContentsContainingTitleKeyword("test content title name");
        Assertions.assertThat(actual).isNotNull().hasSize(1);
    }

    @Test
    void deleteContentValidTest(){
        //given

        //when
        Mockito.when(contentRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(content));
        Mockito.doNothing().when(contentRepository).delete(Mockito.any(Content.class));

        //then
        contentService.deleteContent(content.getId());
        Mockito.verify(contentRepository, Mockito.times(1)).findById(Mockito.anyInt());
        Mockito.verify(contentRepository, Mockito.times(1)).delete(content);
    }

    @Test
    void findByStatusValidTest(){
        //given
        List<Content> contentList = new ArrayList<>(Arrays.asList(content, content2));
        //when
        Mockito.when(contentRepository.listByStatus(Mockito.any(Status.class))).thenReturn(contentList);
        //then
        List<Content> actual = contentService.findByStatus(Status.PUBLISHED);
        Assertions.assertThat(actual).isNotNull().hasSize(2);
    }

//    @Test
//    void postContentValidTest(){
//        //given - prepare data
//        Content content = new Content();
//        content.setTitle("test content title name");
//        content.setDesc("test content description for a new content");
//        content.setStatus(Status.PUBLISHED);
//        content.setContentType(Type.ARTICLE);
//        content.setDateCreated(LocalDateTime.now());
//        content.setUrl("http://test.com");
//
//
//        //when - call the methods
//        Mockito.when(contentRepository.save(Mockito.any(Content.class))).thenReturn(content);
//        Content newContent = contentRepository.save(content);
//
//        //then - assert
//        Assertions.assertNotNull(newContent);
//        Assertions.assertEquals(newContent.getTitle(), content.getTitle());
//        Mockito.verify(contentRepository, Mockito.times(1)).save(Mockito.any(Content.class));
//    }
//
//    @Test
//    void getAllContentsValidTest(){
//        //given - prepare data
//        Content content = new Content();
//        content.setId(1);
//        content.setTitle("test content title name");
//        content.setDesc("test content description for a new content");
//        content.setStatus(Status.PUBLISHED);
//        content.setContentType(Type.ARTICLE);
//        content.setDateCreated(LocalDateTime.now());
//        content.setDateUpdated(LocalDateTime.of(2099, Month.DECEMBER, 15,12,12));
//        content.setUrl("http://test.com");
//
//        Content content2 = new Content();
//        content2.setId(2);
//        content2.setTitle("test content title name2");
//        content2.setDesc("test content description for a new content2");
//        content2.setStatus(Status.PUBLISHED);
//        content2.setContentType(Type.ARTICLE);
//        content2.setDateCreated(LocalDateTime.now());
//        content2.setDateUpdated(LocalDateTime.of(2099, Month.DECEMBER, 15,12,12));
//        content2.setUrl("http://test2.com");
//
//        ContentDTO contentDTO = new ContentDTO(
//                content.getTitle(),
//                content.getDesc(),
//                content.getStatus(),
//                content.getContentType(),
//                content.getDateCreated(),
//                content.getDateUpdated(),
//                content.getUrl()
//        );
//
//        ContentDTO contentDTO2 = new ContentDTO(
//                content2.getTitle(),
//                content2.getDesc(),
//                content2.getStatus(),
//                content2.getContentType(),
//                content2.getDateCreated(),
//                content2.getDateUpdated(),
//                content2.getUrl()
//        );
//        //when
//        List<Content> contentList = new ArrayList<>();
//        contentList.add(content);
//        contentList.add(content2);
//
//        Mockito.when(contentRepository.findAll()).thenReturn(contentList);
//
//        List<ContentDTO> contentDTOList = contentList.stream().map(contentDTOMapper).collect(Collectors.toList());
//
//        List<ContentDTO> actual = contentService.getAllContents();
//
//        //then
//        Assertions.assertEquals(actual,contentDTOList);
//        Assertions.assertEquals(actual.size(), 2);
//    }
////        ContentNotFoundException exception = Assertions.assertThrows(ContentNotFoundException.class, () -> contentService.postContent(contentDTO));
////        Assertions.assertEquals("SERVICE.CONTENT_ALREADY_EXISTS", exception.getMessage());
//
//    @Test
//    void getContentValidTest(){
//        //given
//        Content content = new Content();
//        content.setId(1);
//        content.setTitle("test content title name");
//        content.setDesc("test content description for a new content");
//        content.setStatus(Status.PUBLISHED);
//        content.setContentType(Type.ARTICLE);
//        content.setDateCreated(LocalDateTime.now());
//        content.setDateUpdated(LocalDateTime.of(2099, Month.DECEMBER, 15,12,12));
//        content.setUrl("http://test.com");
//        //when
//        Mockito.when(contentRepository.findById(Mockito.anyInt()))
//                .thenReturn(Optional.of(content));
//
//
//        ContentDTO expected = new ContentDTO(
//                content.getTitle(),
//                content.getDesc(),
//                content.getStatus(),
//                content.getContentType(),
//                content.getDateCreated(),
//                content.getDateUpdated(),
//                content.getUrl());
//        ContentDTO actual = contentService.getContent(1);
//        //then
//        Assertions.assertEquals(actual, expected);
//    }
}
