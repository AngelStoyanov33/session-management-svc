package me.angelstoyanov.sporton.management.session.model.extended;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import me.angelstoyanov.sporton.management.session.model.dto.UserDTO;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@JsonRootName("session_user")
@JsonPropertyOrder({"user", "joined_at"})
public class ExtendedSessionUser {
    @JsonProperty("user")
    @BsonProperty("user")
    private UserDTO user;

    @JsonProperty("joined_at")
    @BsonProperty("joined_at")
    private String joinedAt = ZonedDateTime.now(ZoneId.of("Europe/Sofia")).toInstant().toString();

    @JsonProperty("left_at")
    @BsonProperty("left_at")
    private String leftAt = null;

    public ExtendedSessionUser(UserDTO user) {
        this.user = user;
    }
    public ExtendedSessionUser(UserDTO user, String joinedAt, String leftAt) {
        this.user = user;
        this.joinedAt = joinedAt;
        this.leftAt = leftAt;
    }

    public ExtendedSessionUser() {
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
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
