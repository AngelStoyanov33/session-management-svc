package me.angelstoyanov.sporton.management.session.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import me.angelstoyanov.sporton.management.session.exception.SessionNotExistsException;
import me.angelstoyanov.sporton.management.session.model.Session;
import me.angelstoyanov.sporton.management.session.model.SessionStatus;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.Locale;

@Named("SessionRepository")
@ApplicationScoped
public class SessionRepository implements PanacheMongoRepository<Session> {
    public Session addSession(Session session) {
        //TODO: Add session check before adding
        persist(session);
        return session;
    }


    public void deleteSession(Session session) throws SessionNotExistsException {
        if (findById(session.getId()) == null) {
            throw new SessionNotExistsException("Session with id " + session.getId() + " does not exist");
        }
        delete(session);
    }

    public void deleteSessionById(ObjectId id) throws SessionNotExistsException {
        if (findById(id) == null) {
            throw new SessionNotExistsException("Session with id " + id + " does not exist");
        }
        deleteById(id);
    }

    public Session getSessionByPitchIdAndStatus(String pitchId, SessionStatus type) {
        return find(String.format(Locale.US, "{ \"pitch_id\": ObjectId(\"%s\"), \"status\": \"%s\" }", pitchId, type)).firstResult();
    }

    //TODO: Add Update session operation


}
