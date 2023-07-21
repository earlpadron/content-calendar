package dev.earlpadron.contentcalendar.repository;

import dev.earlpadron.contentcalendar.model.Content;
import dev.earlpadron.contentcalendar.model.enums.Status;
//import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends ListCrudRepository<Content, Integer > {

    //QUERY DERIVATION
    List<Content> findAllByTitleContains(String keyword);

    Content findByTitleContains(String keyword);

    @Query("""
            SELECT c FROM Content c WHERE c.status = :status
            """)
    List<Content> listByStatus(@Param("status") Status status);
}
