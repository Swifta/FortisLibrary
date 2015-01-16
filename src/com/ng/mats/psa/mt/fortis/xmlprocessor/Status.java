package com.ng.mats.psa.mt.fortis.xmlprocessor;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType(name = "status")
public class Status {
	private String value = null;

	@Override
	public String toString() {
		return "Status{" + "value=" + value + '}';
	}

	@XmlValue
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}