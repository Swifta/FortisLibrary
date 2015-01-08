package com.ng.mats.psa.mt.fortis.xmlprocessor;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType(name = "amount")
public class Amount {
	private Double value = null;

	@Override
	public String toString() {
		return "Amount{" + "value=" + value + '}';
	}

	@XmlValue
	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
}