package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AspectExperiment extends AbstractAspectExperiment {

	public static void main(String[] args) {
//		AspectExperiment main = new AspectExperiment();
//		List<Integer> nums = main.foo(new StringHolder("12"));
//		System.out.println(Arrays.toString(nums.toArray()));
//		
//		List<Object> l = null;
		
		ConstructorAspectTest cat = new ConstructorAspectTest("asdf");
		System.out.println("--> cat: " + cat);
		System.out.println(Arrays.toString(cat.getDataCopy().toArray()));
		
		StringHolder sh = new StringHolder("qqriq");
		System.out.println(sh);
	}
	
	public List<Integer> foo(StringHolder num) {
		int n = 0;
		try {
			n = Integer.parseInt(num.getStr());
		} catch (NumberFormatException nfe) {
			n = 1;
		}
		List<Integer> result = new ArrayList<>(n);
		for (int i = 0; i < n; i++) {
			result.add(i * 63 % 29);
		}
		return result;
	}

	@Override
	public List<String> fooFighters(StringHolder num) {
		int n = 0;
		try {
			n = Integer.parseInt(num.getStr());
		} catch (NumberFormatException nfe) {
			n = 1;
		}
		List<String> result = new ArrayList<>(n);
		for (int i = 0; i < n; i++) {
			result.add(new Integer(i * 63 % 29).toString());
		}
		return result;
	}
	
	
}
