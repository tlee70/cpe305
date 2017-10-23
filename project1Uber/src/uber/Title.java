package uber;

/**
 * Car Title contains legal information about a car
 * Not currently used for anything, just more identifying information about Driver
 * @author tlee70
 *
 */
public class Title {
	private String make;
	private String model;
	private int year;
	private String plate;
	
	public Title(String make, String model, int year, String plate) {
		this.make = make;
		this.model = model;
		this.year = year;
		this.plate = plate;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public int getYear() {
		return year;
	}

	public String getPlate() {
		return plate;
	}
	
	
}
