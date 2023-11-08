package latestPBL1;

public class Car {

	// fields,properties
	private int carId;
	private int idOfOffice;
	private String brand;
	private String model;
	private String carClass;
	private int kilometers;
	private boolean isAvailable; // araç boþta mý deðil mi diye bakmak için

	private int[] timesRented;
	private int timesRentedIndex;

	private int maintenanceExpense;
	private int kmExpense;
	private int totalExpense;
	private int rentPrice;
	private int bonusValue;

	private int[] dailyIncomes;
	private int dailyIncomesIndex;

	private int[] dailyOutcomes;
	private int dailyOutcomesIndex;

	private int[] dailyProfits;
	private int dailyProfitsIndex;

	// constructors
	public Car(String inputBrand, String inputModel, String inputcarClass,
			int inputKilometers, int inputID) {
		this.brand = inputBrand;
		this.model = inputModel;
		this.carClass = inputcarClass;
		this.kilometers = inputKilometers;
		this.idOfOffice = inputID;
		this.isAvailable = true;
		timesRented = new int[10];
		timesRentedIndex = 0;
		kilometers = 0;

		dailyIncomes = new int[10];
		dailyIncomesIndex = 0;
		dailyOutcomes = new int[10];
		dailyOutcomesIndex = 0;
		dailyProfits = new int[10];
		dailyProfitsIndex = 0;

		if (inputcarClass.equals("economy")) {
			rentPrice = 100;
			maintenanceExpense = 20;
			kmExpense = 5;
			bonusValue = 5;
		} else if (inputcarClass.equals("sports")) {
			rentPrice = 200;
			maintenanceExpense = 70;
			kmExpense = 10;
			bonusValue = 10;
		} else if (inputcarClass.equals("luxury")) {
			rentPrice = 300;
			maintenanceExpense = 120;
			kmExpense = 15;
			bonusValue = 15;
		}

	}

	public Car() { // constructor to create an empty car object
		this.brand = "";
		this.model = "";
		this.carClass = "";
		this.kilometers = 0;
		this.idOfOffice = 0;
		this.isAvailable = false;
	}

	public int getTotalExpense(int kmExpense) {
		return maintenanceExpense + kmExpense;
	}

	public void addDailyIncomes(int income) {
		if (dailyIncomesIndex == 10) {
			dailyIncomesIndex = dailyIncomesIndex % 10;
		}

		dailyIncomes[dailyIncomesIndex] = income;
		dailyIncomesIndex++;
	}

	public void addDailyOutcomes(int outcome) {
		if (dailyOutcomesIndex == 10) {
			dailyOutcomesIndex = dailyOutcomesIndex % 10;
		}

		dailyOutcomes[dailyOutcomesIndex] = outcome;
		dailyOutcomesIndex++;
	}

	public void addDailyProfits(int profit) {
		if (dailyProfitsIndex == 10) {
			dailyProfitsIndex = dailyProfitsIndex % 10;
		}

		dailyProfits[dailyProfitsIndex] = profit;
		dailyProfitsIndex++;
	}

	public int getProfits() { // profitlerin toplamý
		int sum = 0;
		for (int i = 0; i < dailyProfits.length; i++) {
			if (dailyProfits[i] != 0) {
				sum += dailyProfits[i];
			}
		}

		return sum;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getIdOfOffice() {
		return idOfOffice;
	}

	public void setIdOfOffice(int idOfOffice) {
		this.idOfOffice = idOfOffice;
	}

	public String getCarClass() {
		return carClass;
	}

	public void setCarClass(String carClass) {
		this.carClass = carClass;
	}

	public int getKilometers() {
		return kilometers;
	}

	public void setKilometers(int kilometers) {
		this.kilometers = kilometers;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public int getRentPrice() {
		return rentPrice;
	}

	public int getTimesRented() { // 1'lerin toplamý bizim kiralanma sayýmýz
									// aslýnda
		int sum = 0;
		for (int i = 0; i < timesRented.length; i++) {
			if (timesRented[i] != 0) {
				sum += timesRented[i];
			}
		}

		return sum;
	}

	public void updateTimesRented(int dayCounter) { // kiralanan günler için 1
													// yaz ilgili indekse
		if (dayCounter == 10) {
			dayCounter = dayCounter % 10;
		}
		timesRented[dayCounter] = 1;
	}

	public int getMaintenanceExpense() {
		return maintenanceExpense;
	}

	public void setMaintenanceExpense(int maintenanceExpense) {
		this.maintenanceExpense = maintenanceExpense;
	}

	public int getKmExpense() {
		return kmExpense;
	}

	public void setKmExpense(int kmExpense) {
		this.kmExpense = kmExpense;
	}

	public int getBonusValue() {
		return bonusValue;
	}

	public void setBonusValue(int bonusValue) {
		this.bonusValue = bonusValue;
	}

}