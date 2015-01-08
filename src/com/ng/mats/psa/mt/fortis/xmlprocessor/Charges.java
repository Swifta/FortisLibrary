package com.ng.mats.psa.mt.fortis.xmlprocessor;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType(name = "charges")
public class Charges {
	private Double value = null;

	@Override
	public String toString() {
		return "Charges{" + "value=" + value + '}';
	}

	@XmlValue
	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
}
