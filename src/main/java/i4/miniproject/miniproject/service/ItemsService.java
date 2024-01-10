package i4.miniproject.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import i4.miniproject.miniproject.entity.Items;
import i4.miniproject.miniproject.repository.ItemsRepo;

@Service
public class ItemsService {
    @Autowired
    private ItemsRepo ir;

    public Items createItem(Items item){
        return ir.save(item);
    }

    public List<Items> saveProduct(List<Items> item) {
        return ir.saveAll(item);
    }

    public List<Items> getItems(){
        return ir.findAll();
    }

    public Items getItem(int id){
        return ir.findById(id).orElse(null);
    }

    public String deleteItem(int id){
        ir.deleteById(id);
        return "Items have removed: "+ id;
    }

    public Items updateItem(Integer id, Items item){
        Items i = ir.findById(id).orElse(null);
        i.setName(item.getName());
        i.setDescription(item.getDescription());
        i.setPrice(item.getPrice());
        i.setImage(item.getImage());
        i.setCategory(item.getCategory());
        i.setBestsellerflag(i.isBestsellerflag());

        return ir.save(i);
    } 
    
}
