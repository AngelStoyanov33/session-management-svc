package me.angelstoyanov.sporton.management.session.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import me.angelstoyanov.sporton.management.session.model.PitchType;
import me.angelstoyanov.sporton.management.session.model.Polygon;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Map;

@JsonRootName("pitch")
@JsonPropertyOrder({"id", "name", "type", "location", "roles_required"})
public class PitchDTO {
    @JsonProperty("id")
    public ObjectId id;

    @JsonProperty(value = "name", required = true)
    private String name;

    @JsonProperty(value = "type", required = true)
    private PitchType type;

    @JsonProperty(value = "location", required = true)
    @BsonProperty("location")
    private Polygon location;

    @JsonProperty(value = "tags")
    @BsonProperty("tags")
    private Map<String, String> tags;

    @JsonProperty(value = "roles_required")
    @BsonProperty("roles_required")
    private List<String> rolesRequired = null;

    public PitchDTO(ObjectId id, String name, PitchType type, Polygon location, Map<String, String> tags, List<String> rolesRequired) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.location = location;
        this.tags = tags;
        this.rolesRequired = rolesRequired;
    }

    public PitchDTO(String name, PitchType type, Polygon location, Map<String, String> tags, List<String> rolesRequired) {
        this.name = name;
        this.type = type;
        this.location = location;
        this.tags = tags;
        this.rolesRequired = rolesRequired;
    }

    public PitchDTO() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PitchType getType() {
        return type;
    }

    public void setType(PitchType type) {
        this.type = type;
    }

    public Polygon getLocation() {
        return location;
    }

    public void setLocation(Polygon location) {
        this.location = location;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    public List<String> getRolesRequired() {
        return rolesRequired;
    }

    public void setRolesRequired(List<String> rolesRequired) {
        this.rolesRequired = rolesRequired;
    }
}
