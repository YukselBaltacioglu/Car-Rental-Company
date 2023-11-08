package latestPBL1;



public class Customer {

	private String name;
	private String surname;
	private int ID;
	private int[] timesRented;
	private int timesRentedIndex;

	Customer(String name, String surname) {
		this.name = name;
		this.surname = surname;
		timesRented = new int[10];
		timesRentedIndex = 0;
	}

	Customer() {
		this.name = "";
		this.surname = "";
		this.ID = 0;
	}

	public int getID() {
		return ID;
	}

	public void setID(int customerId) {
		this.ID = customerId;
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

	public int getTimesRented() { // 1'lerin toplamý bizim kiralanma sayýmýz aslýnda
		int sum = 0;
		for (int i = 0; i < timesRented.length; i++) {
			if (timesRented[i] != 0) {
				sum += timesRented[i];
			}
		}

		return sum;
	}

	public void updateTimesRented(int dayCounter) { // kiralanan günler için 1 yaz ilgili indekse
		if (dayCounter == 10) {
			dayCounter = dayCounter % 10;
		}
		timesRented[dayCounter] = 1;
	}

}
