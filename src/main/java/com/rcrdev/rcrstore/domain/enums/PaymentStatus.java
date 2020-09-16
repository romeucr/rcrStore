package com.rcrdev.rcrstore.domain.enums;

public enum PaymentStatus {

	PENDING(1, "Pending"),
	PAID(2, "Paid"),
	CANCELED(3, "Canceled");
	
	private int code;
	private String description;
	
	//enum constructors are ALWAYS PRIVATE
	private PaymentStatus(int code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	
	//static because that needs to be executed without instantiating an object
	public static PaymentStatus toEnum(Integer code) {
		
		if (code == null) {
			return null;
		}
	
		for (PaymentStatus x : PaymentStatus.values()) { 
			if (code.equals(x.getCode())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid id" + code);
	}
	
}
