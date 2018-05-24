package com.example;

public class ChildStringHolder extends StringHolder {

	private final String str2;
	
	public ChildStringHolder(String str1, String str2) {
		super(str1);
		this.str2 = str2;
	}

	public String getStr2() {
		return str2;
	}

	@Override
	public String toString() {
		return "ChildStringHolder [str2=" + str2 + ", getStr()=" + getStr() + "]";
	}

}
