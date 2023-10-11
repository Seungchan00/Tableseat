package TableSeat.TableSeat.controller;


import TableSeat.TableSeat.service.RestaurantService;
import TableSeat.TableSeat.service.TableService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class TableController {
    private final TableService tableService;
    private final RestaurantService restaurantService;

    @GetMapping("/restaurants/{restaurantid}/button")
    public String showTable(@PathVariable("restaurantid")Long restaurantsid, Model model , HttpSession session){


    }
