/**
 * 
 */
package net.franchu.taglib.ical;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.*;

import net.fortuna.ical4j.*;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.property.*;

/**
 * @author mgilbir
 * Initialises the iCal support for further processing.
 * Passing the parameter ProdId in the tag, allows the ProdId field of the iCal file to be customised.
 * Version of the iCal file defaults to 2.0
 * The calendar time standards defaults to gregorian calendar
 */
public class CalendarTag extends BodyTagSupport{
	
	Calendar calendar;
	String strProdId = "-//Miguel Gil Biraud//ical-taglib 0.1//based on iCal4j 1.0//EN//";
	String UID;
	
	public void setProdId(String strProdId)
    {
        this.strProdId = strProdId;
    }
	
	public int doStartTag() throws JspTagException 
	{
		calendar = new Calendar();
		calendar.getProperties().add(new ProdId(strProdId));
		calendar.getProperties().add(Version.VERSION_2_0);
		
		//TODO: Add the CalScale value as a parameter
		calendar.getProperties().add(CalScale.GREGORIAN);
		return EVAL_BODY_INCLUDE;
	}
	
	
	public int doEndTag() throws JspTagException 
	{
		CalendarOutputter outputter = new CalendarOutputter();
		//JspWriter out = pageContext.getOut();
		
		//outputter.setValidating(false);
			
		try
		{
			StringWriter outTemp = new StringWriter();
			outputter.output(calendar, outTemp);
			JspWriter out = pageContext.getOut();
			out.print(outTemp.toString());
		}
		catch (Exception e)
		{
			throw new JspTagException("Exception while writting the content: "+e.toString());
		}

		return SKIP_BODY;
	}
	
}
