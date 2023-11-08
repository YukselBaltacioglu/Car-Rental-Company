package latestPBL1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class CarRentalCompany {

	private String name;
	private Office[] offices;
	private int countOffice;
	private Customer[] customers;
	private int countCustomer;
	private Contract[] contracts;
	private int countContract;
	private CarRequest[] carRequests;
	private int countCarRequest;
	private int countStatistic;
	private Date simulationDate;
	private int employeeCount;
	private int carCount;
	private int dayCounter;

	// employee ve car için ayrı bir değişken => ID ler için

	public CarRentalCompany(String name) {
		this.name = name;
		this.offices = new Office[50];
		countOffice = 0;
		this.customers = new Customer[50];
		countCustomer = 0;
		this.contracts = new Contract[50];
		countContract = 0;
		this.carRequests = new CarRequest[50];
		countCarRequest = 0;

		this.simulationDate = new Date(1, 1, 2021);
		employeeCount = 0;
		carCount = 0;
		dayCounter = 1;
		BufferedReader inputStream = null;
		Scanner scanner = new Scanner(System.in);
		while (true) {
			try {
				System.out.print(">>>");
				String command = scanner.nextLine();
				String lines;
				String[] editedCommand = command.split(";");
				if (editedCommand[0].equals("load")) {
					inputStream = new BufferedReader(new FileReader(
							editedCommand[1]));
					while ((lines = inputStream.readLine()) != null) {
						String[] splittedLines = lines.split(";");
						System.out.println(">" + lines);

						switch (splittedLines[0]) {
						case "addOffice":
							addOffice(splittedLines[1], splittedLines[2],
									splittedLines[3]);
							break;
						case "addEmployee":
							findOffice(Integer.parseInt(splittedLines[5]) - 1)
									.addEmployee(splittedLines[1],
											splittedLines[2], splittedLines[3],
											splittedLines[4],
											Integer.parseInt(splittedLines[5]),
											employeeCount);
							employeeCount++;
							break;
						case "listOffice":
							listOffice();
							break;
						case "listEmployee":
							findOffice(Integer.parseInt(splittedLines[1]) - 1)
									.listEmployee();
							break;
						case "addCar":
							getOffices()[Integer.parseInt(splittedLines[5]) - 1]
									.AddCar(splittedLines[1], splittedLines[2],
											splittedLines[3],
											Integer.parseInt(splittedLines[4]),
											Integer.parseInt(splittedLines[5]),
											carCount);
							carCount++;
							break;
						case "listCar":
							getOffices()[Integer.parseInt(splittedLines[1]) - 1]
									.listCar(carCount);
							break;
						case "addCustomer":
							Customer cu = new Customer(splittedLines[1],
									splittedLines[2]);
							addCustomer(cu);
							break;
						case "listCustomer":
							listCustomer();
							break;
						case "deleteOffice":
							deleteOffice(Integer.parseInt(splittedLines[1]));
							countOffice--;
							break;
						case "deleteEmployee":
							deleteEmployee(Integer.parseInt(splittedLines[1]),
									Integer.parseInt(splittedLines[2]));
							employeeCount--;
							break;

						// office_id;customer_id;brand;model;class;start_date;end_date
						case "addCarRequest":
							String[] date = null;
							// You need to escape the dot if you want to split
							// on a literal dot
							// Otherwise you are splitting on the regex ., which
							// means "any character".
							// Note the double backslash needed to create a
							// single backslash in the regex.
							date = splittedLines[6].split("\\."); // Örn:
																	// 1.1.2021
							Date startDate = new Date(
									Integer.parseInt(date[0]),
									Integer.parseInt(date[1]),
									Integer.parseInt(date[2]));
							date = splittedLines[7].split("\\.");
							Date endDate = new Date(Integer.parseInt(date[0]),
									Integer.parseInt(date[1]),
									Integer.parseInt(date[2]));

							addCarRequest(Integer.parseInt(splittedLines[1]),
									Integer.parseInt(splittedLines[2]),
									splittedLines[3], splittedLines[4],
									splittedLines[5], startDate, endDate,
									simulationDate);
							break;

						case "listCarRequest":
							listCarRequest();
							break;

						case "addCarRequestRandom":
							addRandomRequest(
									Integer.parseInt(splittedLines[1]),
									splittedLines[2]);
							break;

						case "addCarRequestNRandom":
							Random rnd2 = new Random();
							int numberOfRequest = rnd2.nextInt(Integer
									.parseInt(splittedLines[2])) + 1;
							int randomOffice = rnd2.nextInt(countOffice) + 1;
							for (int i = 0; i < numberOfRequest; i++) {
								addRandomRequest(randomOffice, "*");
							}
							break;

						default:
							break;
						}
					}
				} else if (!editedCommand[0].equals("load")) {
					switch (editedCommand[0]) {
					case "nextday":
						// nextDay2();
						nextDay();
						break;
					case "listContract":
						listContract();
						break;
					case "listcar":
						getOffices()[Integer.parseInt(command) - 1]
								.listCar(carCount);
						break;
					case "addOffice":
						addOffice(editedCommand[1], editedCommand[2],
								editedCommand[3]);
						break;
					case "addEmployee":
						findOffice(Integer.parseInt(editedCommand[5]) - 1)
								.addEmployee(editedCommand[1],
										editedCommand[2], editedCommand[3],
										editedCommand[4],
										Integer.parseInt(editedCommand[5]),
										employeeCount);
						employeeCount++;
						break;
					case "listOffice":
						listOffice();
						break;
					case "listEmployee":
						findOffice(Integer.parseInt(editedCommand[1]) - 1)
								.listEmployee();
						break;
					case "addCar":
						getOffices()[Integer.parseInt(editedCommand[5]) - 1]
								.AddCar(editedCommand[1], editedCommand[2],
										editedCommand[3],
										Integer.parseInt(editedCommand[4]),
										Integer.parseInt(editedCommand[5]),
										carCount);
						carCount++;
						break;
					case "listCar":
						getOffices()[Integer.parseInt(editedCommand[1]) - 1]
								.listCar(carCount);
						break;
					case "addCustomer":
						Customer cu = new Customer(editedCommand[1],
								editedCommand[2]);
						addCustomer(cu);
						break;
					case "listCustomer":
						listCustomer();
						break;
					case "deleteOffice":
						deleteOffice(Integer.parseInt(editedCommand[1]));
						countOffice--;
						break;
					case "deleteEmployee":
						deleteEmployee(Integer.parseInt(editedCommand[1]),
								Integer.parseInt(editedCommand[2]));
						employeeCount--;
						break;

					// office_id;customer_id;brand;model;class;start_date;end_date
					case "addCarRequest":
						String[] date = null;
						// You need to escape the dot if you want to split
						// on a literal dot
						// Otherwise you are splitting on the regex ., which
						// means "any character".
						// Note the double backslash needed to create a
						// single backslash in the regex.
						date = editedCommand[6].split("\\."); // Örn:
																// 1.1.2021
						Date startDate = new Date(Integer.parseInt(date[0]),
								Integer.parseInt(date[1]),
								Integer.parseInt(date[2]));
						date = editedCommand[7].split("\\.");
						Date endDate = new Date(Integer.parseInt(date[0]),
								Integer.parseInt(date[1]),
								Integer.parseInt(date[2]));

						addCarRequest(Integer.parseInt(editedCommand[1]),
								Integer.parseInt(editedCommand[2]),
								editedCommand[3], editedCommand[4],
								editedCommand[5], startDate, endDate,
								simulationDate);

						break;

					case "listCarRequest":
						listCarRequest();
						break;

					case "addCarRequestRandom":
						addRandomRequest(Integer.parseInt(editedCommand[1]),
								editedCommand[2]);
						break;

					case "addCarRequestNRandom":
						Random rnd2 = new Random();
						int numberOfRequest = rnd2.nextInt(Integer
								.parseInt(editedCommand[2])) + 1;
						int randomOffice = rnd2.nextInt(countOffice) + 1;
						for (int i = 0; i < numberOfRequest; i++) {
							addRandomRequest(randomOffice, "*");
						}
						break;
					default:
						break;
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Something went wrong!!! ");
			}
		}
	}

	public void nextDay() {

		int[] bonuses = new int[5];
		int bonusesIndex = 0;

		int dailyIncome = 0;
		int dailyOutcome = 0;
		int dailyProfit = 0;

		int carIncome = 0;
		int carOutcome = 0;
		int carProfit = 0;

		int employeeIncome = 0;
		int employeeOutcome = 0;
		int employeeProfit = 0;

		System.out.println("---Office Profits---");

		for (int i = 0; i < offices.length; i++) { // ofis seç
			bonusesIndex = 0;
			if (offices[i] != null) {
				dailyOutcome += 100; // kirayı ekle
				dailyOutcome += offices[i].getEmployeeIndex() * 30;
				for (int j = 0; j < countContract; j++) { // kontratları gez
					Random rnd = new Random();
					int km = (rnd.nextInt(3) + 1) * 100; // 100, 200, 300
					if (offices[i].getId() == contracts[j].getOfficeID()) {
						// kontratların tarihi bitinceye kadar
						if (simulationDate.getDay() <= contracts[j]
								.getEndDate().getDay()) {
							employeeIncome += offices[i].getCarWithID(
									contracts[j].getCarID()).getRentPrice();
							employeeOutcome += offices[i].getCarWithID(
									contracts[j].getCarID())
									.getMaintenanceExpense();
							carIncome = offices[i].getCarWithID(
									contracts[j].getCarID()).getRentPrice();
							carOutcome += offices[i].getCarWithID(
									contracts[j].getCarID())
									.getMaintenanceExpense();
							dailyIncome += offices[i].getCarWithID(
									contracts[j].getCarID()).getRentPrice();
							dailyOutcome += offices[i].getCarWithID(
									contracts[j].getCarID())
									.getMaintenanceExpense();
							if (km == 100) { // km başına giderler
								employeeOutcome += offices[i].getCarWithID(
										contracts[j].getCarID()).getKmExpense();
								carOutcome += offices[i].getCarWithID(
										contracts[j].getCarID()).getKmExpense();
								dailyOutcome += offices[i].getCarWithID(
										contracts[j].getCarID()).getKmExpense();
								findOffice(contracts[j].getOfficeID() - 1)
										.getCarWithID(contracts[j].getCarID())
										.setKilometers(100);
							} else if (km == 200) {
								employeeOutcome += offices[i].getCarWithID(
										contracts[j].getCarID()).getKmExpense();
								carOutcome += offices[i].getCarWithID(
										contracts[j].getCarID()).getKmExpense() * 2;
								dailyOutcome += offices[i].getCarWithID(
										contracts[j].getCarID()).getKmExpense() * 2;
								findOffice(contracts[j].getOfficeID() - 1)
										.getCarWithID(contracts[j].getCarID())
										.setKilometers(200);
							} else if (km == 300) {
								employeeOutcome += offices[i].getCarWithID(
										contracts[j].getCarID()).getKmExpense();
								carOutcome += offices[i].getCarWithID(
										contracts[j].getCarID()).getKmExpense() * 3;
								dailyOutcome += offices[i].getCarWithID(
										contracts[j].getCarID()).getKmExpense() * 3;
								findOffice(contracts[j].getOfficeID() - 1)
										.getCarWithID(contracts[j].getCarID())
										.setKilometers(300);
							}
						}
						if (!contracts[j].isGiveBonus()) { // 1 kez bonus verdik
															// çalışanlara
							dailyOutcome += offices[i].getCarWithID(
									contracts[j].getCarID()).getBonusValue();
							contracts[j].updateGiveBonus();
							bonuses[bonusesIndex] = offices[i].getCarWithID(
									contracts[j].getCarID()).getBonusValue();
							bonusesIndex++;
						}
						employeeProfit = employeeIncome - employeeOutcome;

						carProfit = carIncome - carOutcome;

						for (int k = 0; k < offices[i].getEmployeeIndex(); k++) {
							if (offices[i].getEmployee(k) != null) {
								offices[i].getEmployeeWithID(
										contracts[j].getEmployeeID())
										.addDailyIncomes(carIncome);
								offices[i].getEmployeeWithID(
										contracts[j].getEmployeeID())
										.addDailyIncomes(carOutcome);
								offices[i].getEmployeeWithID(
										contracts[j].getEmployeeID())
										.addDailyIncomes(carProfit);
							}
						}

						offices[i].getCarWithID(contracts[j].getCarID())
								.addDailyIncomes(carIncome);
						offices[i].getCarWithID(contracts[j].getCarID())
								.addDailyOutcomes(carOutcome);
						offices[i].getCarWithID(contracts[j].getCarID())
								.addDailyProfits(carProfit);
						employeeIncome = 0;
						employeeOutcome = 0;
						employeeProfit = 0;
						carIncome = 0;
						carOutcome = 0;
						carProfit = 0;

					}

				}

				dailyProfit = dailyIncome - dailyOutcome;

				offices[i].addDailyIncomes(dailyIncome);

				offices[i].addDailyOutcomes(dailyOutcome);

				offices[i].addDailyProfits(dailyProfit);

				System.out.printf("Office%d incomes: %d cp \n",
						offices[i].getId(),
						offices[i].getIncome(dayCounter - 1));
				for (int j = 0; j < offices[i].getCarIndex(); j++) {
					if (!offices[i].getCar(j).isAvailable()
							&& offices[i].getCar(j).getKilometers() != 0) { // araç
																			// kiralıksa,
																			// müsait
																			// değilse
						System.out.printf("   Car%d: %d \n",
								offices[i].getCar(j).getCarId(), offices[i]
										.getCar(j).getRentPrice()); // araç
						// gelirleri
					}
				}
				System.out.printf("Office%d expenses: %d \n", i + 1,
						offices[i].getOutcome(dayCounter - 1));
				System.out.printf("   Office rent: %d \n",
						offices[i].getOfficeRent());
				System.out.printf("   Employee salaries: %d \n",
						offices[i].getEmployeeIndex() * 30);

				int sumBonus = 0;
				System.out.print("   Employee performance bonuses: ");
				for (int j = 0; j < bonuses.length; j++) {
					if (bonuses[j] != 0) {
						sumBonus += bonuses[j];
						if (j % 2 == 1) {
							System.out.printf(" + %d", bonuses[j]);
						} else {
							System.out.printf("%d", bonuses[j]);
						}
					}

				}
				System.out.println(" = " + sumBonus);

				for (int j = 0; j < offices[i].getCarIndex(); j++) {
					if (!offices[i].getCar(j).isAvailable()
							&& offices[i].getCar(j).getKilometers() != 0) { // araç
																			// kiralıksa,
																			// müsait
																			// değilse
						int kmExpense = offices[i].getCar(j).getKmExpense()
								* (offices[i].getCar(j).getKilometers() / 100);
						System.out
								.printf("   Car%d maintenance: %d + %d = %d (%d km) \n",
										offices[i].getCar(j).getCarId(),
										offices[i].getCar(j)
												.getMaintenanceExpense(),
										kmExpense, offices[i].getCar(j)
												.getTotalExpense(kmExpense),
										offices[i].getCar(j).getKilometers());
					}

				}
				System.out.printf("Office%d profit: %d cp\n\n",
						offices[i].getId(), dailyProfit);

				Arrays.fill(bonuses, 0); // içindeki verileri sıfırla
				dailyIncome = 0;
				dailyOutcome = 0;
				dailyProfit = 0;

			}
		}

		System.out.println("--- Office Statistics of the Last 10 Days ----");

		for (int i = 0; i < offices.length; i++) {
			if (offices[i] != null) {
				int sumDay = 0; // toplam kiralama süreleri
				int sumContract = 0; // toplam kontrat sayıları

				for (int k = 0; k < countContract; k++) { // kontratlar
					if (simulationDate.getDay()
							- contracts[k].getStartDate().getDay() < 10) { // son
																			// 10
																			// günlük
																			// istatistik
						if (contracts[k].getOfficeID() == offices[i].getId()) {
							sumDay += contracts[k].getDaysRented();
							sumContract++;
						}
					}
				}
				offices[i].setAverageRentDay((double) sumDay / sumContract);
			}

		}
		Car mostRentedCar = new Car();
		String[] mostCar = new String[countOffice];
		String[] mostClass = new String[countOffice];
		for (int i = 0; i < countOffice; i++) {
			if (offices[i] != null) {
				System.out.printf("---Office%d---\n", offices[i].getId());

				// most rented car
				
				mostRentedCar = offices[i].getCar(0);
				for (int j = 0; j < offices[i].getCarIndex(); j++) { // seçili
																		// ofisteki
																		// arabaları
																		// geziyorum

					if (offices[i].getCar(j).getTimesRented() >= mostRentedCar
							.getTimesRented()) {
						mostRentedCar = offices[i].getCar(j);
					}
				}
				
				System.out.printf("   The most rented car: Car%d;%s;%s\n",
						mostRentedCar.getCarId(), mostRentedCar.getBrand(),
						mostRentedCar.getModel());
				mostCar[i] = mostRentedCar.getBrand();
				System.out.printf("   The most rented car class: %s\n",
						mostRentedCar.getCarClass());
				mostClass[i] = mostRentedCar.getCarClass();
				Car mostProfitCar = new Car();
				
				mostProfitCar = offices[i].getCar(0);
				for (int j = 0; j < offices[i].getCarIndex(); j++) { // seçili
																		// ofisteki
																		// arabaları
																		// geziyorum

					if (offices[i].getCar(j).getProfits() >= mostProfitCar
							.getProfits()) {
						mostProfitCar = offices[i].getCar(j);
					}
				}

				System.out.printf(
						"   The car with the highest profit: Car%d;%s;%s\n",
						mostProfitCar.getCarId(), mostProfitCar.getBrand(),
						mostProfitCar.getModel());

				System.out.printf(
						"   The car class with the highest profit: %s\n",
						mostProfitCar.getCarClass());

				System.out
						.printf("   The average number of days the cars are rented: %.1f days\n",
								offices[i].getAverageRentDay());

				Customer mostCustomer = new Customer();
				mostCustomer = customers[0];
				for (int j = 0; j < countContract; j++) {
					if (contracts[j].getOfficeID() == offices[i].getId()
							&& customers[contracts[j].getCustomerID() - 1]
									.getTimesRented() > mostCustomer
									.getTimesRented()) {
						mostCustomer = customers[contracts[j].getCustomerID() - 1];
					}

				}

				System.out.printf(
						"   The customer who rented most: Customer%d;%s;%s \n",
						mostCustomer.getID(), mostCustomer.getName(),
						mostCustomer.getSurname());

				Employee mostEmployee = new Employee();
				mostEmployee = offices[i].getEmployee(0);
				for (int j = 0; j < offices[i].getEmployeeIndex(); j++) { // seçili
																			// ofisteki
																			// çalışanları
																			// geziyorum

					if (offices[i].getEmployee(j).getTimesRented() >= mostEmployee
							.getTimesRented()) {
						mostEmployee = offices[i].getEmployee(j);
					}
				}

				System.out.printf(
						"   The employee who rented most: Employee%d;%s;%s \n",
						mostEmployee.getID(), mostEmployee.getName(),
						mostEmployee.getSurname());

				Employee profitEmployee = new Employee();
				profitEmployee = offices[i].getEmployee(0);
				int rentPay = 0;
				int maintanencePay = 0;
				int profit = 0;
				int averagePro = 0;
				Car temper = new Car();

				for (int j = 0; j < offices[i].getEmployeeIndex(); j++) {
					for (int k = 0; k < countContract; k++) {
						if(contracts[k] != null){
							if (offices[i].getEmployee(j).getID() == contracts[k]
									.getEmployeeID()) {
								if(offices[i].getCarWithID(contracts[k]
										.getCarID()) != null){
									averagePro += (offices[i].getCarWithID(contracts[k]
											.getCarID()).getRentPrice())
											- (offices[i].getCarWithID(contracts[k]
													.getCarID())
													.getMaintenanceExpense() + offices[i]
													.getCarWithID(contracts[k]
															.getCarID())
													.getKmExpense()
													* (offices[i]
															.getCarWithID(contracts[k]
																	.getCarID())
															.getKilometers() / 100));
									if ((offices[i].getCarWithID(contracts[k]
											.getCarID())
											.getRentPrice())
											- (offices[i].getCarWithID(contracts[k]
													.getCarID())
													.getMaintenanceExpense() + offices[i]
													.getCarWithID(contracts[k]
															.getCarID())
													.getKmExpense()
													* (offices[i]
															.getCarWithID(contracts[k]
																	.getCarID())
															.getKilometers() / 100)) >= profit) {
										rentPay = (offices[i].getCarWithID(contracts[k]
												.getCarID()).getRentPrice());
										maintanencePay = (offices[i].getCarWithID(contracts[k]
												.getCarID())
												.getMaintenanceExpense() + offices[i]
												.getCarWithID(contracts[k]
														.getCarID())
												.getKmExpense()
												* (offices[i].getCarWithID(contracts[k]
														.getCarID())
														.getKilometers() / 100));
										profit = rentPay - maintanencePay;
										profitEmployee = offices[i].getEmployee(j);
									}
								}
								

							}
						}
						
					}

				}
				System.out.println("   The most profitable employee: Employee"
						+ profitEmployee.getID() + ";"
						+ profitEmployee.getName() + ";"
						+ profitEmployee.getSurname() + " (" + rentPay + " - "
						+ maintanencePay + " = " + profit + " cp)");

				System.out
						.println("   Average income levels of the employees for the office: "
								+ averagePro / 2 + " cp");

				rentPay = 0;
				maintanencePay = 0;
				profit = 0;
				averagePro = 0;

			}
		}

		Date.nextDay(simulationDate); // 1 gün ileri aldık simülasyonu
		dayCounter++;
		// system recommendations are displayed for every three consecutive days
		if (dayCounter % 3 == 0) {
			int sums = 0;
			System.out.println("---- System Recommendations ----");
			for (int i = 0; i < offices.length; i++) {
				sums = 0;
				if (offices[i] != null) {
					for (int l = 0; l < offices[i].getDailyProfitsIndex(); l++) {
						sums += offices[i].getDailyProfits()[l];
					}
					System.out.println("Office" + (i+1) + ": Buy a new car ( " + mostCar[i] + " )");
					System.out.println("Office" + (i+1) + ": Buy a new car with " + mostClass[i] + " class");
					if(sums < 0){
						System.out.println("You can close the Office" + (i+1) + " or remove an employee from there.\nYou cannot get any profit from that office.");
					}
				}
			}

		}

		for (int i = 0; i < countContract; i++) { // günü gelen arabaları
													// available yap
			if (contracts[i].getEndDate().getDay() == simulationDate.getDay() - 1) {
				findOffice(contracts[i].getOfficeID() - 1).getCarWithID(
						contracts[i].getCarID()).setAvailable(true);
			}
			// çalışanları available yap
			findOffice(contracts[i].getOfficeID() - 1).getEmployeeWithID(
					contracts[i].getEmployeeID()).setAvailable(true);
		}

	}

	// office_id;customer_id;brand;model;class;start_date;end_date
	public void addCarRequest(int i1, int i2, String s3, String s4, String s5,
			Date startDate, Date endDate, Date simulationDay) {

		CarRequest cR = new CarRequest(countCarRequest + 1, i1, i2, s3, s4, s5,
				startDate, endDate);
		cR.setCarRequestID(countCarRequest + 1);
		carRequests[countCarRequest] = cR;
		countCarRequest++;

		boolean canDoRequest = true;
		boolean timeCheck = true;

		if (!Date.compareWithSimulation(startDate, simulationDay)) {
			System.out.println("   Error:Car request must be for today!");
			timeCheck = false;
		}
		if (endDate.getDay() - startDate.getDay() > 4) {
			System.out.println("   Error:Car requests must be for 1-4 days!");
			timeCheck = false;
		}

		if (!checkStartEndDates(startDate, endDate)) {
			System.out
					.println("   Error:Contract start date must be smaller than end date!");
			timeCheck = false;
		}

		Car tempCar = new Car();

		Random rnd = new Random();
		int random = 0;

		String valid = "";
		String[] validPieces = null;
		boolean carFound = false;

		if (s3.equals("*") || s4.equals("*") || s5.equals("*")) {

			// sadece class girilmiş araç
			if (s3.equals("*") && s4.equals("*")) { // !s5.equals("*")
				for (int i = 0; i < findOffice(i1 - 1).getCarIndex(); i++) {// ilgili
																			// ofisteki
																			// araç
																			// sayısı
																			// kadar
																			// dön
					if (findOffice(i1 - 1).getCar(i).getCarClass()
							.equalsIgnoreCase(s5)) { // istenen classtaki araç
														// bulunduysa
														// ve müsaitse ID'sini
														// bir
														// kenara yaz
						carFound = true;

						if (findOffice(i1 - 1).getCar(i).isAvailable()) {
							valid = valid
									+ findOffice(i1 - 1).getCar(i).getCarId()
									+ "-";
							canDoRequest = true;
						}

					}
				}
			}

			// sadece markası girilmiş araç
			if (s4.equals("*") && s5.equals("*")) {
				for (int i = 0; i < findOffice(i1 - 1).getCarIndex(); i++) {// ilgili
																			// ofisteki
																			// araç
																			// sayısı
																			// kadar
																			// dön
					if (findOffice(i1 - 1).getCar(i).getBrand()
							.equalsIgnoreCase(s3)) { // istenen markadai araç
														// bulunduysa
														// ve müsaitse ID'sini
														// bir
														// kenara yaz
						carFound = true;

						if (findOffice(i1 - 1).getCar(i).isAvailable()) {
							valid = valid
									+ findOffice(i1 - 1).getCar(i).getCarId()
									+ "-";
							canDoRequest = true;
						}
					}
				}

			}

		} else if (!s3.equals("*") && !s4.equals("*") && !s5.equals("*")) { // her
																			// şeyi
																			// girilmiş
																			// araç
																			// (marka,model,class)
			for (int i = 0; i < findOffice(i1 - 1).getCarIndex(); i++) {// ilgili
																		// ofisteki
																		// araç
																		// sayısı
																		// kadar
																		// dön

				if (findOffice(i1 - 1).getCar(i).getBrand()
						.equalsIgnoreCase(s3)
						&& findOffice(i1 - 1).getCar(i).getModel()
								.equalsIgnoreCase(s4)
						&& findOffice(i1 - 1).getCar(i).getCarClass()
								.equalsIgnoreCase(s5)
						&& findOffice(i1 - 1).getCar(i).isAvailable()) { // istenen
																			// markadaki,modeldeki,classtaki
																			// araç
																			// bulunduysa
																			// ve
																			// müsaitse

					carFound = true;

					if (findOffice(i1 - 1).getCar(i).isAvailable()) {
						valid = valid + findOffice(i1 - 1).getCar(i).getCarId()
								+ "-";
						canDoRequest = true;
					}

				}
			}
		}

		if (valid.length() != 0) { // uygun araç varsa içinden random seç

			validPieces = valid.split("-");

			random = rnd.nextInt(validPieces.length);

			tempCar = findOffice(i1 - 1).getCarWithID(
					Integer.parseInt(validPieces[random]));
			canDoRequest = true;

		} else {
			if (!carFound) { // araç bulunamadıysa
				System.out.println("   Error:No car!");
				canDoRequest = false;
			} else if (carFound && !tempCar.isAvailable()) { // araç bulundu ama
																// müsait değil
				System.out.println("   Error:Car not available!");
				canDoRequest = false;
			}

		}

		Employee tempEmp = new Employee();
		valid = "";
		validPieces = null;

		if (findOffice(i1 - 1) != null) {
			for (int i = 0; i < findOffice(i1 - 1).getEmployeeIndex(); i++) {// ilgili
																				// ofisteki
																				// çalışan
																				// sayısı
																				// kadar
																				// dön
				if (findOffice(i1 - 1).getEmployee(i).isAvailable()) { // çalışan
																		// müsaitse
																		// IDsini
																		// bir
																		// kenara
																		// yaz
					valid = valid + findOffice(i1 - 1).getEmployee(i).getID()
							+ "-";
					canDoRequest = true;
				}

			}

			if (valid.length() != 0) { // uygun çalışan var

				validPieces = valid.split("-");

				random = rnd.nextInt(validPieces.length);

				tempEmp = findOffice(i1 - 1).getEmployeeWithID(
						Integer.parseInt(validPieces[random]));
				findOffice(i1 - 1).getEmployeeWithID(
						Integer.parseInt(validPieces[random])).setAvailable(
						false);
				// çalışan artık müsait değil
				canDoRequest = true;

			} else { // uygun çalışan yokmuş
				System.out.println("   Error:No employee for the contract!");
				canDoRequest = false;
			}

		} else {
			System.out.println("   There is no office with entered ID!");
		}

		if (canDoRequest && timeCheck) { // if everything is fine, make contract
											// then

			makeContract(tempEmp, i1, getCustomerWithID(i2), tempCar,
					startDate, endDate);
			cR.updateAccepted();
			getCustomerWithID(i2).updateTimesRented(dayCounter - 1);
			tempEmp.updateTimesRented(dayCounter - 1);
			tempCar.updateTimesRented(dayCounter - 1);
			tempCar.setAvailable(false); // kontrat yapıldıktan sonra aracı ve
											// çalışanı false yaptım
			tempEmp.setAvailable(false);

		}

	}

	public void addRandomRequest(int i1, String s1) {
		Random rnd = new Random();

		boolean canDoRequest = true;

		int randomDay = rnd.nextInt(simulationDate.getDay() + 4) + 1;
		Date endDateRandom = new Date(randomDay, simulationDate.getMonth(),
				simulationDate.getYear());
		int randomCustomer = rnd.nextInt(countCustomer + 1) + 1;

		Car tempCar = new Car();

		int random = 0;

		String valid = "";
		String[] validPieces = null;
		boolean carFound = false;
		String carBrand = "";
		String carModel = "";
		String carClass = "";
		CarRequest cR = new CarRequest(countCarRequest + 1, i1, randomCustomer,
				carBrand, carModel, carClass, simulationDate, endDateRandom);
		cR.setCarRequestID(countCarRequest + 1);
		carRequests[countCarRequest] = cR;
		countCarRequest++;

		// System.out.println(randomCar);
		if (i1 > 0 && s1.equals("*")) {
			int randomCar = rnd.nextInt(findOffice(i1 - 1).getCarIndex()) + 1;
			if (findOffice(i1 - 1).getCarWithID(randomCar).isAvailable() == false) {
				canDoRequest = false;
			} else {
				valid = valid
						+ findOffice(i1 - 1).getCarWithID(randomCar).getCarId()
						+ "-";
				canDoRequest = true;
				
//				tempCar = findOffice(i1 - 1).getCarWithID(randomCar);
//				findOffice(i1 - 1).getCarWithID(randomCar).setAvailable(false);
//				canDoRequest = true;
				carBrand = findOffice(i1 - 1).getCarWithID(randomCar).getBrand();
				carModel = findOffice(i1 - 1).getCarWithID(randomCar).getModel();
				carClass = findOffice(i1 - 1).getCarWithID(randomCar)
						.getCarClass();
				System.out.println(carBrand);
				System.out.println(carModel);
				System.out.println(carClass);
			}
		}
		
		if (i1 > 0 && s1.equals("*") == false) {
			for (int i = 0; i < findOffice(i1 - 1).getCarIndex(); i++) {// ilgili
																		// ofisteki
																		// araç
																		// sayısı
																		// kadar
																		// dön
				if (findOffice(i1 - 1).getCarWithID(i).getCarClass()
						.equalsIgnoreCase(s1)) { // istenen
													// classtaki
													// araç
					// bulunduysa
					// ve müsaitse ID'sini bir
					// kenara yaz
					carFound = true;

					if (findOffice(i1 - 1).getCarWithID(i).isAvailable()) {
						valid = valid + findOffice(i1 - 1).getCarWithID(i).getCarId()
								+ "-";
						canDoRequest = true;
						
					}
				}
			}

		}
		if (i1 == -1 && s1.equals("*")) {
			int randomOffice = rnd.nextInt(countOffice) + 1;
			int randomCar2 = rnd.nextInt(findOffice(randomOffice - 1)
					.getCarIndex()) + 1;
			if (findOffice(randomOffice - 1).getCarWithID(randomCar2)
					.isAvailable()) {
				carBrand = findOffice(randomOffice - 1).getCarWithID(randomCar2)
						.getBrand();
				carModel = findOffice(randomOffice - 1).getCarWithID(randomCar2)
						.getModel();
				carClass = findOffice(randomOffice - 1).getCarWithID(randomCar2)
						.getCarClass();
//				tempCar = findOffice(randomOffice - 1).getCarWithID(randomCar2);
//				canDoRequest = true;
				System.out.println(carBrand);
				System.out.println(carModel);
				System.out.println(carClass);
				valid = valid + findOffice(randomOffice - 1).getCarWithID(randomCar2).getCarId()
						+ "-";
				canDoRequest = true;
				carFound = true;
			}
		}
		
		if (valid.length() != 0) { // uygun araç varsa içinden random seç

			validPieces = valid.split("-");

			random = rnd.nextInt(validPieces.length);

			tempCar = findOffice(i1 - 1).getCarWithID(
					Integer.parseInt(validPieces[random]));
			canDoRequest = true;

		}
		

		if (!carFound) { // araç bulunamadıysa
			System.out.println("   Error:No car!");
			canDoRequest = false;
		} else if (carFound && !tempCar.isAvailable()) { // araç
															// bulundu
															// ama
															// müsait
															// değil
			System.out.println("   Error:Car not available!");
			canDoRequest = false;
		}

		
		
	}

	public void makeContract(Employee tempEmployee, int officeID,
			Customer tempCustomer, Car tempCar, Date startDate, Date endDate) {
		Contract cT = new Contract(countContract + 1, officeID,
				tempEmployee.getID(), tempCustomer.getID(), tempCar.getCarId(),
				startDate, endDate);
		System.out.printf(
				"   Contract:Employee%d;Customer%d;Car%d;%d.%d.%d;%d.%d.%d\n",
				cT.getEmployeeID(), cT.getCustomerID(), cT.getCarID(), cT
						.getStartDate().getDay(), cT.getStartDate().getMonth(),
				cT.getStartDate().getYear(), cT.getEndDate().getDay(), cT
						.getEndDate().getMonth(), cT.getEndDate().getYear());

		contracts[countContract] = cT;
		countContract++;
	}

	public void listCarRequest() {
		for (int i = 0; i < countCarRequest; i++) {
			System.out.printf("   %d.CarRequest;%d;%d;%s;%s;%s;",
					carRequests[i].getCarRequestID(),
					carRequests[i].getOfficeID(),
					carRequests[i].getCustomerID(), carRequests[i].getBrand(),
					carRequests[i].getModel(), carRequests[i].getCarClass());
			displayDate(carRequests[i].getStartDate(),
					carRequests[i].getEndDate());
			if (carRequests[i].isAccepted()) {
				System.out.println(" - accepted");
			} else {
				System.out.println(" - denied");
			}

		}

	}

	public void listContract() {
		for (int i = 0; i < countContract; i++) {
			System.out.printf("   %d.Contract:Employee%d;Customer%d;Car%d;",
					contracts[i].getContractID(), contracts[i].getEmployeeID(),
					contracts[i].getCustomerID(), contracts[i].getCarID());
			displayDate(contracts[i].getStartDate(), contracts[i].getEndDate());
			System.out.print("\n");
		}

	}

	public void displayDate(Date d1, Date d2) {
		System.out.printf("%d.%d.%d;%d.%d.%d", d1.getDay(), d1.getMonth(),
				d1.getYear(), d2.getDay(), d2.getMonth(), d2.getYear());
	}

	// function that checks whether contract start
	// date is smaller than contract end date
	public static boolean checkStartEndDates(Date startDate, Date endDate) {// Contract
																			// start
																			// date
																			// must
																			// be
																			// smaller
																			// than
																			// the
																			// contract
																			// end
																			// date
		if ((endDate.getYear() == startDate.getYear())
				&& (endDate.getMonth() == startDate.getMonth())
				&& endDate.getDay() < startDate.getDay()) {
			return false;
		} else if ((endDate.getYear() == startDate.getYear())
				&& (endDate.getMonth() < startDate.getMonth())) {
			return false;
		} else if (endDate.getYear() < startDate.getYear()) {
			return false;
		}

		return true;
	}

	public Customer getCustomerWithID(int a) {

		Customer tempCustomer = new Customer();
		for (int i = 0; i < countCustomer; i++) {
			if (customers[i].getID() == a) {
				tempCustomer = customers[i];
				break;
			}
		}
		return tempCustomer;
	}

	public Office[] getOffices() {
		return offices;
	}

	public void setOffices(Office[] offices) {
		this.offices = offices;
	}

	public Office findOffice(int a) {
		return offices[a];
	}

	public Customer[] getCustomers() {
		return customers;
	}

	public void setCustomers(Customer[] customers) {
		this.customers = customers;
	}

	public int getCountOffice() {
		return countOffice;
	}

	public void setCountOffice(int countOffice) {
		this.countOffice = countOffice;
	}

	public int getCountCustomers() {
		return countCustomer;
	}

	public void setCountCustomers(int countCustomers) {
		this.countCustomer = countCustomers;
	}

	public void addOffice(String s1, String s2, String s3) {
		Office o = new Office(s1, s2, s3);
		o.SetId(countOffice + 1);
		offices[countOffice] = o;
		countOffice++;
	}

	public void addCustomer(Customer c) {
		c.setID(countCustomer + 1);
		customers[countCustomer] = c;
		countCustomer++;
	}

	public void listOffice() {
		for (int i = 0; i < countOffice; i++) {
			if (offices[i] != null) {
				System.out.printf("   %d.Office;%s;%s;%s\n",
						offices[i].getId(), offices[i].getPhone_number(),
						offices[i].getAddress(), offices[i].getCity()); // office
																		// classının
																		// getter
																		// methodlarını
																		// kullan
			}
		}
	}

	public void listCustomer() {
		for (int i = 0; i < countCustomer; i++) {
			System.out.println("   " + (i + 1) + ".Customer;"
					+ customers[i].getName() + ";" + customers[i].getSurname());
		}
	}

	public void deleteOffice(int a) {
		for (int i = 0; i < countOffice; i++) {
			if (a == offices[i].getId()) {
				offices[i] = null;
				break;
			}
		}
	}

	public void deleteEmployee(int officeID, int employeeID) {
		for (int i = 0; i < countOffice; i++) {
			if (officeID - 1 == i) {
				for (int j = 0; j < offices[i].getEmployeeIndex(); j++) {
					if (employeeID - 1 == j) {
						offices[i].getEmployees()[j] = null;
					}
				}
			}

		}
	}
}