package io.compiler.types;

public enum Types {
	NUMBER(1),
	FRAC(2),
	TEXT(3);
	
	private int value;
	
	private Types(int valueNumber) {
		this.value = valueNumber;
	}
	public Integer getValue() {
		return this.value;
	}
}
