package com.ng.mats.psa.mt.fortis.xmlprocessor;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType(name = "refID")
public class RefID {
	private String value = null;

	@Override
	public String toString() {
		return "RefID{" + "value=" + value + '}';
	}

	@XmlValue
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}