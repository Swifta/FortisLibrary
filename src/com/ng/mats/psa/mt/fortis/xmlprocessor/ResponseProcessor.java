package com.ng.mats.psa.mt.fortis.xmlprocessor;

import java.io.ByteArrayInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class ResponseProcessor {
	private static final Logger logger = Logger
			.getLogger(ResponseProcessor.class.getName());

	public static Response unMarshal(String xml) {
		Response response = null;
		logger.info("--------------------------Unmarshal the xml which is :"
				+ xml);

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Response.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			if (xml != null) {
				response = (Response) jaxbUnmarshaller
						.unmarshal(new ByteArrayInputStream(xml.getBytes()));
			} else {
				logger.info("XML is NULL>>>>>>>>>>>>>>");
			}

			System.out.println(response);
			logger.info("New patch breakpoint>>>>>>>>");
		} catch (JAXBException e) {
			logger.info("ERROR OCCURED WHILE UNMARSHALLING XML" + e.toString());
			logger.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		}
		logger.info("New patch breakpoint>>>>>> BEFORE RETURNING RESPONSE");
		return response;

	}

}
