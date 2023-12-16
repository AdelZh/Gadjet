package peaksoft.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import peaksoft.enums.Category;

import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    @ElementCollection
    private List<String> images;
    private String characteristic;
    private Boolean isFavorite;
    private String madeIn;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne(fetch = FetchType.EAGER)
    private Brand brand;
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Comment> comment;
    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<Favorite> favorite;



    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", images=" + images +
                ", characteristic='" + characteristic + '\'' +
                ", isFavorite=" + isFavorite +
                ", madeIn='" + madeIn + '\'' +
                ", category=" + category +
                ", brand=" + brand +
                ", comment=" + comment +
                '}';
    }
}
