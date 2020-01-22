package dsc.mwahbak.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MediaModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("nLikes")
    @Expose
    private Integer nLikes;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("liked")
    @Expose
    private Boolean liked;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getNLikes() {
        return nLikes;
    }

    public void setNLikes(Integer nLikes) {
        this.nLikes = nLikes;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    public Integer getnLikes() {
        return nLikes;
    }

    public void setnLikes(Integer nLikes) {
        this.nLikes = nLikes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
