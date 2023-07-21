package dev.earlpadron.contentcalendar.model.DTO;

import dev.earlpadron.contentcalendar.model.enums.Status;
import dev.earlpadron.contentcalendar.model.enums.Type;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

/**
 * Records should not be used as JPA Entities w/ Hibernate
 *     >records are immutable used to store data. Contains fields, all-args constructors, toString(), hashCode(), equals()
 *     >they DON'T have setters
 *
 * Entities are handled by a JPA provider for creating database tables, mapping entities to the tables, and persisting the entities to the database
 *      >JPA provides(ex. Hibernate) create and manage entities using PROXIES
 *      PROXIES -- classes generated at runtime and extend the entity class
 *              >proxies rely on the entity class to have a **no-args constructor and setters**
 *              !!!since records do not have a no-args constructor or setters THEY CANNOT BE USED AS ENTITIES!!!!!
 *
 * Entities are classes that are mapped to a database table used to represent an entry in a database
 *      >fields are mapped to columns in a database
 *
 * However, they are often used as DTO's
 *
 * > Spring Data JPA allows automatic mapping from entity to record making
 * it unnecessary to convert from entity to DTO (only possible if entity and record share the same fields)
 *
 * https://www.baeldung.com/spring-jpa-java-records <-------------learn more about records here
 *
 *
 * @param title
 * @param desc
 * @param status
 * @param contentType
 * @param dateCreated
 * @param dateUpdated
 * @param url
 */
public record ContentDTO(
        @NotBlank(message = "{content.title.invalid}") // must create custom configuration message for each invalid property
        @Length(min = 5, message = "{content.title.invalid}")// for more info - https://www.baeldung.com/spring-custom-validation-message-source
        String title,
        String desc,
        //@Pattern(regexp = "(IDEA|IN_PROGRESS|COMPLETED|PUBLISHED)",message = "{content.status.invalid}")
        Status status,
        Type contentType,
        //@PastOrPresent(message = "{content.dateCreated.invalid}")
        LocalDateTime dateCreated,
        //@FutureOrPresent(message = "{content.dateUpdated.invalid}")
        LocalDateTime dateUpdated,
        String url
) {
    /**
     *  creates an immutable class with a bunch of fields(or called components for records) that cannot be changed
     *  > fields access modifiers are stored as private and final
     *  > additional instance fields that are not private and final cannot be added
     *  holds info and returns to the user
     *  implements toString(), hashCode(), equals()
     *  inserts a "canonical" constructor instead of default no-args constructor
     *  > can override the default constructor and validation with a "compact" constructor
     *  fields are retrieved with recordName.fieldname() instead of recordClassName.getFieldName()
     *  records cannot extend another class
     *  > can implement an interface
     */



}
