/**
 * 
 */
package net.franchu.taglib.ical;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.*;
import java.net.URISyntaxException;

import net.fortuna.ical4j.*;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.parameter.Cn;
import net.fortuna.ical4j.model.property.Organizer;

/**
 * @author filip.kis
 *
 */
public class OrganizerTag extends EventBodyTag {

	private String name = null;
	
	public void setName(String name)
    {
        this.name=name;
    }
	
	void ProcessBody(String body) throws JspTagException
	{
		VEvent event = parent.event;
		//Organizer organizer = new Organizer(body);
		Organizer organizer;
		try {
			organizer = new Organizer(body);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			throw new JspTagException("OrganizerTag invalid string format: " + e.getMessage());
		}
		event.getProperties().add(organizer);
		event.getProperties().getProperty(Property.ORGANIZER).getParameters().add(new Cn(this.name));
	}
	


}