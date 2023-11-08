package latestPBL1;



public class Office {

	private int officeID;
	private String phone_number;
	private String address;
	private String city;
	private double income;
	private double outcome;
	private double profit;
	private static int officeRent = 100;

	private int[] dailyIncomes;
	private int dailyIncomesIndex;

	private int[] dailyOutcomes;
	private int dailyOutcomesIndex;

	private int[] dailyProfits;
	private int dailyProfitsIndex;

	private int[] bonuses;
	private int bonusesIndex;

	private Car[] cars;
	private int carIndex;// aslýnda ofisteki araç sayýsý carIndex

	private Employee[] employees;
	private int employeeIndex;// aslýnda ofisteki çalýþan sayýsý employeeIndex

	private Car[] mostRentedCars;
	private int mostRentedCarsIndex;

	private Car[] highestProfitCars;
	private int highestProfitCarsIndex;

	private double averageRentDay;

	private Customer[] mostCustomers;
	private int mostCustomersIndex;

	private Employee[] mostEmployees;
	private int mostEmployeesIndex;

	public Office(String input_phone_number, String input_address, String input_city) { // phone, address, city
		this.phone_number = input_phone_number;
		this.address = input_address;
		this.city = input_city;
		employees = new Employee[20];
		carIndex = 0;
		cars = new Car[20];
		employeeIndex = 0;
		income = 0;
		outcome = 0;
		profit = 0;

		dailyIncomes = new int[10];
		dailyIncomesIndex = 0;
		dailyOutcomes = new int[10];
		dailyOutcomesIndex = 0;
		dailyProfits = new int[10];
		dailyProfitsIndex = 0;
		bonuses = new int[10];
		bonusesIndex = 0;

		mostRentedCars = new Car[10];
		mostRentedCarsIndex = 0;
		highestProfitCars = new Car[10];
		highestProfitCarsIndex = 0;
		averageRentDay = 0;
		mostCustomers = new Customer[10];
		mostCustomersIndex = 0;
		mostEmployees = new Employee[10];
		mostEmployeesIndex = 0;
	}

	public int[] getDailyIncomes() {
		return dailyIncomes;
	}

	public void setDailyIncomes(int[] dailyIncomes) {
		this.dailyIncomes = dailyIncomes;
	}

	public int getDailyIncomesIndex() {
		return dailyIncomesIndex;
	}

	public void setDailyIncomesIndex(int dailyIncomesIndex) {
		this.dailyIncomesIndex = dailyIncomesIndex;
	}

	public int[] getDailyOutcomes() {
		return dailyOutcomes;
	}

	public void setDailyOutcomes(int[] dailyOutcomes) {
		this.dailyOutcomes = dailyOutcomes;
	}

	public int getDailyOutcomesIndex() {
		return dailyOutcomesIndex;
	}

	public void setDailyOutcomesIndex(int dailyOutcomesIndex) {
		this.dailyOutcomesIndex = dailyOutcomesIndex;
	}

	public int[] getDailyProfits() {
		return dailyProfits;
	}

	public void setDailyProfits(int[] dailyProfits) {
		this.dailyProfits = dailyProfits;
	}

	public int getDailyProfitsIndex() {
		return dailyProfitsIndex;
	}

	public void setDailyProfitsIndex(int dailyProfitsIndex) {
		this.dailyProfitsIndex = dailyProfitsIndex;
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

	public void addBonuses(int bonus) {
		if (bonusesIndex == 10) {
			bonusesIndex = bonusesIndex % 10;
		}

		bonuses[bonusesIndex] = bonus;
		bonusesIndex++;
	}

	public void addMostRentedCar(Car c) {
		if (c.getTimesRented() > mostRentedCars[0].getTimesRented()) { // ilk sýradaki araçtan büyükse
																		// direkt 0. indekse ata
			mostRentedCars[0] = c;
			mostRentedCarsIndex++;
		} else if (c.getTimesRented() == mostRentedCars[0].getTimesRented()) { // eþitse bir sonraki indekse koy
			mostRentedCars[mostRentedCarsIndex] = c;
			mostRentedCarsIndex++;
		}
	}

	public void addMostCustomer(Customer cU) {
		if (cU.getTimesRented() > mostCustomers[0].getTimesRented()) { // ilk sýradaki araçtan büyükse
																		// direkt 0. indekse ata
			mostCustomers[0] = cU;
		} else if (cU.getTimesRented() == mostCustomers[0].getTimesRented()) { // eþitse bir sonraki indekse koy
			mostCustomers[mostCustomersIndex] = cU;
			mostCustomersIndex++;
		}
	}

	public void addMostEmployee(Employee e) {
		if (e.getTimesRented() > mostEmployees[0].getTimesRented()) { // ilk sýradaki araçtan büyükse
																		// direkt 0. indekse ata
			mostEmployees[0] = e;
		} else if (e.getTimesRented() == mostEmployees[0].getTimesRented()) { // eþitse bir sonraki indekse koy
			mostEmployees[mostEmployeesIndex] = e;
			mostEmployeesIndex++;
		}
	}

	public int getOfficeRent() {
		return officeRent;
	}

	// Getters
	public int getId() {
		return officeID;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public int getIncome(int a) {
		if (a == 10) {
			a = a % 10;
		}
		return dailyIncomes[a];
	}

	public int getOutcome(int a) {
		if (a == 10) {
			a = a % 10;
		}
		return dailyOutcomes[a];
	}

	// Setters
	public void SetId(int ID) {
		this.officeID = ID;
	}

	public void SetPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public void SetAddress(String address) {
		this.address = address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void updateIncome(double income) {
		this.income += income;
	}

	public void updateOutcome(double outcome) {
		this.outcome += outcome;
	}

	public double getProfit(int a) {
		if (a == 10) {
			a = a % 10;
		}
		return dailyIncomes[a] - dailyOutcomes[a];
	}

	public double getAverageRentDay() {
		return averageRentDay;
	}

	public void setAverageRentDay(double averageRentDay) {
		this.averageRentDay = averageRentDay;
	}

	public void addEmployee(String s1, String s2, String s3, String s4, int i5, int employeeCount2) { // name,surname,gender,birthdate,ID
		Employee e = new Employee(s1, s2, s3, s4, i5, true);
		e.setID(employeeCount2 + 1);
		employees[employeeIndex] = e;
		employeeIndex++;
	}

	public void listEmployee() {
		for (int i = 0; i < employeeIndex; i++) {
			if (employees[i] != null) {
				System.out.printf("   %d.Employee;%s;%s;%s;%s;%d \n", employees[i].getID(), employees[i].getName(),
						employees[i].getSurname(), employees[i].getGender(), employees[i].getBirthdate(),
						employees[i].getIdOfOffice());
			}
		}
	}

	public void deleteEmployee(int a) {
		for (int i = 0; i < employeeIndex; i++) {
			if (a == employees[i].getID()) {
				employees[i] = null;
				break;
			}
		}
	}

	public void AddCar(String s1, String s2, String s3, int i4, int i5, int carCount2) {
		Car c = new Car(s1, s2, s3, i4, i5);
		c.setCarId(carCount2 + 1);
		cars[carIndex] = c;
		carIndex++;
	}

	public void listCar(int carCount2) {
		for (int i = 0; i < carCount2; i++) {// araç müsaitse available diyor deðilse not available
			if (cars[i] != null) {
				System.out.printf("   %d.Car;%s;%s;%s;%d;%d - ", cars[i].getCarId(), cars[i].getBrand(),
						cars[i].getModel(), cars[i].getCarClass(), cars[i].getKilometers(), cars[i].getIdOfOffice());
				System.out.println(cars[i].isAvailable() ? "available" : "not available");
			}
		}
	}

	public Car[] getCars() {
		return cars;
	}

	public Car getCar(int a) {
		return cars[a];
	}

	public Car getCarWithID(int a) {

		Car tempCar = new Car();
		for (int i = 0; i < carIndex; i++) {
			if (cars[i].getCarId() == a) {
				tempCar = cars[i];
				break;
			}
		}
		return tempCar;

	}

	public void setCars(Car[] cars) {
		this.cars = cars;
	}

	public int getCarIndex() {
		return carIndex;
	}

	public void setCarIndex(int carIndex) {
		this.carIndex = carIndex;
	}

	public Employee getEmployee(int a) {
		return employees[a];
	}

	public Employee getEmployeeWithID(int a) {

		Employee tempEmployee = new Employee();
		for (int i = 0; i < employeeIndex; i++) {
			if (employees[i].getID() == a) {
				tempEmployee = employees[i];
				break;
			}
		}
		return tempEmployee;

	}

	public Employee[] getEmployees() {
		return employees;
	}

	public void setEmployees(Employee[] employees) {
		this.employees = employees;
	}

	public int getEmployeeIndex() {
		return employeeIndex;
	}

	public void setEmployeeIndex(int employeeIndex) {
		this.employeeIndex = employeeIndex;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public void setOutcome(double outcome) {
		this.outcome = outcome;
	}

}