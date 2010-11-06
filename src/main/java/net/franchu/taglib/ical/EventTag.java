package net.franchu.taglib.ical;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import net.fortuna.ical4j.model.component.VEvent;

/**
 * @author mgilbir
 * 
 */
public class EventTag extends BodyTagSupport {

	private static final long serialVersionUID = 8745557389271841073L;
	private CalendarTag parent;
	private VEvent event;

	@Override
	public final int doStartTag() throws JspTagException {
		parent = (CalendarTag) findAncestorWithClass(this, CalendarTag.class);
		if (parent == null) {
			throw new JspTagException("nesting error");
		}

		setEvent(new VEvent());

		//TODO: Check that this is the correct return value. Alternative is EVAL_BODY_BUFFERED
		return (EVAL_BODY_AGAIN);
	}

	@Override
	public final int doEndTag() throws JspTagException {
		parent.getCalendar().getComponents().add(getEvent());
		return SKIP_BODY;
	}

	public void setEvent(final VEvent theEvent) {
		this.event = theEvent;
	}

	public VEvent getEvent() {
		return event;
	}

}
