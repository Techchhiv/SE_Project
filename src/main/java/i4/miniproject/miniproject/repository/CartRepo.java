package i4.miniproject.miniproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import i4.miniproject.miniproject.entity.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer>{
    @Query(value = "select * from carts where users_id = :id and pid = :pid", nativeQuery = true)
    Cart getCartBypid(@Param("id") int id, @Param("pid") int pid);

    @Query(value = "select count(c.pid) from carts c where users_id = :id",nativeQuery = true)
    int countCart(@Param("id") int id);

    @Query(value = "select pid from carts where users_id = :users_id",nativeQuery = true)
    List<Integer> getPID(@Param("users_id") int users_id);
    
    @Query(value = "select quantity from carts c join items i on c.pid = i.id where users_id = :users_id",nativeQuery = true)
    List<Integer> getAllProductQuantity(@Param("users_id") int users_id);

    

}
