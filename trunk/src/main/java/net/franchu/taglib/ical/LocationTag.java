package net.franchu.taglib.ical;

import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Location;

/**
 * @author mgilbir
 * 
 */
public class LocationTag extends EventBodyTag {

	private static final long serialVersionUID = -3847814877224448911L;

	@Override
	final void processBody(final String theBody) {
		final String body = theBody.trim();
		VEvent event = parent.getEvent();
		Location location = new Location(body);
		event.getProperties().add(location);

	}

}
