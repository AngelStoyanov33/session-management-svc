package me.angelstoyanov.sporton.management.session.model.extended;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import me.angelstoyanov.sporton.management.session.model.PitchType;
import me.angelstoyanov.sporton.management.session.model.SessionStatus;
import me.angelstoyanov.sporton.management.session.model.SessionUser;
import me.angelstoyanov.sporton.management.session.model.dto.PitchDTO;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@JsonRootName("session")
@JsonPropertyOrder({"id", "created_at", "scheduled_for", "canceled_at", "finished_at",
        "status", "type", "sport_type", "pitch_id", "users"})
public class ExtendedSession {
    @JsonProperty("id")
    private ObjectId id;

    @JsonProperty("created_at")
    @BsonProperty("created_at")
    private String createdAt = ZonedDateTime.now(ZoneId.of("Europe/Sofia")).toInstant().toString();

    @JsonProperty("scheduled_for")
    @BsonProperty("scheduled_for")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String scheduledFor = null;

    @JsonProperty("canceled_at")
    @BsonProperty("canceled_at")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String canceledAt = null;

    @JsonProperty("finished_at")
    @BsonProperty("finished_at")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String finishedAt = null;

    @JsonProperty("status")
    @BsonProperty("status")
    private SessionStatus status;

    @JsonProperty("sport_type")
    @BsonProperty("sport_type")
    private PitchType type;

    @JsonProperty("pitch")
    @BsonProperty("pitch")
    private PitchDTO pitch;

    @JsonProperty("users")
    private List<ExtendedSessionUser> users;

    public ExtendedSession(ObjectId id, SessionStatus status, PitchType type, PitchDTO pitch, List<ExtendedSessionUser> users) {
        this.id = id;
        this.status = status;
        this.type = type;
        this.pitch = pitch;
        this.users = users;
    }

    public ExtendedSession(SessionStatus status, PitchType type, PitchDTO pitch, List<ExtendedSessionUser> users) {
        this.status = status;
        this.type = type;
        this.pitch = pitch;
        this.users = users;
    }

    public ExtendedSession() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getScheduledFor() {
        return scheduledFor;
    }

    public void setScheduledFor(String scheduledFor) {
        this.scheduledFor = scheduledFor;
    }

    public String getCanceledAt() {
        return canceledAt;
    }

    public void setCanceledAt(String canceledAt) {
        this.canceledAt = canceledAt;
    }

    public String getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(String finishedAt) {
        this.finishedAt = finishedAt;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public void setStatus(SessionStatus status) {
        this.status = status;
    }

    public PitchType getType() {
        return type;
    }

    public void setType(PitchType type) {
        this.type = type;
    }

    public PitchDTO getPitch() {
        return pitch;
    }

    public void setPitch(PitchDTO pitch) {
        this.pitch = pitch;
    }

    public List<ExtendedSessionUser> getUsers() {
        return users;
    }

    public void setUsers(List<ExtendedSessionUser> users) {
        this.users = users;
    }
}
