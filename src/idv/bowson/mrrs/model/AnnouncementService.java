package idv.bowson.mrrs.model;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class AnnouncementService {

    private static String ANNOUNCEMENTS_TABLE = "Announcements";

    @Autowired
    private DataSource dataSource;

    /**
     * Inject SQL之DataSource
     */
    @Autowired
    public AnnouncementService(DataSource dataSource) {

        this.dataSource = dataSource;
    }

    public List<Announcement> getLastestPostList(int rowCount) {

        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource);
        String sql = "SELECT * FROM " + ANNOUNCEMENTS_TABLE + " ORDER BY postTime DESC LIMIT :rowCount";
        SqlParameterSource namedParameters = new MapSqlParameterSource("rowCount", rowCount);
        List<Announcement> announcements = jdbcTemplate.query(sql, namedParameters,
                new AnnouncementRowMapper(this.dataSource));

        return announcements;
    }
}
