package com.ng.mats.psa.mt.fortis.xmlprocessor;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType(name = "destinationMDN")
public class DestinationMDN {
	private String value = null;

	@Override
	public String toString() {
		return "DestinationMDN{" + "value=" + value + '}';
	}

	@XmlValue
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
