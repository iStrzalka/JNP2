package pl.szybkie.szybkiepl.dealership;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import pl.szybkie.szybkiepl.Location.Location;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "dealership")
public class Dealership {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="dealership_id", length = 38, nullable = false, unique = true)
    private Long id;

    @Column(name="name", length = 38, nullable = true, unique = false)
    private String name;

    @Column(name="phone_no", length = 9, nullable = false, unique = false)
    private String phoneNo;

    @Column(name="avg_rating", length = 38, nullable = true, unique = false)
    private double AvgRating;

    @ManyToOne
    private Location location;

    public Long getId(){
        return this.id;
    }

    public Location getLocation(){
        return this.location;
    }

    public String getphoneNo(){
        return this.phoneNo;
    }

    public double getAvgRating(){
        return this.AvgRating;
    }
}
