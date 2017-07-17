package com.example;

public class StringHolder {

	private final String str;
	
	public StringHolder(String str) {
		this.str = str;
	}
	
	public String getStr() {
		return str;
	}
	
	public String toString() {
		return "StringHolder(" + str + ")";
	}
}
