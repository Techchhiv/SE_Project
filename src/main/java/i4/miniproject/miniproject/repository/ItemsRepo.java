package i4.miniproject.miniproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import i4.miniproject.miniproject.entity.Items;
import java.util.List;


@Repository
public interface ItemsRepo extends JpaRepository<Items,Integer>{
    
    //Find Items by Category
    @Query(value = "select * from items where category = '?1'", nativeQuery = true)
    List<Items> findByCategory(String category);
}
