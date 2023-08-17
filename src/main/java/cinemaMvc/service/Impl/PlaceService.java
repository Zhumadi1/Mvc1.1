package cinemaMvc.service.Impl;

import cinemaMvc.model.Place;
import cinemaMvc.service.ServiceLayer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PlaceService implements ServiceLayer<Place> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Place findById(int id) {
        return entityManager.find(Place.class, id);
    }

    @Override
    public List<Place> findAll() {
        return (List<Place>) entityManager.createQuery("from Place").getResultList();
    }

    @Override
    public Place save(Place place) {
        entityManager.persist(place);
        return place;
    }

    @Override
    public Place updateById(int id, Place place) {
        Place oldPlace = new Place();
        oldPlace.setX(place.getX());
        oldPlace.setY(place.getY());
        oldPlace.setPrice(place.getPrice());
        oldPlace.setRoom(place.getRoom());
        entityManager.merge(oldPlace);
        return oldPlace;
    }

    @Override
    public void deleteById(int id) {
        entityManager.remove(entityManager.find(Place.class, id));

    }
}

