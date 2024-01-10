package i4.miniproject.miniproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import i4.miniproject.miniproject.entity.Customers;
import i4.miniproject.miniproject.service.CustomerService;
import jakarta.servlet.http.HttpSession;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService cs;

    @GetMapping("/session-info")
    public ResponseEntity<String> getSessionInfo(HttpSession session) {
        if (session.getAttribute("username") != null) {
            String username = (String) session.getAttribute("username");
            return ResponseEntity.ok("Username in session: " + username);
        } else {
            return ResponseEntity.ok("No username in session");
        }
    }

    @GetMapping("/Login")
    public String userLoginString(Model m, HttpSession session) {
        if(session.getAttribute("fail")!=null) m.addAttribute("fail", session.getAttribute("fail"));
        m.addAttribute("title", "Login");
        return "Register";
    }

    @PostMapping("/Login/authenticateL")
    public String loginAuthenticate(@ModelAttribute Customers c,HttpSession session, Model m){
        String name = "";
        Customers customer = cs.getCustomer(c.getEmail());
        if(c.getEmail().equals("admin@gmail.com") && c.getPasswords().equals("admin")){
            name = cs.getCustomer("admin@gmail.com", "admin").getUsername();
            session.setAttribute("id", customer.getId());
            session.setAttribute("username", name);
        }else if(c.getEmail().equals(customer.getEmail()) && c.getPasswords().equals(customer.getPasswords())){
            name = cs.getCustomer(c.getEmail(), c.getPasswords()).getUsername();
            session.setAttribute("id", customer.getId());
            session.setAttribute("username", name); 
        }
        else{
            session.setAttribute("fail", "Invalid email or password");
            return "redirect:/Login";
        }
        return "redirect:/";
    }

    @GetMapping("/SignUp")
    public String userRegister(Model m,HttpSession session) {
        if(session.getAttribute("fail")!=null) m.addAttribute("fail", session.getAttribute("fail"));
        m.addAttribute("title", "Register");
        return "Register";
    }

    @PostMapping("/SignUp/register")
    public String signUp(@ModelAttribute Customers c,HttpSession session,Model m){
        if(!cs.customerExist(c.getEmail())){
            cs.createAccount(c);
            session.setAttribute("id", c.getId());
            session.setAttribute("username", c.getUsername());
            return "redirect:/";
        }else{
            session.setAttribute("fail", "User already registered");
            return "redirect:/SignUp";
        }
        
    }
}
