package TableSeat.TableSeat.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter@Setter
public class Reservation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private long id;

    private int people;

    @Enumerated(EnumType.STRING)
    private Reservationstatus reservationstatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="restaurant_id")
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime reservation_time;

    @Column(name = "queue_number")
    private int queueNumber;


    public void setMember(Member member) {
        this.member = member;
        member.getReservations().add(this);
    }
}