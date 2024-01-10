package i4.miniproject.miniproject.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import i4.miniproject.miniproject.entity.Items;
import i4.miniproject.miniproject.repository.CartRepo;
import i4.miniproject.miniproject.service.CartService;
import i4.miniproject.miniproject.service.ItemsService;
import jakarta.servlet.http.HttpSession;


@Controller
public class MainController {
    @Autowired
    private ItemsService is;

    @Autowired 
    private CartService cs;

    @Autowired
    private CartRepo cr;

    public List<Items> makeProduct(){
        List<Items> products = new ArrayList<Items>();

        products.add(new Items("Coffee Latte with milk", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Odit dicta similique totam tempora ab quas molestias suscipit ipsum. Modi aliquid dolore distinctio iusto nihil inventore sapiente deleniti impedit aut, nisi cumque et unde fugit corrupti corporis optio libero alias quos magnam dolores minus odit mollitia vitae! Facilis ea accusantium exercitationem nulla accusamus quidem totam voluptatem, nam fuga et tempora eos aut, dolores quae quibusdam ipsum id voluptatibus, possimus blanditiis consequatur optio architecto. Incidunt exercitationem dolorem cum quam nesciunt tenetur id nemo neque error? Aut eligendi amet dignissimos alias quam, voluptatum vero sapiente ex, repellat, modi in deleniti. Assumenda, corporis maiores?","R.jpg", 4.99, "Beverage"));
        products.add(new Items("Croissant", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Odit dicta similique totam tempora ab quas molestias suscipit ipsum. Modi aliquid dolore distinctio iusto nihil inventore sapiente deleniti impedit aut, nisi cumque et unde fugit corrupti corporis optio libero alias quos magnam dolores minus odit mollitia vitae! Facilis ea accusantium exercitationem nulla accusamus quidem totam voluptatem, nam fuga et tempora eos aut, dolores quae quibusdam ipsum id voluptatibus, possimus blanditiis consequatur optio architecto. Incidunt exercitationem dolorem cum quam nesciunt tenetur id nemo neque error? Aut eligendi amet dignissimos alias quam, voluptatum vero sapiente ex, repellat, modi in deleniti. Assumenda, corporis maiores?","B.jpg", 2.99, "Food"));
        products.add(new Items("Coffee Latte with milk", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Odit dicta similique totam tempora ab quas molestias suscipit ipsum. Modi aliquid dolore distinctio iusto nihil inventore sapiente deleniti impedit aut, nisi cumque et unde fugit corrupti corporis optio libero alias quos magnam dolores minus odit mollitia vitae! Facilis ea accusantium exercitationem nulla accusamus quidem totam voluptatem, nam fuga et tempora eos aut, dolores quae quibusdam ipsum id voluptatibus, possimus blanditiis consequatur optio architecto. Incidunt exercitationem dolorem cum quam nesciunt tenetur id nemo neque error? Aut eligendi amet dignissimos alias quam, voluptatum vero sapiente ex, repellat, modi in deleniti. Assumenda, corporis maiores?","R.jpg", 4.99, "Beverage"));
    
        products.get(0).setBestsellerflag(true);
        return products;
    }

    @GetMapping("/")
    public String Home(Model m,HttpSession session) {
        if(session.getAttribute("username") != null && session.getAttribute("username").equals("admin")){
            m.addAttribute("role", session.getAttribute("username"));
            m.addAttribute("allitem", cs.getQuantity((int) session.getAttribute("id")));
        }
        else if(session.getAttribute("username") != null){
            m.addAttribute("role", "customer");
            m.addAttribute("username", session.getAttribute("username"));
            m.addAttribute("allitem", cs.getQuantity((int) session.getAttribute("id")));
        }
        else{
            m.addAttribute("role", "user");
            m.addAttribute("allitem", 0);
        }
        m.addAttribute("pro", is.getItems());
        

        return "Menu";
    }

    @GetMapping("/Logout")
    public String Logout(HttpSession session){
        if(session.getAttribute("username") != null){
            session.invalidate();
            return "redirect:/";
        }
        return "redirect:/";
    }

    @GetMapping("/Admin")
    public String getItem(Model m){
        m.addAttribute("item", is.getItems());
        return "Admin";
    }

    @GetMapping("/Admin/InsertData")
    public String insertItem(Model m){
        m.addAttribute("title", "Insert Data");
        return "Dataitems";
    }

    @GetMapping("/Admin/UpdateData")
    public String updateItem(@RequestParam("id") String id ,Model m){
        m.addAttribute("p", is.getItem(Integer.parseInt(id)));
        m.addAttribute("title", "Edit Data");
        return "Dataitems";
    }
  
    @GetMapping("/Itemdetails/{id}")
    public String ItemDetail(@PathVariable int id, Model m, HttpSession session) {
        int users_id = (int) session.getAttribute("id");
        m.addAttribute("allitem", cs.getQuantity(users_id));
        m.addAttribute("item", is.getItem(id));
        m.addAttribute("items", is.getItems());
        return "ItemDetails";
    }

    @GetMapping("/Checkout")
    public String Checkout(Model m, HttpSession session) {
        int id = (int) session.getAttribute("id");
        if(session.getAttribute("id")!=null){
             m.addAttribute("quantity",cr.getAllProductQuantity(id));
            m.addAttribute("allitem", cs.getQuantity(id));
            m.addAttribute("items", cs.getItemCart(id));
            return "Checkout";
        }
        return "Menu";
    }

    @GetMapping("/Testing/test")
    public String test(Model m){
        m.addAttribute("quantity",cr.getAllProductQuantity(1));
        m.addAttribute("items", cs.getItemCart(1));
        
        return "Testing";
    }
}
