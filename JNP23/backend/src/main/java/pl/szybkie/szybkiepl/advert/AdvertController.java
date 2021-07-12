package pl.szybkie.szybkiepl.advert;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.szybkie.szybkiepl.CarModel.CarModel;
import pl.szybkie.szybkiepl.CarModel.CarModelRepository;
import pl.szybkie.szybkiepl.dealership.Dealership;
import pl.szybkie.szybkiepl.dealership.DealershipRepository;
import pl.szybkie.szybkiepl.utilityFunctions.UtilityFunctions;


@RestController  // for handling requests - GET
public class AdvertController {
    public static final int PRICE_MULTIPLIER = 100;
    public static final int RATING_MULTIPLIER = 20;

    @Autowired    
    private AdvertRepository advertRepository;

    @Autowired
    private CarModelRepository carModelRepository;

    @Autowired
    private DealershipRepository dealershipRepository;

    @GetMapping("/advert")  // GET endpoint
    @CrossOrigin
    public List<Advert> advertInfo(@RequestParam(required=false, defaultValue="-1") int distance,
                                                        @RequestParam(required=false, defaultValue="-1") int min_engine_capacity,
                                                        @RequestParam(required=false, defaultValue="-1") int max_engine_capacity,
                                                        @RequestParam(required=false, defaultValue="-1") int min_price,
                                                        @RequestParam(required=false, defaultValue="-1") int max_price,
                                                        @RequestParam(required=false, defaultValue="-1") int min_rating,
                                                        @RequestParam(required=false, defaultValue="-1") int max_rating,
                                                        @RequestParam(required=false, defaultValue="52.2308") double posX,
                                                        @RequestParam(required=false, defaultValue="21.0098") double posY)

    {  
        List<Advert> adverts = advertRepository.findAll();
        List<Advert> goodAdverts = new ArrayList<Advert>();
        
        for(Advert advert : adverts){
            boolean suitableAdvert = true;
            double actual_distance = UtilityFunctions.distance(posX, advert.getDealership().getLocation().getCoordX(), posY, advert.getDealership().getLocation().getCoordY(), 0, 0)/1000;

            if(distance != -1 && actual_distance > distance){
                suitableAdvert = false;
            }
            if(min_engine_capacity != -1 && advert.getCarModel().getEngine().getSize() < min_engine_capacity*100){
                suitableAdvert = false;
            }
            if(max_engine_capacity != -1 && advert.getCarModel().getEngine().getSize() > max_engine_capacity*100){
                suitableAdvert = false;
            }
            if(min_price != -1 && advert.getPrice() < min_price*PRICE_MULTIPLIER){
                suitableAdvert = false;
            }
            if(max_price != -1 && advert.getPrice() > max_price*PRICE_MULTIPLIER){
                suitableAdvert = false;
            }
            if(min_rating != -1 && advert.getDealership().getAvgRating()*RATING_MULTIPLIER < min_rating){
                suitableAdvert = false;
            }
            if(min_rating != -1 && advert.getDealership().getAvgRating()*RATING_MULTIPLIER > max_rating){
                suitableAdvert = false;
            }
            if(advert.getTakenUntil().compareTo(OffsetDateTime.now()) > 0){
                suitableAdvert = false;   
            }
            if(suitableAdvert){
                advert.setDistance(actual_distance);
                goodAdverts.add(advert);
            }
        }
        return goodAdverts;
    }

    @Transactional
    @CrossOrigin
    @PostMapping("/create_advert")
        public String createAdvert(@RequestBody String json){
            ObjectMapper objectMapper = new ObjectMapper();
            try{
                List<Advert> adverts = advertRepository.findAll();
                Long id = 0L;
                for (Advert advert : adverts) {
                    if (id < advert.getId())
                        id = advert.getId();
                }
                id += 1;
                Map<String, String> result = objectMapper.readValue(json, Map.class);
                Long car_id = Long.parseLong(result.get("car_model_id"));
                Long dealership_id = Long.parseLong(result.get("dealership_id"));
                Optional<CarModel> car = carModelRepository.findById(car_id);
                Optional<Dealership> dealership = dealershipRepository.findById(dealership_id);
                Double price = Double.parseDouble(result.get("price"));
                String description = result.get("description");
                if(car.isPresent() && dealership.isPresent()){
                    System.out.println("######################################ID : " + id + ", price " + price);
                    Advert newAdvert = new Advert(id, price, description, car.get(), dealership.get(), OffsetDateTime.now());
                    advertRepository.saveAndFlush(newAdvert);
                    return "{success:true}";
                }
                else{
                    return "{success:false, reason:'No car or description with such id'}";
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return "{success:false, reason: 'Something failed'}";
        }
        
        @CrossOrigin
        @GetMapping("/book_advert")
        public String createAdvert(@RequestParam(required = true) int advert_id)
        {
            Advert advert = advertRepository.getOne(Long.valueOf(advert_id));
            advert.setTakenUntil(OffsetDateTime.now().plusSeconds(30)); //pol minuty dla potrzeb testow
            advertRepository.save(advert);
            return "{success:true}";
        }
    
  }
