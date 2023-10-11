package TableSeat.TableSeat.service;

import TableSeat.TableSeat.Repository.RestaurantRepository;
import TableSeat.TableSeat.domain.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor

public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Transactional(readOnly = false)
    public void saveRestauran(Restaurant restaurant){
        restaurantRepository.save(restaurant);
    }
    public Restaurant findOne(Long restaurantId){
        return restaurantRepository.findOne(restaurantId);
    }
    public List<Restaurant> findRestaurants(){
        return restaurantRepository.findAll();
    }
    public List<Restaurant> findByname(String name){
        return restaurantRepository.findByPartialName(name);
    }

}
