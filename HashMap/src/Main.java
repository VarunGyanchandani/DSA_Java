import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        // Create a new HashMap
        HashMap<String, Integer> ages = new HashMap<>();

        // Add key-value pairs to the map
        ages.put("Alice", 25);
        ages.put("Bob", 30);
        ages.put("Carol", 35);

        // Get a value from the map
        int bobAge = ages.get("Bob");
        System.out.println("Bob's age: " + bobAge);

        // Check if a key exists
        if (ages.containsKey("David")) {
            System.out.println("David's age is known");
        } else {
            System.out.println("David's age is unknown");
        }

        // Update a value
        ages.put("Alice", 26);

        // Remove a key-value pair
        ages.remove("Carol");

        // Print all entries
        System.out.println("\nAll entries:");
        for (String name : ages.keySet()) {
            System.out.println(name + ": " + ages.get(name));
        }
    }
}