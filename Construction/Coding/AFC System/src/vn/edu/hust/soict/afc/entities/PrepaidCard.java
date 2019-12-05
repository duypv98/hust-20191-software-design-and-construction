package vn.edu.hust.soict.afc.entities;

public class PrepaidCard {

	private String id;
	private String cardCode;
	private double balance;
	private boolean checkedIn;

	public PrepaidCard() {
	}

	public PrepaidCard(String id, String cardCode, double balance, boolean checkedIn) {
		this.id = id;
		this.cardCode = cardCode;
		this.balance = balance;
		this.checkedIn = checkedIn;
	}

	public boolean isComparedWith(PrepaidCard card) {
		return this.getId() == card.getId() &&
			   this.getCardCode() == card.getCardCode() &&
			   this.getBalance() == card.getBalance() &&
			   this.isCheckedIn() == card.isCheckedIn();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public boolean isCheckedIn() {
		return checkedIn;
	}

	public void setCheckedIn(boolean checkedIn) {
		this.checkedIn = checkedIn;
	}
}
