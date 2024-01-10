package i4.miniproject.miniproject.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import i4.miniproject.miniproject.entity.Customers;
import i4.miniproject.miniproject.repository.CustomerRepo;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepo cr;

    public Customers getCustomers(Customers c) {
        return cr.findById(c.getId()).orElse(null);
    }

    public Customers getCustomer(String email, String password) {
        return cr.getCustomer(email, password);
    }

    public Customers getCustomer(String email) {
        return cr.getCustomer(email);
    }

    public Customers createAccount(Customers c){
        return cr.save(c);
    }

    public boolean customerExist(String email){
        Optional<Customers> customer =  cr.findByEmail(email);
        return customer.isPresent();
    }
}
