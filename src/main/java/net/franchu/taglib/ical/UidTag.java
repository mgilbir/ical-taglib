package net.franchu.taglib.ical;

import javax.servlet.jsp.JspTagException;

import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Uid;

/**
 * @author mgilbir
 * 
 */
public class UidTag extends EventBodyTag {

	private static final long serialVersionUID = 5895757437858636964L;

	@Override
	final void processBody(final String theBody) throws JspTagException {
		try {
			final String body = theBody.trim();
			VEvent event = parent.getEvent();

			Uid uid = new Uid(body);
			event.getProperties().add(uid);
		} catch (Exception e) {
			throw new JspTagException(e);
		}
	}
}
