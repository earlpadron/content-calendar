//package dev.earlpadron.contentcalendar.controller;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import dev.earlpadron.contentcalendar.model.Content;
//import dev.earlpadron.contentcalendar.model.DTO.ContentDTO;
//import dev.earlpadron.contentcalendar.model.enums.Status;
//import dev.earlpadron.contentcalendar.model.enums.Type;
//import dev.earlpadron.contentcalendar.service.ContentService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.LocalDateTime;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//
//
///**
// * Tests Spring MVC Components
// */
//@WebMvcTest
//public class ContentControllerTest {
//
//    @MockBean //if a testcase is dependent on Spring container Bean then use this method
//    private ContentService contentService;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    private Content content;
//    private Content content2;
//
//    @BeforeEach
//    void init(){
//        content = new Content();
//        content.setTitle("test content title name");
//        content.setDesc("test content description for a new content");
//        content.setStatus(Status.PUBLISHED);
//        content.setContentType(Type.ARTICLE);
//        content.setDateCreated(LocalDateTime.now());
//        content.setUrl("http://test.com");
//
//        content2 = new Content();
//        content2.setTitle("test content title name2");
//        content2.setDesc("test content description for a new content2");
//        content2.setStatus(Status.PUBLISHED);
//        content2.setContentType(Type.ARTICLE);
//        content2.setDateCreated(LocalDateTime.now());
//        content2.setUrl("http://test2.com");
//    }
//
//    @Test
//    void postContentValidTest() throws JsonProcessingException {
//        //given
//
//        ContentDTO contentDTO = new ContentDTO(
//                content.getTitle(),
//                content.getDesc(),
//                content.getStatus(),
//                content.getContentType(),
//                content.getDateCreated(),
//                content.getDateUpdated(),
//                content.getUrl());
//        //when
//        Mockito.when(contentService.postContent(Mockito.any(Content.class))).thenReturn(1);
//
////        this.mockMvc.perform(post("/create").contentType(MediaType.APPLICATION_JSON)
////                        .content(objectMapper.writeValueAsString(contentDTO));
//
//
//        //then
//    }
//
//}
