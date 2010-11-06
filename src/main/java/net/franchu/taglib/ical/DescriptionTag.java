package net.franchu.taglib.ical;

import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Description;

/**
 * @author mgilbir
 * 
 */
public class DescriptionTag extends EventBodyTag {
	
	private static final long serialVersionUID = -8078915868462317711L;

	@Override
	final void processBody(final String body) {
		VEvent event = parent.getEvent();
		Description description = new Description(body);
		event.getProperties().add(description);
	}

}
