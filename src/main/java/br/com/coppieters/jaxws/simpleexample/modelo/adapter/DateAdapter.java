package br.com.coppieters.jaxws.simpleexample.modelo.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter< String,Date>{
	private final static String DATE_FORMAT = "dd/MM/yyyy";
	
	@Override
	public Date unmarshal(String v) throws Exception {
		return new SimpleDateFormat(DATE_FORMAT).parse(v);
	}

	@Override
	public String marshal(Date v) throws Exception {
		return new SimpleDateFormat(DATE_FORMAT).format(v);
	}

}
