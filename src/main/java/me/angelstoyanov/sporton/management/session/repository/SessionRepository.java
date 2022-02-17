package me.angelstoyanov.sporton.management.session.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import me.angelstoyanov.sporton.management.session.exception.SessionNotExistsException;
import me.angelstoyanov.sporton.management.session.model.Session;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

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
            throw new SessionNotExistsException("Pitch with id " + id + " does not exist");
        }
        deleteById(id);
    }

    //TODO: Add Update session operation


}
