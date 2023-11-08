package latestPBL1;



public class Date {
	private int day;
	private int month;
	private int year;

	public Date(int inputDay, int inputMonth, int inputYear) {
		this.year = inputYear;
		this.month = inputMonth;
		this.day = inputDay;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}
	
	public static void nextDay(Date simulationDate) {
		simulationDate.day += 1; // simülasyon gününü 1 arttýrýr.
	}
	

	public boolean isValid() {
		// even months have at most 30 days odd months have at most 31 days
		if ((this.getMonth() % 2 == 0 && this.getDay() <= 30) || (this.getMonth() % 2 == 1 && this.getDay() <= 31)) {
			// if, input is negative (dates can't be negative)
			// Also, there are 12 months in a year,at most 31 days in a month.
			if (this.getDay() <= 0 || this.getYear() <= 0 || this.getMonth() <= 0 || this.getMonth() > 12
					|| this.getDay() > 31) {
				return false;
			}
			return true;
		}
		return false;
	}

	public static boolean compareWithSimulation(Date startDate, Date simulationDay) {

		if ((simulationDay.getYear() == startDate.getYear()) && (simulationDay.getMonth() == startDate.getMonth())
				&& startDate.getDay() != simulationDay.getDay()) {
			return false;
		} else if ((simulationDay.getYear() == startDate.getYear())
				&& (simulationDay.getMonth() < startDate.getMonth())) {
			return false;
		} else if (simulationDay.getYear() < startDate.getYear()) {
			return false;
		}

		return true;

	}

}
