package pl.szybkie.szybkiepl.advert;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Transient;

import pl.szybkie.szybkiepl.CarModel.CarModel;
import pl.szybkie.szybkiepl.dealership.Dealership;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "adverts")
public class Advert{
    @Id
    @Column(name="advert_id", length = 38, nullable = false, unique = true)
    private Long id;

    @Column(name = "price", length = 38, nullable = false, unique = false)
    private double price;

    @Column(name = "description", length = 32, nullable = true, unique = false)
    private String description;

    @ManyToOne
    private CarModel carModel;
    
    @ManyToOne
    private Dealership dealership;

    @Column(name="taken_until")
    private OffsetDateTime takenUntil;

    @Transient
    private double distance;

    public Advert() {}

    public Advert(Long id, double price, String description, CarModel carModel, Dealership dealership, OffsetDateTime takenUntil){
        this.id = id;
        this.price = price;
        this.description = description;
        this.carModel = carModel;
        this.dealership = dealership;
        this.takenUntil = takenUntil;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return this.description;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public CarModel getCarModel() {
        return this.carModel;
    }

    public void setCarModel(CarModel carModel){
        this.carModel = carModel;
    }

    public Dealership getDealership() {
        return this.dealership;
    }


    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double new_distance) {
        if (new_distance < 0)
        {
            new_distance = 0;
        }
        this.distance = new_distance;
    }

    public OffsetDateTime getTakenUntil(){
        return this.takenUntil;
    }

    public void setTakenUntil(OffsetDateTime takenUntil){
        this.takenUntil = takenUntil;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", price='" + getPrice() + "'" +
            ", description='" + getDescription() + "'" +
            ", carModel='" + getCarModel() + "'" +
            ", dealership='" + getDealership() + "'" +
            "}";
    }

    public void setDealership(Dealership dealership){
        this.dealership = dealership;
    }

}
