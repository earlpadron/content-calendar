//package dev.earlpadron.contentcalendar.util;
//
//import dev.earlpadron.contentcalendar.model.Content;
//import dev.earlpadron.contentcalendar.model.DTO.ContentDTO;
//import org.springframework.stereotype.Service;
//
//import java.util.function.Function;
//
///**
// * This class prepares a ContentDTO from a given Content Entity
// *
// * inject this class into unit tests
// * for more info on mapping entities to DTOs : https://www.youtube.com/watch?v=5yquJa2x3Ko&ab_channel=Amigoscode
// *
// * Learn about ModelMapper
// */
//@Service
//public class ContentDTOMapper implements Function<Content, ContentDTO> {
//
//    @Override
//    public ContentDTO apply(Content content) {
//        return new ContentDTO(
//                content.getTitle(),
//                content.getDesc(),
//                content.getStatus(),
//                content.getContentType(),
//                content.getDateCreated(),
//                content.getDateUpdated(),
//                content.getUrl()
//        );
//    }
//
//}
