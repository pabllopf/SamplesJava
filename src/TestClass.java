import java.util.*;

public class TestClass {


    public static void main(String[] args) {

        // Inventory
        Map<String, int[]> inventory = new LinkedHashMap<>();
        inventory.put("Toronto", new int[]{5, 0, 0});
        inventory.put("Vancouver", new int[]{10, 2, 6});
        inventory.put("Montreal", new int[]{3, 5, 5});
        inventory.put("Calgary", new int[]{1, 18, 2});
        inventory.put("Halifax", new int[]{28, 2, 12});


        // Print the values:
        for(var entry : inventory.entrySet()){
            int[] counts = entry.getValue();
            System.out.printf("%-10s %5d %5d %5d%n", entry.getKey(), counts[0], counts[1], counts[2]);
        }

        String input = "J:3 H:2 s:4";
        // output: Van, Mon, Hali


        // Find the requirements
        Map<Character, Integer> requirements = new HashMap<>();
        for(String part : input.split(" ")){
            if(part.contains(":")){
               String[] pairs = part.split(":");
               if(pairs.length == 2){
                   char key = pairs[0].trim().charAt(0);
                   int value = Integer.parseInt(pairs[1].trim());
                   requirements.put(key, value);
               }
            }
        }

        // Search the cities
        List<String> matchingCities = new ArrayList<>();
        for(Map.Entry<String, int[]> entry: inventory.entrySet()){
            int[] counts = entry.getValue();
            boolean found = true;
            if(requirements.getOrDefault('J', 0) > counts[0] ||
                    requirements.getOrDefault('H', 0) > counts[1] ||
                    requirements.getOrDefault('S', 0) > counts[2]
            ){
                found = false;
            }
            if(found){
                matchingCities.add(entry.getKey().substring(0, 3));
            }
        }

        System.out.println("Output: " + String.join(", ", matchingCities));

    }




}
