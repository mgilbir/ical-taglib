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
import net.fortuna.ical4j.model.property.Location;

/**
 * @author mgilbir
 *
 */
public class LocationTag extends EventBodyTag {

	void ProcessBody(String body)
	{
		VEvent event = parent.event;
		Location location = new Location(body);
		event.getProperties().add(location);
		
	}


}
