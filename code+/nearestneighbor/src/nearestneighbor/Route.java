package nearestneighbor;

import java.util.ArrayList;
import java.util.Arrays;

public class Route {
	private ArrayList<City> cities = new ArrayList<City>();

	public Route(ArrayList<City> cities) {
		this.cities.addAll(cities);
	}

	public ArrayList<City> getCities() {
		return cities;
	}

	public int calculateTotalDistance() {
		int citiesSize = this.getCities().size();
		int sum = 0;

		for (int i = 0; i < citiesSize - 1; i++) {
			City city = this.getCities().get(i);
			int distance = city.measureDistance(getCities().get(i + 1));
			sum += distance;
		}

		// At the end, the route will go from last city to first one.......
		sum += this.getCities().get(citiesSize - 1).measureDistance(this.getCities().get(0));

		return sum;
	}

	public String toString() {
		return Arrays.toString(cities.toArray());
	}

}
