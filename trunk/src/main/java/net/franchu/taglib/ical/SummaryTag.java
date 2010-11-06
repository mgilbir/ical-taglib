package net.franchu.taglib.ical;

import javax.servlet.jsp.JspTagException;

import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Summary;

/**
 * @author mgilbir
 * 
 */
public class SummaryTag extends EventBodyTag {

	private static final long serialVersionUID = 4143751291474313216L;

	@Override
	final void processBody(final String theBody) throws JspTagException {
		try {
			final String body = theBody.trim();
			VEvent event = parent.getEvent();

			Summary summary = new Summary(body);
			event.getProperties().add(summary);
		} catch (Exception e) {
			throw new JspTagException(e);
		}
	}

}
