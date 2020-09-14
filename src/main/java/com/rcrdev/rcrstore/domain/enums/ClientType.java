package com.rcrdev.rcrstore.domain.enums;

public enum ClientType {

	PERSON(1, "Person"),
	COMPANY(2, "Company");
	
	private int code;
	private String description;
	
	//enum constructors are ALWAYS PRIVATE
	private ClientType(int code, String description) {
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
	public static ClientType toEnum(Integer code) {
		
		if (code == null) {
			return null;
		}
	
		for (ClientType x : ClientType.values()) { 
			if (code.equals(x.getCode())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid id" + code);
	}
}
