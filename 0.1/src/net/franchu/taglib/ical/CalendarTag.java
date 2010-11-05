/**
 * 
 */
package net.franchu.taglib.ical;

import java.io.StringWriter;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.model.property.XProperty;

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
	String title = null;
	String description = null;
	
	public void setProdId(String strProdId)
    {
        this.strProdId = strProdId;
    }
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public int doStartTag() throws JspTagException 
	{
		calendar = new Calendar();
		calendar.getProperties().add(new ProdId(strProdId));
		calendar.getProperties().add(Version.VERSION_2_0);
		if(title!=null)
			calendar.getProperties().add(new XProperty("X-WR-CALNAME",title));
		if(description!=null)
			calendar.getProperties().add(new XProperty("X-WR-CALDESC",description));
		
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
