package TableSeat.TableSeat.controller;

import TableSeat.TableSeat.domain.Restaurant;
import TableSeat.TableSeat.service.RestaurantService;
import TableSeat.TableSeat.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @Autowired
    private TableService tableService;

    @GetMapping("/restaurant")
    public String showRestaurant(Model model){
        List<Restaurant> restaurantList = restaurantService.findRestaurants();
        Map<Long, String> restaurantStatuses = new HashMap<>();
        Map<Long, Boolean> restaurantFullStatus = new HashMap<>();

        for (Restaurant restaurant : restaurantList) {
            restaurantStatuses.put(restaurant.getId(), tableService.checkRestaurantStatus(restaurant.getId()));
            restaurantFullStatus.put(restaurant.getId(), tableService.isRestaurantFull(restaurant.getId()));
        }

        model.addAttribute("restaurantList", restaurantList);
        model.addAttribute("restaurantStatuses", restaurantStatuses);
        model.addAttribute("restaurantFullStatus", restaurantFullStatus);

        return "restaurants/restaurantView";
    }

    @GetMapping("/restaurantsearchname")
    public String searchRestaurantForm(){

        return "restaurants/restaurantsearch";
    }

    @PostMapping("/restaurants/search")
    public String searchRestaurant(@RequestParam("name") String name, Model model) {
        List<Restaurant> restaurants = restaurantService.findByname(name);
        if(restaurants == null || restaurants.isEmpty()) {
            model.addAttribute("error", "해당하는 식당을 찾을 수 없습니다.");
        } else {
            model.addAttribute("restaurants", restaurants);

        }
        return "restaurants/search";
    }

}

