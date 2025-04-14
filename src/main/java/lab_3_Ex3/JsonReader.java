package lab_3_Ex3;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

public class JsonReader {
    public static void main(String[] args){
        JSONParser parser = new JSONParser();

        try(FileReader reader = new FileReader("cars.json")){
            //{} = {JSONObject} || [] = [JSONArray]
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONArray carsArray = (JSONArray) jsonObject.get("cars");

            for(Object carObj : carsArray){
                JSONObject car = (JSONObject) carObj;
                String brand = (String) car.get("brand");
                Long brandId = (Long) car.get("brandId");

                System.out.println("brand: " + car.get("brand") + "ID: " + car.get("brandId"));

                JSONArray modelsArray = (JSONArray) car.get("models");
                if("Toyota".equalsIgnoreCase(brand) && brandId == 1){
                    LinkedHashMap<String, Object> newCar = new LinkedHashMap<>();

                    newCar.put("brand",brand);
                    newCar.put("brandId",brandId);

                    JSONArray models = (JSONArray) car.get("models");
                    JSONArray singleModel = new JSONArray();
                    if(!models.isEmpty()){
                        singleModel.add(models.get(0));
                    }
                    newCar.put("models", singleModel);

                    try (FileWriter file = new FileWriter("toyota_single_model.json")) {
                        file.write(JSONObject.toJSONString(newCar));
                        file.flush();
                        System.out.println("Файл subaru_single_model.json создан.");
                    }
                }
                for(Object modelObj : modelsArray){
                    JSONObject model = (JSONObject) modelObj;
                    System.out.println("modelId: " + model.get("modelId"));
                    System.out.println("name: " + model.get("name"));
                    System.out.println("engine: " + model.get("engine"));
                    System.out.println("year: " + model.get("year"));
                    System.out.println("color: " + model.get("color"));
                    if(model.containsKey("features")){
                        List<String> features = (List<String>) model.get("features");
                        for(String feature : features){
                            System.out.println("\tfeature: " + feature);
                        }
                    }
                    System.out.println("price: " + model.get("price"));
                    System.out.println();
                }
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
