package pl.szybkie.szybkiepl.CarModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pl.szybkie.szybkiepl.Engine.Engine;
import pl.szybkie.szybkiepl.brand.Brand;

@Entity
@Table(name = "car_model")
public class CarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="car_id", length = 38, nullable = false, unique = true)
    private Long id;

    @Column(name = "name", length=38, nullable = false, unique = false)
    private String name;

    @Column(name ="production_year", length=38, nullable = false, unique = false)
    private Long productionYear;

    @Column(name ="seats", length=38, nullable = false, unique = false)
    private Long seats;
    
    @ManyToOne
    @JoinColumn(name = "engine_engine_id")
    private Engine engine;
    
    @ManyToOne
    @JoinColumn(name = "brand_brand_id")
    private Brand brand;

    public String getName(){
        return this.name;
    }

    public Long getProductionYear(){
        return this.productionYear;
    }

    public Long getSeats(){
        return this.seats;
    }
    
    public Engine getEngine(){
        return this.engine;
    }
    
    public Brand getBrand(){
        return this.brand;
    }
    
}
