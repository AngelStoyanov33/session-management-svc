package me.angelstoyanov.sporton.management.session.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@JsonRootName("session_user")
@JsonPropertyOrder({"user_id", "joined_at"})
public class SessionUser {

    @JsonProperty("user_id")
    @BsonProperty("user_id")
    private String userId;

    @JsonProperty("joined_at")
    @BsonProperty("joined_at")
    private String joinedAt = ZonedDateTime.now(ZoneId.of("Europe/Sofia")).toInstant().toString();

    @JsonProperty("left_at")
    @BsonProperty("left_at")
    private String leftAt;

    public SessionUser(String userId) {
        this.userId = userId;
    }

    public SessionUser() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(String joinedAt) {
        this.joinedAt = joinedAt;
    }

    public String getLeftAt() {
        return leftAt;
    }

    public void setLeftAt(String leftAt) {
        this.leftAt = leftAt;
    }
}
