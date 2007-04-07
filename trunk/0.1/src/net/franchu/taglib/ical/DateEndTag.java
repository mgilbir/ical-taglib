/**
 * 
 */
package net.franchu.taglib.ical;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import net.fortuna.ical4j.*;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.parameter.Value;
import net.fortuna.ical4j.model.property.DtEnd;
import net.fortuna.ical4j.model.property.DtStart;

/**
 * @author mgilbir
 *
 */
public class DateEndTag extends EventBodyTag {
	
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
		DtEnd end = new DtEnd();
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
			end.setDate(new Date(date));
		} 
		catch (Exception e) 
		{
			throw new JspTagException("DateEndTag invalid string format: " + e.getMessage());
		}
     		
		VEvent event = parent.event;
		event.getProperties().add(end);
		event.getProperties().getProperty(Property.DTEND).getParameters().add(Value.DATE);
	}

}
