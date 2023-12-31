package cinemaMvc.service.Impl;

import cinemaMvc.model.Cinema;
import cinemaMvc.service.ServiceLayer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CinemaService implements ServiceLayer<Cinema> {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Cinema findById(int id) {
        return entityManager.find(Cinema.class,id);
    }

    @Override
    public List<Cinema> findAll() {
        return (List<Cinema>) entityManager.createQuery("from Cinema").getResultList();
    }

    @Override
    public Cinema save(Cinema cinema) {
        entityManager.persist(cinema);
        return cinema;
    }

    @Override
    public Cinema updateById(int id, Cinema cinema) {
        Cinema oldCinema = entityManager.find(Cinema.class,id);
        oldCinema.setName(cinema.getName());
        oldCinema.setAddress(cinema.getAddress());
        oldCinema.setRoom(cinema.getRoom());
        entityManager.merge(oldCinema);
        return oldCinema;
    }

    @Override
    public void deleteById(int id) {
        entityManager.remove(entityManager.find(Cinema.class,id));

    }
}

