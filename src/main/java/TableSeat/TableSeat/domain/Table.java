package TableSeat.TableSeat.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter
public class Table {
    @Id
    @Column(name = "table_id", insertable = true, updatable = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Enumerated(EnumType.STRING)
    private Tablestatus tablestatus;

    @Column(name = "table_people")
    private int tablePeople;
    @Column(name = "table_Start_Time")
    private LocalDateTime tableStarttime;
    @Column(name = "table_End_Time")
    private LocalDateTime tableEndtime;

    @Column(name = "remain_time")
    private int remainTime;

    @OneToMany(mappedBy = "table")
    private List<Order> orders;





}