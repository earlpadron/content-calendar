package dev.earlpadron.contentcalendar.util;

import dev.earlpadron.contentcalendar.model.Content;
import dev.earlpadron.contentcalendar.model.DTO.ContentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContentMapper {


    ContentDTO entityToDTO(Content source);
    Content DTOToEntity(ContentDTO source);

    List<ContentDTO>  entityToDTO(List<Content> sources);
    List<Content> DTOToEntity(List<ContentDTO> sources);
}
