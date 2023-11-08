package latestPBL1;

public class Contract {

	private int contractID;
	private int officeID;
	private int employeeID;
	private int customerID;
	private int carID;
	private Date startDate;
	private Date endDate;
	private int daysRented;
	private boolean giveBonus;

	public Contract(int contractID, int officeID, int employeeID,
			int customerID, int carID, Date startDate, Date endDate) {
		this.contractID = contractID;
		this.officeID = officeID;
		this.employeeID = employeeID;
		this.customerID = customerID;
		this.carID = carID;
		this.startDate = startDate;
		this.endDate = endDate;
		daysRented = endDate.getDay() - startDate.getDay() + 1;
		giveBonus = false;
	}

	public int getContractID() {
		return contractID;
	}

	public void setContractID(int contractID) {
		this.contractID = contractID;
	}

	public int getOfficeID() {
		return officeID;
	}

	public void setOfficeID(int officeID) {
		this.officeID = officeID;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getCarID() {
		return carID;
	}

	public void setCarID(int carID) {
		this.carID = carID;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getDaysRented() {
		return daysRented;
	}

	public void setDaysRented(int daysRented) {
		this.daysRented = daysRented;
	}

	public boolean isGiveBonus() {
		return giveBonus;
	}

	public void updateGiveBonus() {
		this.giveBonus = true;
	}

}
