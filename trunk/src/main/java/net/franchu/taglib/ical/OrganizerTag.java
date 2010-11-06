package net.franchu.taglib.ical;

import java.net.URISyntaxException;

import javax.servlet.jsp.JspTagException;

import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.parameter.Cn;
import net.fortuna.ical4j.model.property.Organizer;

/**
 * @author filip.kis
 * 
 */
public class OrganizerTag extends EventBodyTag {

	private static final long serialVersionUID = 7308034078212280423L;
	private String name = null;

	public final void setName(final String theName) {
		this.name = theName;
	}

	@Override
	final void processBody(final String theBody) throws JspTagException {
		final String body = theBody.trim();
		VEvent event = parent.getEvent();
		// Organizer organizer = new Organizer(body);
		Organizer organizer;
		try {
			organizer = new Organizer(body);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			throw new JspTagException("OrganizerTag invalid string format: "
					+ e.getMessage());
		}
		event.getProperties().add(organizer);
		event.getProperties().getProperty(Property.ORGANIZER).getParameters()
				.add(new Cn(this.name));
	}

}