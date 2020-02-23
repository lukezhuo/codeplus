package nearestneighbor;

import java.util.ArrayList;
import java.util.Arrays;

public class Driver {

	private static final ArrayList<City> initialCities = new ArrayList<City>(
			Arrays.asList(new City("Boston", 42, -71), new City("Houston", 29, -95), new City("Austin", 30, -97),
					new City("SFO", 37, -122), new City("Denver", 39, -104), new City("LAX", 34, -118),
					new City("Chicago", 41, -87), new City("New York", 40, -74), new City("Dallas", 32, -96)));

	public static void main(String[] args) {
		ArrayList<City> cities = new ArrayList<City>();
		cities.addAll(initialCities);
		Route r = new NearestNeighbor().findShortestRoute(cities);
		printShortestRoute(r);
	}

	private static void printShortestRoute(Route shortestRoute) {
		System.out.println("-----------------------------------------------------------");
		System.out.println("Shortestroute found so far: " + shortestRoute);
		System.out.println("w/ toatal distance: " + shortestRoute.calculateTotalDistance());
		System.out.println("-----------------------------------------------------------");
	}

}
