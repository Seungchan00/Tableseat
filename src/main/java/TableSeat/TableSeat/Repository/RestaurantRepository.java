package TableSeat.TableSeat.Repository;

import TableSeat.TableSeat.domain.Restaurant;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RestaurantRepository {

    private final EntityManager em;

    public void save(Restaurant restaurant) {
        if (restaurant.getId() == null) {               //  restaurantId가 null이면 바로 persist하고.(DB에 올리고)
            em.persist(restaurant);
        }
        else {
            if (em.find(Restaurant.class, restaurant.getId()) != null) {        //DB에있는 값이랑 비교해서 있으면
                throw new EntityExistsException("Restaurant with id already exists");   // 오류 발생
            }
            em.merge(restaurant);
        }
    }
    public Restaurant findOne (Long id){
        return em.find(Restaurant.class, id);
    }

    public List<Restaurant> findAll () {
        return em.createQuery("select r from Restaurant r", Restaurant.class).getResultList();
    }

    public List<Restaurant> findByPartialName(String partialName) {
        return em.createQuery("select r from Restaurant r where r.name like concat('%', :name, '%')", Restaurant.class)
                .setParameter("name", partialName)
                .getResultList();
    }



}