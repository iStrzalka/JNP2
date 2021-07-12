package pl.szybkie.szybkiepl.Engine;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "engine")
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "engine_id", length=38, nullable = false, unique = true)
    private Long id;

    @Column(name = "hp", length = 38, nullable = true, unique = false)
    private Long hp;

    @Column(name = "engine_size", length = 38, nullable = true, unique = false)
    private Long size;

    @Column(name = "fuel_type", length = 32, nullable = true, unique = false)
    private String fuel_type;

    public Long getHp(){
        return this.hp;
    }
    public Long getSize(){
        return this.size;
    }
    public String getFuelType(){
        return this.fuel_type;
    }
}
