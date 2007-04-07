package net.franchu.taglib.ical;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.*;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import net.fortuna.ical4j.*;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.parameter.Value;
import net.fortuna.ical4j.model.property.DtStart;

public class DateStartTag extends EventBodyTag {
	
	private String format = "yyyy-MM-dd'T'HH:mm:ss";
	private String timezone = null;
	
	public void setFormat(String format)
    {
        this.format=format;
    }
	
	public void setTimezone(String timezone)
    {
        this.timezone=timezone;
    }


	void ProcessBody(String body) throws JspTagException
	{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		DtStart start = new DtStart();
		Calendar calendar = Calendar.getInstance();
		try 
		{
			java.util.Date date = sdf.parse(body);
			if (timezone != null)
			{
				calendar.setTime(date);
				calendar.setTimeZone(java.util.TimeZone.getTimeZone(timezone));
				date = calendar.getTime();
			}
			start.setDate(new Date(date));
		} 
		catch (Exception e) 
		{
			throw new JspTagException("DateStartTag invalid string format: " + e.getMessage());
		}
     		
		VEvent event = parent.event;
		event.getProperties().add(start);
		event.getProperties().getProperty(Property.DTSTART).getParameters().add(Value.DATE);
	}
}
