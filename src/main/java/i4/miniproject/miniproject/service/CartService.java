package i4.miniproject.miniproject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import i4.miniproject.miniproject.entity.Cart;
import i4.miniproject.miniproject.entity.Items;
import i4.miniproject.miniproject.repository.CartRepo;
import i4.miniproject.miniproject.repository.ItemsRepo;

@Service
public class CartService {
    @Autowired
    private CartRepo cs;

    @Autowired
    private ItemsRepo ir;

    public List<Cart> getCarts(){
        return cs.findAll();
    }

    public Cart insertCart(Cart c){
        return cs.save(c);
    }

    public Cart updateQTYCart(Integer id, Integer pid, int qty){
        Cart c = cs.getCartBypid(id, pid);
        c.setQuantity(qty);
        return cs.save(c);
    }

    public Integer getQuantity(int id){
        return cs.countCart(id);
    }

    public List<Items> getItemCart(int id){
        List<Integer> i = cs.getPID(id);
        List<Items> item = new ArrayList<Items>();
        for(Integer k: i){
            item.add(ir.findById(k).orElse(null));
        }

        return item;
    }

}
