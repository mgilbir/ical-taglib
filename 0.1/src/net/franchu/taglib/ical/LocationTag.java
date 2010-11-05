/**
 * 
 */
package net.franchu.taglib.ical;

import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Location;

/**
 * @author mgilbir
 * 
 */
public class LocationTag extends EventBodyTag {

	void ProcessBody(String body) {
		body = body.trim();
		VEvent event = parent.event;
		Location location = new Location(body);
		event.getProperties().add(location);

	}

}
