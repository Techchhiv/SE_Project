package i4.miniproject.miniproject.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import i4.miniproject.miniproject.entity.Items;
import i4.miniproject.miniproject.service.ItemsService;

@RestController
public class AdminController {
    @Autowired
    private ItemsService is;

    @PostMapping("/Admin/InsertData/insert")
    public RedirectView addItems(@RequestParam("img") MultipartFile img,@ModelAttribute Items item) throws IOException{
        String filename = img.getOriginalFilename();
        Path location = Paths.get("src/main/resources/static/images/" + item.getCategory().toLowerCase(), filename);
        Files.copy(img.getInputStream(), location, StandardCopyOption.REPLACE_EXISTING);
        item.setImage(filename);
        is.createItem(item);
        return new RedirectView("/Admin");
    }

    @PostMapping("/Admin/UpdateData/update")
    public RedirectView updateItem(@RequestParam("img") MultipartFile img,@RequestParam("ids") String ids, @ModelAttribute Items i) throws IOException{
        String filename = img.getOriginalFilename();
        Path targetLocation = Paths.get("src/main/resources/static/images/" + i.getCategory().toLowerCase(), filename);
        Files.copy(img.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        
        i.setImage(filename);
        is.updateItem(Integer.parseInt(ids), i);

        return new RedirectView("/Admin");
    }

    @GetMapping("/Admin/delete")
    public RedirectView deleteItem(@RequestParam("id") String id){
        is.deleteItem(Integer.parseInt(id));
        return new RedirectView("/Admin");
    }
}
