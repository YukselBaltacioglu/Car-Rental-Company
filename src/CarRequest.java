package latestPBL1;

public class CarRequest {

	// office_id;customer_id;brand;model;class;start_date;end_date
	private int carRequestID;
	private int officeID;
	private int customerID;
	private String brand;
	private String model;
	private String carClass;
	private Date startDate;
	private Date endDate;

	private boolean accepted;

	public CarRequest(int carRequestID, int officeID, int customerID,
			String brand, String model, String carClass, Date startDate,
			Date endDate) {
		this.carRequestID = carRequestID;
		this.officeID = officeID;
		this.customerID = customerID;
		this.brand = brand;
		this.model = model;
		this.carClass = carClass;
		this.startDate = startDate;
		this.endDate = endDate;
		accepted = false;
	}

	public int getOfficeID() {
		return officeID;
	}

	public void setOfficeID(int officeID) {
		this.officeID = officeID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getCarRequestID() {
		return carRequestID;
	}

	public void setCarRequestID(int carRequestID) {
		this.carRequestID = carRequestID;
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

	public String getCarClass() {
		return carClass;
	}

	public void setCarClass(String carClass) {
		this.carClass = carClass;
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

	public boolean isAccepted() {
		return accepted;
	}

	public void updateAccepted() {
		this.accepted = true;
	}

}
