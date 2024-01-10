package i4.miniproject.miniproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "items")
public class Items {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private String namep;
    private String descriptions;
    private String image;
    private double price; 
    private String category; 
    private boolean bestsellerflag;

    public Items() {
        // Default constructor
    }
    
    public Items(String namep, String descriptions, double price, String category) {
        this.namep = namep;
        this.descriptions = descriptions;
        this.price = price;
        this.category = category;
        this.image = "R.jpg";
        this.bestsellerflag = false;
    }

    public Items(String namep, String descriptions, double price) {
        this.namep = namep;
        this.descriptions = descriptions;
        this.price = price;
        this.image = "R.jpg";
        this.category = null;
        this.bestsellerflag = false;
    }

    public Items(String namep, String descriptions, String image, double price, String category) {
        this.namep = namep;
        this.descriptions = descriptions;
        this.image = image;
        this.price = price;
        this.category = category;
        this.bestsellerflag = false;
    }

    public Items(String namep, String descriptions, String image, double price, String category, boolean bestsellerflag) {
        this.namep = namep;
        this.descriptions = descriptions;
        this.image = image;
        this.price = price;
        this.category = category;
        this.bestsellerflag = bestsellerflag;
    }

    public String getName() {
        return namep;
    }

    public void setName(String namep) {
        this.namep = namep;
    }

    public String getDescription() {
        return descriptions;
    }

    public void setDescription(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isBestsellerflag() {
        return bestsellerflag;
    }

    public void setBestsellerflag(boolean bestsellerflag) {
        this.bestsellerflag = bestsellerflag;
    }
    
    public Integer getId() {
        return id;
    }
    
}
