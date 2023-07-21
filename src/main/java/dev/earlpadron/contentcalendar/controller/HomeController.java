//package dev.earlpadron.contentcalendar.controller;
//
//import dev.earlpadron.contentcalendar.config.ContentCalendarProperties;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class HomeController {
//
//    /**
//     * it is possible to manually input metadata for the application
//     * "Externalized Configuartion" do more research to better understand on spring documentation
//     *
//     * to allow externalized configuration, must include in pom.xml
//     * <dependency>
//     * 		<groupId>org.springframework.boot</groupId>
//     * 		<artifactId>spring-boot-configuration-processor</artifactId>
//     * 		<optional>true</optional>
//     * 	</dependency>
//     *
//     * 	Also must indicate @EnableConfigurationProperties(ContentCalendarProperties.class) on the spring application class
//     *
//     *
//     * 	a new configuration .json file will be created after building the project
//     * 	target
//     * 	    -> classes
//     * 	       -> META-INF
//     * 	            ->spring-configuration-metadata.json
//     *
//     */
//
//    /**
//     * Injecting values into beans using @Value() annotation, these values are saved in application.properties
//     */
////    @Value("${cc.welcomeMessage: Default Welcome Message}")
////    private String welcomeMessage;
////
////    @Value("${cc.about}")
////    private String about;
//
//    private final ContentCalendarProperties properties;
//
//    public HomeController(ContentCalendarProperties properties) {
//        this.properties = properties;
//    }
//
//    @GetMapping("/")
//    public ContentCalendarProperties home() {
//        return properties;
//    }
//}