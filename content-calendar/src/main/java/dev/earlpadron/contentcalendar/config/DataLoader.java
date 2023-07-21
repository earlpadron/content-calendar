//package dev.earlpadron.contentcalendar.config;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import dev.earlpadron.contentcalendar.model.Content;
//import dev.earlpadron.contentcalendar.repository.ContentRepository;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Component;
//
//import java.io.InputStream;
//import java.util.List;
//
///**
// * CommandLineRunner starts after dependency injection occurs upon application start-up,
// * best place to do some bootstrapping or data loading
// */
////@Profile("!dev") //profiles lets us control which particular components will be loaded
//                 //since "!dev" = not dev is used, this bean will not placed in the container;therefor, hello will not be printed in the console
//@Component
//public class DataLoader implements CommandLineRunner {
//    private final ContentRepository repository;
//    private final ObjectMapper objectMapper;
//
//    public DataLoader(ContentRepository repository, ObjectMapper objectMapper) {
//        this.repository = repository;
//        this.objectMapper = objectMapper;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        if(repository.count() == 0) {
//            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/content.json")) {
//                repository.saveAll(objectMapper.readValue(inputStream,new TypeReference<List<Content>>(){}));
//            }
//        }
//    }
//
//        /**
//         * "C:\Program Files\Java\jdk-17\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2023.1.2\lib\idea_rt.jar=54189:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2023.1.2\bin" -Dfile.encoding=UTF-8 -classpath C:\Users\Earl\Desktop\SpringBoot_Projects\content-calendar\content-calendar\target\classes;C:\Users\Earl\.m2\repository\org\springframework\boot\spring-boot-starter-web\3.1.0\spring-boot-starter-web-3.1.0.jar;C:\Users\Earl\.m2\repository\org\springframework\boot\spring-boot-starter\3.1.0\spring-boot-starter-3.1.0.jar;C:\Users\Earl\.m2\repository\org\springframework\boot\spring-boot\3.1.0\spring-boot-3.1.0.jar;C:\Users\Earl\.m2\repository\org\springframework\boot\spring-boot-autoconfigure\3.1.0\spring-boot-autoconfigure-3.1.0.jar;C:\Users\Earl\.m2\repository\org\springframework\boot\spring-boot-starter-logging\3.1.0\spring-boot-starter-logging-3.1.0.jar;C:\Users\Earl\.m2\repository\ch\qos\logback\logback-classic\1.4.7\logback-classic-1.4.7.jar;C:\Users\Earl\.m2\repository\ch\qos\logback\logback-core\1.4.7\logback-core-1.4.7.jar;C:\Users\Earl\.m2\repository\org\apache\logging\log4j\log4j-to-slf4j\2.20.0\log4j-to-slf4j-2.20.0.jar;C:\Users\Earl\.m2\repository\org\apache\logging\log4j\log4j-api\2.20.0\log4j-api-2.20.0.jar;C:\Users\Earl\.m2\repository\org\slf4j\jul-to-slf4j\2.0.7\jul-to-slf4j-2.0.7.jar;C:\Users\Earl\.m2\repository\jakarta\annotation\jakarta.annotation-api\2.1.1\jakarta.annotation-api-2.1.1.jar;C:\Users\Earl\.m2\repository\org\yaml\snakeyaml\1.33\snakeyaml-1.33.jar;C:\Users\Earl\.m2\repository\org\springframework\boot\spring-boot-starter-json\3.1.0\spring-boot-starter-json-3.1.0.jar;C:\Users\Earl\.m2\repository\com\fasterxml\jackson\core\jackson-databind\2.15.0\jackson-databind-2.15.0.jar;C:\Users\Earl\.m2\repository\com\fasterxml\jackson\core\jackson-annotations\2.15.0\jackson-annotations-2.15.0.jar;C:\Users\Earl\.m2\repository\com\fasterxml\jackson\core\jackson-core\2.15.0\jackson-core-2.15.0.jar;C:\Users\Earl\.m2\repository\com\fasterxml\jackson\datatype\jackson-datatype-jdk8\2.15.0\jackson-datatype-jdk8-2.15.0.jar;C:\Users\Earl\.m2\repository\com\fasterxml\jackson\datatype\jackson-datatype-jsr310\2.15.0\jackson-datatype-jsr310-2.15.0.jar;C:\Users\Earl\.m2\repository\com\fasterxml\jackson\module\jackson-module-parameter-names\2.15.0\jackson-module-parameter-names-2.15.0.jar;C:\Users\Earl\.m2\repository\org\springframework\boot\spring-boot-starter-tomcat\3.1.0\spring-boot-starter-tomcat-3.1.0.jar;C:\Users\Earl\.m2\repository\org\apache\tomcat\embed\tomcat-embed-core\10.1.8\tomcat-embed-core-10.1.8.jar;C:\Users\Earl\.m2\repository\org\apache\tomcat\embed\tomcat-embed-websocket\10.1.8\tomcat-embed-websocket-10.1.8.jar;C:\Users\Earl\.m2\repository\org\springframework\spring-web\6.0.9\spring-web-6.0.9.jar;C:\Users\Earl\.m2\repository\org\springframework\spring-beans\6.0.9\spring-beans-6.0.9.jar;C:\Users\Earl\.m2\repository\io\micrometer\micrometer-observation\1.11.0\micrometer-observation-1.11.0.jar;C:\Users\Earl\.m2\repository\io\micrometer\micrometer-commons\1.11.0\micrometer-commons-1.11.0.jar;C:\Users\Earl\.m2\repository\org\springframework\spring-webmvc\6.0.9\spring-webmvc-6.0.9.jar;C:\Users\Earl\.m2\repository\org\springframework\spring-aop\6.0.9\spring-aop-6.0.9.jar;C:\Users\Earl\.m2\repository\org\springframework\spring-context\6.0.9\spring-context-6.0.9.jar;C:\Users\Earl\.m2\repository\org\springframework\spring-expression\6.0.9\spring-expression-6.0.9.jar;C:\Users\Earl\.m2\repository\org\springframework\boot\spring-boot-starter-validation\3.1.0\spring-boot-starter-validation-3.1.0.jar;C:\Users\Earl\.m2\repository\org\apache\tomcat\embed\tomcat-embed-el\10.1.8\tomcat-embed-el-10.1.8.jar;C:\Users\Earl\.m2\repository\org\hibernate\validator\hibernate-validator\8.0.0.Final\hibernate-validator-8.0.0.Final.jar;C:\Users\Earl\.m2\repository\jakarta\validation\jakarta.validation-api\3.0.2\jakarta.validation-api-3.0.2.jar;C:\Users\Earl\.m2\repository\org\jboss\logging\jboss-logging\3.5.0.Final\jboss-logging-3.5.0.Final.jar;C:\Users\Earl\.m2\repository\com\fasterxml\classmate\1.5.1\classmate-1.5.1.jar;C:\Users\Earl\.m2\repository\org\slf4j\slf4j-api\2.0.7\slf4j-api-2.0.7.jar;C:\Users\Earl\.m2\repository\org\springframework\spring-core\6.0.9\spring-core-6.0.9.jar;C:\Users\Earl\.m2\repository\org\springframework\spring-jcl\6.0.9\spring-jcl-6.0.9.jar;C:\Users\Earl\.m2\repository\org\springframework\boot\spring-boot-starter-data-jdbc\3.1.0\spring-boot-starter-data-jdbc-3.1.0.jar;C:\Users\Earl\.m2\repository\org\springframework\boot\spring-boot-starter-jdbc\3.1.0\spring-boot-starter-jdbc-3.1.0.jar;C:\Users\Earl\.m2\repository\com\zaxxer\HikariCP\5.0.1\HikariCP-5.0.1.jar;C:\Users\Earl\.m2\repository\org\springframework\spring-jdbc\6.0.9\spring-jdbc-6.0.9.jar;C:\Users\Earl\.m2\repository\org\springframework\data\spring-data-jdbc\3.1.0\spring-data-jdbc-3.1.0.jar;C:\Users\Earl\.m2\repository\org\springframework\data\spring-data-relational\3.1.0\spring-data-relational-3.1.0.jar;C:\Users\Earl\.m2\repository\org\springframework\data\spring-data-commons\3.1.0\spring-data-commons-3.1.0.jar;C:\Users\Earl\.m2\repository\org\springframework\spring-tx\6.0.9\spring-tx-6.0.9.jar;C:\Users\Earl\.m2\repository\com\h2database\h2\2.1.214\h2-2.1.214.jar dev.earlpadron.contentcalendar.Application
//         *
//         *   .   ____          _            __ _ _
//         *  /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
//         * ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
//         *  \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
//         *   '  |____| .__|_| |_|_| |_\__, | / / / /
//         *  =========|_|==============|___/=/_/_/_/
//         *  :: Spring Boot ::                (v3.1.0)
//         *
//         * 2023-06-16T12:33:31.193-07:00  INFO 14940 --- [           main] d.e.contentcalendar.Application          : Starting Application using Java 17.0.7 with PID 14940 (C:\Users\Earl\Desktop\SpringBoot_Projects\content-calendar\content-calendar\target\classes started by Earl in C:\Users\Earl\Desktop\SpringBoot_Projects\content-calendar)
//         * 2023-06-16T12:33:31.197-07:00  INFO 14940 --- [           main] d.e.contentcalendar.Application          : No active profile set, falling back to 1 default profile: "default"
//         * 2023-06-16T12:33:31.881-07:00  INFO 14940 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JDBC repositories in DEFAULT mode.
//         * 2023-06-16T12:33:31.944-07:00  INFO 14940 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 58 ms. Found 1 JDBC repository interfaces.
//         * 2023-06-16T12:33:32.622-07:00  INFO 14940 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
//         * 2023-06-16T12:33:32.633-07:00  INFO 14940 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
//         * 2023-06-16T12:33:32.633-07:00  INFO 14940 --- [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.8]
//         * 2023-06-16T12:33:32.735-07:00  INFO 14940 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
//         * 2023-06-16T12:33:32.737-07:00  INFO 14940 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 1493 ms
//         * 2023-06-16T12:33:32.773-07:00  INFO 14940 --- [           main] com.zaxxer.hikari.HikariDataSource       : content - Starting...
//         * 2023-06-16T12:33:32.989-07:00  INFO 14940 --- [           main] com.zaxxer.hikari.pool.HikariPool        : content - Added connection conn0: url=jdbc:h2:mem:content user=SA
//         * 2023-06-16T12:33:32.991-07:00  INFO 14940 --- [           main] com.zaxxer.hikari.HikariDataSource       : content - Start completed.
//         * 2023-06-16T12:33:33.006-07:00  INFO 14940 --- [           main] o.s.b.a.h2.H2ConsoleAutoConfiguration    : H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:content'
//         * 2023-06-16T12:33:33.091-07:00 DEBUG 14940 --- [           main] o.s.jdbc.datasource.DataSourceUtils      : Fetching JDBC Connection from DataSource
//         * 2023-06-16T12:33:33.099-07:00 DEBUG 14940 --- [           main] o.s.jdbc.datasource.DataSourceUtils      : Fetching JDBC Connection from DataSource
//         * 2023-06-16T12:33:33.100-07:00 DEBUG 14940 --- [           main] o.s.jdbc.datasource.init.ScriptUtils     : Executing SQL script from file [C:\Users\Earl\Desktop\SpringBoot_Projects\content-calendar\content-calendar\target\classes\schema.sql]
//         * 2023-06-16T12:33:33.117-07:00 DEBUG 14940 --- [           main] o.s.jdbc.datasource.init.ScriptUtils     : 0 returned as update count for SQL: CREATE TABLE IF NOT EXISTS Content ( id INTEGER AUTO_INCREMENT, title varchar(255) NOT NULL, desc text, status VARCHAR(20) NOT NULL, content_type VARCHAR(50) NOT NULL, date_created TIMESTAMP NOT NULL, date_updated TIMESTAMP, url VARCHAR(255), primary key(id) )
//         * 2023-06-16T12:33:33.120-07:00 DEBUG 14940 --- [           main] o.s.jdbc.datasource.init.ScriptUtils     : 1 returned as update count for SQL: INSERT INTO CONTENT(title,desc,status,content_type,date_created) VALUES ('My Spring Data Blog Post', 'A post about spring data', 'IDEA', 'ARTICLE', CURRENT_TIMESTAMP())
//         * 2023-06-16T12:33:33.122-07:00 DEBUG 14940 --- [           main] o.s.jdbc.datasource.init.ScriptUtils     : Executed SQL script from file [C:\Users\Earl\Desktop\SpringBoot_Projects\content-calendar\content-calendar\target\classes\schema.sql] in 21 ms.
//         * 2023-06-16T12:33:33.147-07:00 DEBUG 14940 --- [           main] o.s.jdbc.datasource.DataSourceUtils      : Fetching JDBC Connection from DataSource
//         * 2023-06-16T12:33:33.772-07:00  INFO 14940 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
//         * 2023-06-16T12:33:33.781-07:00  INFO 14940 --- [           main] d.e.contentcalendar.Application          : Started Application in 2.995 seconds (process running for 3.345)
//         * Hello <THIS LINE WAS ADDED>
//         */
//
//}
