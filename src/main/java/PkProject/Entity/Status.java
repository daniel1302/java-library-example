package PkProject.Entity;

import java.util.Date;

public class Status implements Entity {
    public static final Integer TYPE_LOAN = 1;
    public static final Integer TYPE_FREE = 0;
    public static final Integer TYPE_RESERVED = 2;
    
    private Integer id = 0;
    private String username = "";
    private Integer type = Status.TYPE_FREE;
    private Date created;
    private Integer userId = 0;
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String user) {
        this.username = user;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    
}
