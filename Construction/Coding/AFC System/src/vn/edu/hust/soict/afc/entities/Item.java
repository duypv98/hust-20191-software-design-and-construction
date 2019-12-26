package vn.edu.hust.soict.afc.entities;

public abstract class Item {

	protected String id;
	protected boolean checkedIn;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isCheckedIn() {
		return checkedIn;
	}

	public void setCheckedIn(boolean checkedIn) {
		this.checkedIn = checkedIn;
	}

}
