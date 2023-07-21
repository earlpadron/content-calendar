package dev.earlpadron.contentcalendar;

//import dev.earlpadron.contentcalendar.config.ContentCalendarProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

//@EnableConfigurationProperties(ContentCalendarProperties.class)
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	//creating a bean method of the commandLineRunner allows data to be loaded after dependency injection
//	@Bean
//	CommandLineRunner commandLineRunner(ContentRepository repository) {
//		return args -> {
//			//insert some data into the database
//			Content content
//					= new Content(null,
//					"Hello Chat GPT",
//					"All about Chat GPT",
//					Status.IDEA,
//					Type.VIDEO,
//					LocalDateTime.now(),
//					null,
//					"");
//			repository.save(content);
//		};

	}

