package TableSeat.TableSeat.service;

import TableSeat.TableSeat.Repository.TableRepository;
import TableSeat.TableSeat.domain.Table;
import TableSeat.TableSeat.domain.Tablestatus;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TableService {
    private final TableRepository tableRepository;

    @Transactional(readOnly = false)
    public void saveTable(Table table){
        tableRepository.save(table);
    }
    public Table findOne(Long tableId){
        return tableRepository.findOne(tableId);
    }
    public List<Table> findtables(){
        return tableRepository.findAll();
    }
    public List<Table> findtablebyid(long restaurantId){
        return tableRepository.findAllbyRestaurantId(restaurantId);
    }

    @Transactional(readOnly = false)
    @Scheduled(fixedRate = 60000) // 60000ms = 1분
    public void updateRemainingTimes() {
        List<Table> tables = tableRepository.findAll();

        for (Table table : tables) {
            int currentRemainingTime = table.getRemainTime();

            if (currentRemainingTime <= 0) {
                if (table.getTablestatus() != Tablestatus.NOSEATED) {
                    table.setRemainTime(0); // Set remaining time to 0
                    tableRepository.save(table);
                }
                continue;
            }
            table.setRemainTime((currentRemainingTime - 60));
            tableRepository.save(table);
        }

    }
    public String checkRestaurantStatus(Long restauranId){       //해당 레스토랑의 혼잡도를 나타내는 로직
        List<Table> tables = tableRepository.findAllbyRestaurantId(restauranId);
        int totalTables = tables.size();
        long noSeatedCount = tables.stream().filter(table -> table.getTablestatus() == Tablestatus.NOSEATED).count();

        float noSeatedPercent = ((float) noSeatedCount / totalTables) * 100;

        if(noSeatedPercent <= 50){
            return "원활";
        } else if (noSeatedPercent <= 80) {
            return "혼잡";
        } else if (noSeatedPercent < 100) {
            return "매후 혼잡";
        } else {
            return "자리 없음";
        }

    }


    public boolean isRestaurantFull(Long restaurantId) {            // 자리가 꽉 찼는지 안 찼는지
        List<Table> tables = tableRepository.findAllbyRestaurantId(restaurantId);
        return tables.stream().allMatch(table -> table.getTablestatus() != Tablestatus.NOSEATED);
    }
}


