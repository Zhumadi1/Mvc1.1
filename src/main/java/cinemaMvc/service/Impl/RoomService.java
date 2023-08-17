package cinemaMvc.service.Impl;

import cinemaMvc.model.Cinema;
import cinemaMvc.model.Room;
import cinemaMvc.service.ServiceLayer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RoomService implements ServiceLayer<Room> {
    @PersistenceContext
    private EntityManager entityManager;

    private CinemaService cinemaService;

    @Override
    public Room findById(int id) {
        return entityManager.find(Room.class, id);
    }

    @Override
    public List<Room> findAll() {
        return (List<Room>) entityManager.createQuery("from Room").getResultList();
    }

    public List<Room> findAllId(int id) {
        return (List<Room>) entityManager.createQuery("from Room r where r.cinema.id =:id", Room.class).setParameter("id", id).getResultList();
    }

    @Override
    public Room save(Room room) {
        Cinema cinema = entityManager.find(Cinema.class,room.getCinemaId());
        room.setCinema(cinema);
        entityManager.persist(room);
        return room;
    }

    @Override
    public Room updateById(int id, Room room) {
        Room oldRoom = new Room();
        oldRoom.setName(room.getName());
        oldRoom.setRating(room.getRating());
        oldRoom.setCinema(room.getCinema());
        oldRoom.setPlaceList(room.getPlaceList());
        oldRoom.setSessions(room.getSessions());
        entityManager.merge(oldRoom);
        return oldRoom;
    }

    @Override
    public void deleteById(int id) {
        entityManager.remove(entityManager.find(Room.class, id));

    }
}

