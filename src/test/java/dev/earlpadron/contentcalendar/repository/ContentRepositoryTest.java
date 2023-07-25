package dev.earlpadron.contentcalendar.repository;

import dev.earlpadron.contentcalendar.model.Content;
import dev.earlpadron.contentcalendar.model.enums.Status;
import dev.earlpadron.contentcalendar.model.enums.Type;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;


/**
 * @DataJpaTest is used to test JPA application
 * > it scans @Entity classes and configures Spring Data JPA repositories
 * > if an embedded database is available in the classpath, it is configured
 * > logs SQL queries by default using spring.jpa.show-sql=true
 * JPA tests are transactional and roll back at the end of each test by default(but this can be disabled)
 *
 */
@DataJpaTest
@DisplayName("Unit Tests for Content Repository")
public class  ContentRepositoryTest {

    @Autowired
    private ContentRepository contentRepository;

    private Content content;
    private Content content2;
    @BeforeEach
    void init(){
        content = new Content();
        content.setTitle("test content title name");
        content.setDesc("test content description for a new content");
        content.setStatus(Status.PUBLISHED);
        content.setContentType(Type.ARTICLE);
        content.setDateCreated(LocalDateTime.now());
        content.setUrl("http://test.com");

        content2 = new Content();
        content2.setTitle("test content title name2");
        content2.setDesc("test content description for a new content2");
        content2.setStatus(Status.PUBLISHED);
        content2.setContentType(Type.ARTICLE);
        content2.setDateCreated(LocalDateTime.now());
        content2.setUrl("http://test2.com");
    }

    @Test
    @DisplayName("Content should be saved into the database")
    void save(){
        //Given - set up data for test case

        //when - call the method to be tested
        Content newContent = contentRepository.save(content);

        //then - verify that the expected result is correct

        Assertions.assertThat(newContent.getId()).isNotEqualTo(null);
    }

    @Test
    @DisplayName("Should show that 2 contents entities are successfully saved into the database")
    void getAllContents(){
        //given
        contentRepository.save(content);
        contentRepository.save(content2);

        //when

        List<Content> contentList = contentRepository.findAll();

        //then
          Assertions.assertThat(contentList).isNotNull().hasSize(2);
//        Assertions.assertThat(contentList).isNotNull();
//        Assertions.assertThat(contentList).allSatisfy( c -> {
//            Assertions.assertThat(c.getStatus()).isEqualTo(Status.PUBLISHED);
//            Assertions.assertThat(c.getId()).isNotNull();
//        });
    }

//    @Test
//    @DisplayName("Content found with status set as PUBLISHED")
//    void getContentById(){
//        //given
//        contentRepository.save(content);
//        //when
//        Content existingContent = contentRepository.findById(1).get();
//        //then
//        Assertions.assertThat(existingContent.getStatus()).as("content should equal published").isEqualTo(Status.PUBLISHED);
//    }

    @Test
    @DisplayName("Updated content status from PUBLISHED to IDEA")
    void updateContent(){
        //given
        contentRepository.save(content);
        //when
        Content contentToUpdate = contentRepository.findById(content.getId()).get();
        contentToUpdate.setStatus(Status.IDEA);
        Content updatedContent = contentRepository.save(contentToUpdate);
        //then
        Assertions.assertThat(content.getStatus()).isEqualTo(Status.IDEA);
    }

    @Test
    @DisplayName("Existing content should be deleted")
    void deleteContent(){
        //given
        contentRepository.save(content);
        contentRepository.save(content2);

        //when
        contentRepository.delete(content2);
        List<Content> contentList = contentRepository.findAll();
        //then
        Assertions.assertThat(contentList).hasSize(1)
                                          .contains(content);
        Assertions.assertThat(contentRepository.findById(2)).isEmpty();
    }

    @Test
    @DisplayName("Content with specific title can be found")
    void findByTitleValidTest(){
        //given
        contentRepository.save(content);

        //when
        Content contentWithTitle = contentRepository.findByTitleContains("test content title name");

        //then
        Assertions.assertThat(contentWithTitle).isNotNull();
    }

    @Test
    void listByStatus(){
        //given
        contentRepository.save(content);
        contentRepository.save(content2);

        //when
        List<Content> contentListByStatus = contentRepository.listByStatus(Status.PUBLISHED);
        //then
        Assertions.assertThat(contentListByStatus).isNotNull().hasSize(2);
    }

}
