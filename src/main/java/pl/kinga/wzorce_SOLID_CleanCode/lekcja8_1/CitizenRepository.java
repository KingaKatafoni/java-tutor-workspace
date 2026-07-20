package pl.kinga.wzorce_SOLID_CleanCode.lekcja8_1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CitizenRepository {
   private final Map<String, Citizen> citizens = new HashMap<>();


   public void save(Citizen citizen){

       if(citizens.containsKey(citizen.pesel())){
           throw new IllegalStateException("Citizen already registered: " + citizen.pesel());
       }

       citizens.put(citizen.pesel(), citizen);
   }

   public Citizen findByPesel(String pesel){

       if (!citizens.containsKey(pesel)){
           throw new IllegalStateException("Citizen with pesel: " + pesel + " is not registered");
       }

      return citizens.get(pesel);

   }

   public List<Citizen> findAll(){
       return citizens.values().stream().toList();
   }

}
