package dev.earlpadron.contentcalendar.model.DTO;

import dev.earlpadron.contentcalendar.model.enums.Status;
import dev.earlpadron.contentcalendar.model.enums.Type;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record ContentUpdateRequestDTO(
        @NotBlank(message = "{content.title.invalid}")
        @Length(min = 5, message = "{content.title.invalid}")
        String title,

        String desc,

        Status status,
        Type contentType,
        @PastOrPresent(message = "{content.dateCreated.invalid}")
        LocalDateTime dateCreated,
        @FutureOrPresent(message = "{content.dateUpdated.invalid}")
        LocalDateTime dateUpdated,
        String url) {
}
