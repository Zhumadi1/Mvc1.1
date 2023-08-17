package cinemaMvc.service.Impl;

import cinemaMvc.model.Movie;
import cinemaMvc.model.Session;
import cinemaMvc.service.ServiceLayer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SessionService implements ServiceLayer<Session> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Session findById(int id) {
        return entityManager.find(Session.class, id);
    }

    @Override
    public List<Session> findAll() {
        return (List<Session>) entityManager.createQuery("from Session").getResultList();
    }

    public List<Session> findAllId( int id){
        return  entityManager.createQuery("from Session s where s.movie.id =:id", Session.class).setParameter("id",id).getResultList();
    }
    @Override
    public Session save(Session session) {
        Movie movie = entityManager.find(Movie.class, session.getMovieId());
        session.setMovie(movie);
        entityManager.persist(session);
        return session;
    }

    @Override
    public Session updateById(int id, Session session) {
        Session oldSession = new Session();
        oldSession.setName(session.getName());
        oldSession.setStart(session.getStart());
        oldSession.setFinish(session.getFinish());
        oldSession.setRooms(session.getRooms());
        oldSession.setMovie(session.getMovie());
        entityManager.merge(oldSession);
        return oldSession;
    }

    @Override
    public void deleteById(int id) {
        entityManager.remove(entityManager.find(Session.class, id));

    }
}

