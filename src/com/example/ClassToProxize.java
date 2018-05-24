package com.example;

public class ClassToProxize {

	public String foo() {
		return "foo";
	}
	
	public /*final*/ String bar() {
		return "bar";
	}
}
