package alchemytec.expenses.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Expense {

	@JsonProperty
	private String _id;

	@JsonProperty
	private Double amount;

	@JsonProperty
	private String date;

	@JsonProperty
	private String reason;

	@JsonProperty
	private Double vat;

	public Expense() {
	};

	public Expense(String _id, Double amount, String date, String reason,
			Double vat) {
		this._id = _id;
		this.amount = amount;
		this.date = date;
		this.reason = reason;
	}

	public Expense(Double amount, String date, String reason, Double vat) {

		this.amount = amount;
		this.date = date;
		this.reason = reason;
	}

	public String getReason() {
		return reason;
	}

	public Double getVat() {
		return vat;
	}

	public String getDate() {
		return date;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String get_id() {
		return _id;
	}

	@Override
	public boolean equals(Object obj) {
		Expense other = (Expense) obj;
		return amount.equals(other.amount) && date.equals(other.date)
				&& reason.equals(other.reason);
	}

}
