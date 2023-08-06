import java.util.ArrayList;

	public class PandemicCity {
		ArrayList<City> cities;
    
    public PandemicCity() {
	        cities = new ArrayList<>();
		        cities.add(new City("Atlanta", true, 0));
		        cities.add(new City("Chicago", false, 0));
		        cities.add(new City("Essen", false, 0));
		        cities.add(new City("London", false, 0));
		        cities.add(new City("Madrid", false, 0));
		        cities.add(new City("Milan", false, 0));
		        cities.add(new City("Montreal", false, 0));
		        cities.add(new City("New York", false, 0));
		        cities.add(new City("Paris", false, 0));
		        cities.add(new City("San Francisco", false, 0));
		        cities.add(new City("St. Petersburg", false, 0));
		        cities.add(new City("Washington", false, 0));
		
		        cities.add(new City("Bangkok", false, 0));
		        cities.add(new City("Beijing", false, 0));
		        cities.add(new City("Ho Chi Minh City", false, 0));
		        cities.add(new City("Hong Kong", false, 0));
		        cities.add(new City("Jakarta", false, 0));
		        cities.add(new City("Manila", false, 0));
		        cities.add(new City("Osaka", false, 0));
		        cities.add(new City("Seoul", false, 0));
		        cities.add(new City("Shanghai", false, 0));
		        cities.add(new City("Sydney", false, 0));
		        cities.add(new City("Taipei", false, 0));
		        cities.add(new City("Tokyo", false, 0));
		
		        cities.add(new City("Algiers", false, 0));
		        cities.add(new City("Baghdad", false, 0));
		        cities.add(new City("Cairo", false, 0));
		        cities.add(new City("Chennai", false, 0));
		        cities.add(new City("Delhi", false, 0));
		        cities.add(new City("Istanbul", false, 0));
		        cities.add(new City("Karachi", false, 0));
		        cities.add(new City("Kolkata", false, 0));
		        cities.add(new City("Moscow", false, 0));
		        cities.add(new City("Mumbai", false, 0));
		        cities.add(new City("Riyadh", false, 0));
		        cities.add(new City("Tehran", false, 0));
		
		        cities.add(new City("Johannesburg", false, 0));
		        cities.add(new City("Kinshasa", false, 0));
		        cities.add(new City("Khartoum", false, 0));
		        cities.add(new City("Lagos", false, 0));
		        cities.add(new City("Lima", false, 0));
		        cities.add(new City("Los Angeles", false, 0));
		        cities.add(new City("Mexico City", false, 0));
		        cities.add(new City("Miami", false, 0));
		        cities.add(new City("Santiago", false, 0));
		        cities.add(new City("Sao Paulo", false, 0));
		        cities.add(new City("Buenos Aires", false, 0));
		        cities.add(new City("Bogota", false, 0));
		    }
	
	public City findCity(String cityName) {
	    for (City city : cities) {
	        if (city.getName().equals(cityName)) {
	            return city;
	        }
	    }
	    System.out.print("Critical system error" + cityName);
	    return null; // city not found
	}
	}