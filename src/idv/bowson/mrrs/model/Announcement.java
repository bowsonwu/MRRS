package idv.bowson.mrrs.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Announcement {

    private static Log log = LogFactory.getLog(Announcement.class);
    private static DateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd  HH:mm");

    private int id;

    /**
     * 公告標題
     */
    private String title;

    /**
     * 公告內容
     */
    private String content;

    /**
     * 公告建立時間
     */
    private Date postTime;

    /**
     * 公告最後修改時間
     */
    private Date modifiedTime;

    /**
     * 公告置頂結束時間
     */
    private Date stickyTime;

    /**
     * 公告發布人員
     */
    private User postUser;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getPostTime() {
        return postTime;
    }

    public String getPostTimeString() {

        String timeString = DATEFORMAT.format(postTime);
        return timeString;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public String getModifiedTimeString() {

        String timeString = DATEFORMAT.format(modifiedTime);
        return timeString;
    }

    public Date getStickyTime() {
        return stickyTime;
    }

    public String getStickyTimeString() {

        String timeString = DATEFORMAT.format(stickyTime);
        return timeString;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public void setStickyTime(Date stickyTime) {
        this.stickyTime = stickyTime;
    }

    public User getPostUser() {
        return postUser;
    }

    public void setPostUser(User postUser) {
        this.postUser = postUser;
    }
}
