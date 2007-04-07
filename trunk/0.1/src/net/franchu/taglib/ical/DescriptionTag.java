/**
 * 
 */
package net.franchu.taglib.ical;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.*;
import net.fortuna.ical4j.*;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Description;

/**
 * @author mgilbir
 *
 */
public class DescriptionTag extends EventBodyTag {

	void ProcessBody(String body)
	{
		VEvent event = parent.event;
		Description description = new Description(body);
		event.getProperties().add(description);
	}
	


}
