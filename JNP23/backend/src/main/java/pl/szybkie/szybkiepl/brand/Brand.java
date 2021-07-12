package pl.szybkie.szybkiepl.brand;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "brand_id", length = 38, nullable = false, unique = true)
    private Long id;

    @Column(name = "name", length = 32, nullable = false, unique = false)
    private String name;
    
    @Column(name = "country", length = 32, nullable = false, unique = false)
    private String country;

    public String getName(){
        return this.name;
    }
    public String getCountry(){
        return this.country;
    }
}
