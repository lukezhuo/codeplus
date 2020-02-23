package nearestneighbor;

import java.util.ArrayList;
import java.util.Arrays;

public class NearestNeighbor {
	public Route findShortestRoute(ArrayList<City> cities) {
		System.out.println("-------------------------------------------------------------");
		System.out.println("Initial Route	==> " + Arrays.toString(cities.toArray()));
		System.out.println("w/ total distance: " + new Route(cities).calculateTotalDistance());
		System.out.println("-------------------------------------------------------------");

		// City city = cities.get((int) (cities.size() * Math.random()));
		City city = cities.get((7));
		ArrayList<City> shortestRouteCities = new ArrayList<City>(cities.size());
		updateRoutes(shortestRouteCities, cities, city);
		while (cities.size() >= 1) {
			city = getNextCity(cities, city);
			updateRoutes(shortestRouteCities, cities, city);
		}
		return new Route(shortestRouteCities);
	}

	private void updateRoutes(ArrayList<City> shortestRouteCities, ArrayList<City> cities, City city) {
		shortestRouteCities.add(city);
		cities.remove(city);
		System.out.println("Cities In Shourtest Route ==> " + Arrays.toString(shortestRouteCities.toArray()));
		System.out.println("Remaining Cities          ==> " + Arrays.toString(cities.toArray()) + "\n");
	}

	/*
	 * Find nearest city to current city
	 */
	private City getNextCity(ArrayList<City> cities, City city) {
		int index = 0;
		int minValue = Integer.MAX_VALUE;
		for (int i = 0; i < cities.size(); i++) {
			int distance = cities.get(i).measureDistance(city);
			if (distance < minValue) {
				minValue = distance;
				index = i;
			}
		}
		return cities.get(index);
	}
}
