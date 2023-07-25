//package dev.earlpadron.contentcalendar.repository;
//
//import dev.earlpadron.contentcalendar.model.Content;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//@Repository
//public class ContentJdbcTemplateRepository {
//
//    /**
//     * Jdbc Template is the first level of abstraction above the DriverManger making a manual connection to a database
//     * and writing sql query
//     *
//     * next level of abstraction is Spring Data (most popular is Spring Data JPA(Hibernate))
//     */



/**
 *     private final JdbcTemplate jdbcTemplate;
 *
 *     private static Content mapRow(ResultSet rs, int rowNum) throws SQLException {
 *         return new Content(rs.getInt("id"),
 *                 rs.getString("title"),
 *                 rs.getString("desc"),
 *                 rs.getString("status"),
 *                 rs.getString("content_type"),
 *                 rs.getTimestamp("date_created"),
 *                 rs.getTimestamp("date_updated"),
 *                 rs.getString("url"));
 *
 *     }
 *     public ContentJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
 *         this.jdbcTemplate = jdbcTemplate;
 *     }
 *
 *     public List<Content> getAllContent(){
 *         String sql = "SELECT * FROM CONTENT";
 *         List<Content> contents = jdbcTemplate.query(sql, ContentJdbcTemplateRepository::mapRow);
 *     }
 *
 *     public void createContent(String title, String desc, String status, String contentType)
 * }
 */
