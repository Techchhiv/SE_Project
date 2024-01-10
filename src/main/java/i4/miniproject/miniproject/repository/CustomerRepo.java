package i4.miniproject.miniproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import i4.miniproject.miniproject.entity.Customers;


@Repository
public interface CustomerRepo extends JpaRepository<Customers, Integer>{
    
    @Query(value = "select * from users where email = :email and passwords = :password", nativeQuery = true)
    Customers getCustomer(@Param("email") String email, @Param("password") String password);
    
    @Query(value = "select * from users where email = :email", nativeQuery = true)
    Customers getCustomer(@Param("email") String email);

    Optional<Customers> findByEmail(String email);

}
