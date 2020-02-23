package nearestneighbor;

public class City {
	private int x;
	private int y;
	private String name;

	public City(String name, int x, int y) {
		this.name = name;
		this.x = x;
		this.y = y;
	}

	public int measureDistance(City city) {
		return Math.abs(this.x - city.x) + Math.abs(this.y - city.y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return this.name;
	}
}
