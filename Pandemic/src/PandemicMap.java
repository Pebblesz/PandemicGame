import java.util.*;

public class PandemicMap {
    private Map<String, Set<String>> connectivityMap;

    public PandemicMap() {
        connectivityMap = new HashMap<>();
        // Add all cities and their connections to the map
        addCity("Atlanta", "Chicago", "Washington", "Miami");
        addCity("Chicago", "San Francisco", "Los Angeles", "Mexico City", "Atlanta", "Montreal");
        addCity("San Francisco", "Tokyo", "Manila", "Los Angeles", "Chicago");
        addCity("Montreal", "Chicago", "Washington", "New York");
        addCity("Washington", "Atlanta", "New York", "Montreal", "Miami");
        addCity("New York", "Montreal", "Washington", "London", "Madrid");
        addCity("London", "New York", "Madrid", "Essen", "Paris");
        addCity("Madrid", "New York", "Paris", "Algiers", "Sao Paulo", "London");
        addCity("Paris", "London", "Essen", "Milan", "Madrid", "Algiers");
        addCity("Essen", "London", "Paris", "Milan", "St. Petersburg");
        addCity("Milan", "Paris", "Essen", "Istanbul");
        addCity("St. Petersburg", "Essen", "Istanbul", "Moscow");
        addCity("Los Angeles", "Sydney", "San Francisco", "Chicago", "Mexico City");
        addCity("Mexico City", "Los Angeles", "Chicago", "Miami", "Bogota", "Lima");
        addCity("Miami", "Atlanta", "Washington", "Mexico City", "Bogota");
        addCity("Bogota", "Mexico City", "Miami", "Lima", "Sao Paulo", "Buenos Aires");
        addCity("Lima", "Mexico City", "Bogota", "Santiago");
        addCity("Santiago", "Lima");
        addCity("Buenos Aires", "Bogota", "Sao Paulo");
        addCity("Sao Paulo", "Bogota", "Buenos Aires", "Madrid", "Lagos");
        addCity("Algiers", "Madrid", "Paris", "Istanbul", "Cairo");
        addCity("Istanbul", "St. Petersburg", "Milan", "Essen", "Algiers", "Cairo", "Baghdad", "Moscow");
        addCity("Cairo", "Algiers", "Istanbul", "Riyadh", "Baghdad", "Khartoum");
        addCity("Moscow", "St. Petersburg", "Istanbul", "Tehran");
        addCity("Tehran", "Moscow", "Baghdad", "Karachi", "Delhi");
        addCity("Riyadh", "Cairo", "Baghdad", "Karachi");
        addCity("Karachi", "Tehran", "Baghdad", "Riyadh", "Mumbai", "Delhi");
        addCity("Mumbai", "Karachi", "Delhi", "Chennai");
        addCity("Delhi", "Tehran", "Karachi", "Mumbai", "Chennai", "Kolkata");
        addCity("Kolkata", "Delhi", "Chennai", "Bangkok", "Hong Kong");
        addCity("Bangkok", "Kolkata", "Hong Kong", "Ho Chi Minh City", "Jakarta", "Chennai");
        addCity("Hong Kong", "Kolkata", "Bangkok", "Ho Chi Minh City", "Shanghai", "Taipei", "Manila");
        addCity("Ho Chi Minh City", "Bangkok", "Hong Kong", "Jakarta", "Manila");
        addCity("Jakarta", "Chennai", "Bangkok", "Ho Chi Minh City", "Sydney");
        addCity("Manila", "Hong Kong", "Taipei", "San Francisco", "Ho Chi Minh City", "Sydney");
        addCity("Sydney", "Jakarta", "Manila", "Los Angeles");
        addCity("Chennai", "Mumbai", "Delhi", "Kolkata", "Bangkok", "Jakarta");
        addCity("Baghdad", "Istanbul", "Tehran", "Karachi", "Riyadh", "Cairo");
        addCity("Khartoum", "Cairo", "Lagos", "Kinshasa", "Johannesburg");
        addCity("Lagos", "Sao Paulo", "Khartoum", "Kinshasa");
        addCity("Kinshasa", "Lagos", "Khartoum", "Johannesburg");
        addCity("Johannesburg", "Kinshasa", "Khartoum");
        addCity("Seoul", "Beijing", "Shanghai", "Tokyo");
        addCity("Tokyo", "Osaka", "Seoul", "Shanghai", "San Francisco");
        addCity("Osaka", "Taipei", "Tokyo");
        addCity("Beijing", "Seoul", "Shanghai");
        addCity("Shanghai", "Beijing", "Seoul", "Tokyo", "Taipei");
        addCity("Taipei", "Shanghai", "Osaka", "Hong Kong", "Manila");
    }

    private void addCity(String city, String... connections) {
        Set<String> cityConnections = new HashSet<>(Arrays.asList(connections));
        connectivityMap.put(city, cityConnections);
    }

    public Set<String> getCityConnections(String city) {
        return connectivityMap.get(city);
    }
}