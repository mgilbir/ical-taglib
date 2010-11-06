package net.franchu.taglib.ical;

import java.io.StringWriter;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.model.property.XProperty;

/**
 * Initialises the iCal support for further processing. Passing the parameter
 * ProdId in the tag, allows the ProdId field of the iCal file to be customised.
 * Version of the iCal file defaults to 2.0 The calendar time standards defaults
 * to gregorian calendar
 * 
 * @author mgilbir
 */
public class CalendarTag extends BodyTagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3915650637889397903L;

	private Calendar calendar;
	private String prodId = "-//Miguel Gil Biraud//ical-taglib 0.1//based on iCal4j 1.0//EN//";
	private String title = null;
	private String description = null;

	public final void setProdId(final String theProdId) {
		this.prodId = theProdId;
	}

	public final void setTitle(final String theTitle) {
		this.title = theTitle;
	}

	public final void setDescription(final String theDescription) {
		this.description = theDescription;
	}

	@Override
	public final int doStartTag() throws JspTagException {
		setCalendar(new Calendar());
		getCalendar().getProperties().add(new ProdId(prodId));
		getCalendar().getProperties().add(Version.VERSION_2_0);
		if (title != null)
			getCalendar().getProperties().add(
					new XProperty("X-WR-CALNAME", title));
		if (description != null)
			getCalendar().getProperties().add(
					new XProperty("X-WR-CALDESC", description));

		// TODO: Add the CalScale value as a parameter
		getCalendar().getProperties().add(CalScale.GREGORIAN);
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspTagException {
		CalendarOutputter outputter = new CalendarOutputter();
		// JspWriter out = pageContext.getOut();

		// outputter.setValidating(false);

		try {
			StringWriter outTemp = new StringWriter();
			outputter.output(getCalendar(), outTemp);
			JspWriter out = pageContext.getOut();
			out.print(outTemp.toString());
		} catch (Exception e) {
			throw new JspTagException("Exception while writting the content: "
					+ e.toString());
		}

		return SKIP_BODY;
	}

	public void setCalendar(final Calendar theCalendar) {
		this.calendar = theCalendar;
	}

	public Calendar getCalendar() {
		return calendar;
	}

}
