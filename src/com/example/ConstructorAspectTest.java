package com.example;

import java.util.ArrayList;
import java.util.List;

public class ConstructorAspectTest {

	private List<String> data = new ArrayList<>();
	
	public ConstructorAspectTest(String foo) {
		addData("Black Sabbath");
		addData("Led Zeppelin");
		addData("Alice in Chains");
	}
	/*
	public ConstructorAspectTest(String str) {
		this();
		System.out.println("--> pina bausch");
	}
	*/
	public void addData(String data) {
		this.data.add(data);
	}
	
	public List<String> getDataCopy() {
		return new ArrayList<>(this.data);
	}
}
