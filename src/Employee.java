package latestPBL1;

public class Employee {

	private String name;
	private String surname;
	private String gender;
	private int idOfOffice;
	private int ID;
	private String birthdate;
	private boolean isAvailable;
	private double salary;
	private double performance_bonuse;

	private int[] timesRented;
	private int timesRentedIndex;

	private int[] dailyIncomes;
	private int dailyIncomesIndex;
	private int[] dailyOutcomes;
	private int dailyOutcomesIndex;
	private int[] dailyProfits;
	private int dailyProfitsIndex;

	// Constructor
	public Employee(String input_name, String input_surname,
			String input_gender, String input_birthdate, int input_ID,
			boolean isAvailable) {
		this.name = input_name;
		this.surname = input_surname;
		this.gender = input_gender;
		this.birthdate = input_birthdate;
		this.idOfOffice = input_ID; // OFÝS ID'SINI MI TUTUCAZ ONU SOR
		this.isAvailable = isAvailable;
		timesRented = new int[10];
		timesRentedIndex = 0;
		dailyIncomes = new int[10];
		dailyIncomesIndex = 0;
		dailyOutcomes = new int[10];
		dailyOutcomesIndex = 0;
		dailyProfits = new int[10];
		dailyProfitsIndex = 0;

	}

	public Employee() { // constructor to create an empty employee object
		this.name = "";
		this.surname = "";
		this.gender = "";
		this.birthdate = "";
		this.idOfOffice = 0; // OFÝS ID'SINI MI TUTUCAZ ONU SOR
		this.isAvailable = false;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getIdOfOffice() {
		return idOfOffice;
	}

	public void setIdOfOffice(int idOfOffice) {
		this.idOfOffice = idOfOffice;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public double getPerformance_bonuse() {
		return performance_bonuse;
	}

	public void setPerformance_bonuse(double performance_bonuse) {
		this.performance_bonuse = performance_bonuse;
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

}
