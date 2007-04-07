/**
 * 
 */
package net.franchu.taglib.ical;

import javax.servlet.jsp.JspTagException;

import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Summary;
import net.fortuna.ical4j.model.property.Uid;

/**
 * @author mgilbir
 *
 */
public class UidTag extends EventBodyTag
{
	void ProcessBody(String body) throws JspTagException
	{
		try
		{
			VEvent event = parent.event;
		
			Uid uid = new Uid(body); 
			event.getProperties().add(uid);
		}
		catch (Exception e)
		{
			throw new JspTagException(e);
		}
	}
}
