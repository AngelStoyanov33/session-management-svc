package me.angelstoyanov.sporton.management.session.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import io.quarkus.mongodb.panache.common.MongoEntity;
import me.angelstoyanov.sporton.management.session.model.dto.PitchDTO;
import me.angelstoyanov.sporton.management.session.model.extended.ExtendedSessionUser;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@JsonRootName("session")
@JsonPropertyOrder({"id", "created_at", "scheduled_for", "canceled_at", "finished_at",
        "status", "type", "sport_type", "pitch_id", "users"})
@MongoEntity(collection = "Session", database = "sporton-dev-db")
public class Session {
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

    @JsonProperty("pitch_id")
    @BsonProperty("pitch_id")
    private ObjectId pitchId;

    @JsonProperty("users")
    private List<SessionUser> users;

    public Session(ObjectId id, SessionStatus status, PitchType type, ObjectId pitchId, List<SessionUser> users) {
        this.id = id;
        this.status = status;
        this.type = type;
        this.pitchId = pitchId;
        this.users = users;
    }

    public Session(SessionStatus status, PitchType type, ObjectId pitchId, List<SessionUser> users) {
        this.status = status;
        this.type = type;
        this.pitchId = pitchId;
        this.users = users;
    }

    public Session() {
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

    public ObjectId getPitchId() {
        return pitchId;
    }

    public void setPitchId(ObjectId pitchId) {
        this.pitchId = pitchId;
    }

    public List<SessionUser> getUsers() {
        return users;
    }

    public void setUsers(List<SessionUser> users) {
        this.users = users;
    }
}
