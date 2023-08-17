package cinemaMvc.service.Impl;

import cinemaMvc.model.Movie;
import cinemaMvc.service.ServiceLayer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MovieService implements ServiceLayer<Movie> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Movie findById(int id) {
        return entityManager.find(Movie.class, id);
    }

    @Override
    public List<Movie> findAll() {
        return (List<Movie>) entityManager.createQuery("from Movie ").getResultList();
    }

    @Override
    public Movie save(Movie movie) {
        entityManager.persist(movie);
        return movie;
    }

    @Override
    public Movie updateById(int id, Movie movie) {
        Movie oldMovie = new Movie();
        oldMovie.setName(movie.getName());
        oldMovie.setGenres(movie.getGenres());
        oldMovie.setCountry(movie.getCountry());
        oldMovie.setLanguage(movie.getLanguage());
        oldMovie.setSessions(movie.getSessions());
        entityManager.merge(oldMovie);
        return oldMovie;
    }

    @Override
    public void deleteById(int id) {
        entityManager.remove(entityManager.find(Movie.class, id));
    }
}
