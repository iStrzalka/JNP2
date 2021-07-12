package pl.szybkie.szybkiepl.Location;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="location_id", length = 38, nullable = false, unique = true)
    private Long id;

    @Column(name="country", length = 32, nullable = false, unique = false)
    private String country;

    @Column(name="region", length = 32, nullable = false, unique = false)
    private String region;

    @Column(name="city", length = 32, nullable = false, unique = false)
    private String city;

    @Column(name="coord_x", nullable = false, unique = false)
    private double coordX;

    @Column(name="coord_y", nullable = false, unique = false)
    private double coordY;

    public String getCountry(){
        return this.country;
    }
    
    public String getRegion(){
        return this.region;
    }

    public String getCity(){
        return this.city;
    }

    public Double getCoordX(){
        return this.coordX;
    }

    public Double getCoordY(){
        return this.coordY;
    }


}
