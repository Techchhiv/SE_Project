package i4.miniproject.miniproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import i4.miniproject.miniproject.entity.Cart;
import i4.miniproject.miniproject.service.CartService;
import jakarta.servlet.http.HttpSession;

@RestController
public class CartController{
   @Autowired
   private CartService cs;

    @PostMapping("/Itemdetails/{id}/insert")
    public RedirectView insertCart(@PathVariable("id") String id,@ModelAttribute Cart c, HttpSession session){
        if(session.getAttribute("username")!=null){
            int i = (int) session.getAttribute("id");
            c.setPid(Integer.parseInt(id));
            c.setUsers_id(i);
            cs.insertCart(c);
        }
        return new RedirectView("/");
    }


}

