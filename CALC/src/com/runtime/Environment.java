package com.runtime;

import java.util.HashMap;
import java.util.Map;

public class Environment {
	private Map<String, Object> env;
	
	public Environment() {
		env = new HashMap<>();
	}
	
	public void set(String name, Object value) {
		env.put(name, value);
	}
	
	public Object get(String name) {
		if(env.containsKey(name)) {
			return env.get(name);
		}else {
			throw new RuntimeException("Variable not defined: "+ name);
		}
	}
}