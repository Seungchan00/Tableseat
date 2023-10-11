package TableSeat.TableSeat.Repository;

import TableSeat.TableSeat.domain.Table;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class TableRepository {
    private final EntityManager em;

    public void save(Table table){
        if(table.getId() == null){
            em.persist(table);
        }
        else{
            em.merge(table);
        }
    }
    public Table findOne(Long id) {
        return em.find(Table.class, id);
    }
    public List<Table> findAll(){
        return em.createQuery("select T from Table T", Table.class).getResultList();
    }

    public List<Table> findAllbyRestaurantId(Long id){
        return em.createQuery("select T from Table T where T.restaurant.id = : id", Table.class).setParameter("id",id).getResultList();
    }





}




