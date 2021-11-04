package com.ERS.model;
import java.sql.Timestamp;
import java.util.List;

public class Reimbursement {
       private int reimbursementId;
       private double amount;
       private Timestamp submitted;
       private Timestamp resolved;
	   private String description;
	   private int author;
	   private int resolver;
	   private int statusId;
	   private int typeId;
	   
    public Reimbursement() {
	   }
	public Reimbursement(double amount, String description, int statusId, int  typeId,int author) {
		this.amount = amount;
		this.description = description;
		this.statusId = statusId;
		this.typeId = typeId;
		this.author= author;
	}
	
	public Reimbursement(int reimbursementId, double amount,  Timestamp submitted, Timestamp resolved, String description,
			 int author, int resolver, int statusId, int typeId) {
		super();
		this.reimbursementId = reimbursementId;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.author = author;
		this.resolver = resolver;
		this.statusId = statusId;
		this.typeId = typeId;
	}

	public int getReimbursementId() {
		return reimbursementId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}

	public Timestamp getResolved() {
		return resolved;
	}

	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public int getResolver() {
		return resolver;
	}

	public void setResolver(int resolver) {
		this.resolver = resolver;
	}

	public int getStatusId() {
		return statusId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbursementId=" + reimbursementId + ", amount=" + amount + ", submitted=" + submitted
				+ ", resolved=" + resolved + ", description=" + description + ", receipt=" + ", author="
				+ author + ", resolver=" + resolver + ", statusId=" + statusId + ", typeId=" + typeId + "]";
	}   
}
	 