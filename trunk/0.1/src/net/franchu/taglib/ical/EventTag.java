/**
 * 
 */
package net.franchu.taglib.ical;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import net.fortuna.ical4j.model.component.VEvent;
/**
 * @author mgilbir
 *
 */
public class EventTag extends BodyTagSupport
{
	CalendarTag parent;
	VEvent event;
	
	public int doStartTag() throws JspTagException 
	{
	   parent =  (CalendarTag)findAncestorWithClass(this, CalendarTag.class);
	   if (parent == null)
	   {
		   throw new JspTagException("nesting error");
	   }
	   
	   event = new VEvent();
	   return(EVAL_BODY_TAG);
	}
	
	public int doEndTag() throws JspTagException 
	{
	      parent.calendar.getComponents().add(event);
	      return SKIP_BODY;
	}

}
