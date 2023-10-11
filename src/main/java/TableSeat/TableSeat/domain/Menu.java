package TableSeat.TableSeat.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter @Setter
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="menu_id")
    private Long id;
    @Column(name ="menu_name")
    private String name;
    @Column(name ="menu_count")
    private int count;
    @Column(name = "menu_price")
    private Double price;
}
