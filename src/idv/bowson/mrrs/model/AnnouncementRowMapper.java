package idv.bowson.mrrs.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

public class AnnouncementRowMapper implements RowMapper<Announcement> {

    private static Log log = LogFactory.getLog(AnnouncementRowMapper.class);

    @Autowired
    private DataSource dataSource;

    @Autowired
    public AnnouncementRowMapper() {

    }

    public AnnouncementRowMapper(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Announcement mapRow(ResultSet rs, int rowNum) throws SQLException {

        Announcement announcement = new Announcement();
        announcement.setId(rs.getInt("id"));
        announcement.setTitle(rs.getString("title"));
        announcement.setContent(rs.getString("content"));
        announcement.setPostTime(rs.getTimestamp("postTime"));
        announcement.setModifiedTime(rs.getTimestamp("modifiedTime"));
        announcement.setStickyTime(rs.getTimestamp("stickyTime"));

        UserService userService = new UserService(this.dataSource);

        User postUser = userService.get(rs.getString("postUser"));
        announcement.setPostUser(postUser);
        // announcement.setPostUser(rs.getString("postUser"));

        return announcement;
    }
}
