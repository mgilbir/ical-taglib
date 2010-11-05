/**
 * 
 */
package net.franchu.taglib.ical;

import javax.servlet.jsp.JspTagException;

import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Summary;

/**
 * @author mgilbir
 * 
 */
public class SummaryTag extends EventBodyTag {

	void ProcessBody(String body) throws JspTagException {
		try {
			body = body.trim();
			VEvent event = parent.event;

			Summary summary = new Summary(body);
			event.getProperties().add(summary);
		} catch (Exception e) {
			throw new JspTagException(e);
		}
	}

}
