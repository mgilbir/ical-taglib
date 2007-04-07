/**
 * 
 */
package net.franchu.taglib.ical;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * @author mgilbir
 *
 */
public class EventBodyTag extends BodyTagSupport {

	EventTag parent;
	
	public int doStartTag() throws JspTagException 
	{
	   parent =  (EventTag)findAncestorWithClass(this, EventTag.class);
	   if (parent == null)
	   {
		   throw new JspTagException("nesting error");
	   }
	   
	   /* Avoids several BodyTagSupport tags to be nested */ 
	   /*BodyTagSupport temp = (BodyTagSupport)findAncestorWithClass(this, BodyTagSupport.class);
	   if(temp != null)
	   {
		   throw new JspTagException("nesting error");		   
	   }*/
	   return(EVAL_BODY_TAG);
	}
	
	public int doAfterBody() throws JspTagException {
	      BodyContent bc = getBodyContent();
	      String body;
	      try 
	      {
	      //Do the data handling
	    	  body = bc.getString();
	    	  bc.clearBody();
	    	  ProcessBody(body);
	    	  
	      } catch (Exception e) {
	         throw new JspTagException("Tag " + this.getClass().getName() +": " + e.getMessage());
	      }
	      return SKIP_BODY;
	   }
	
	void ProcessBody(String body) throws JspTagException
	{
	    throw new JspTagException("Method Overriding not working");	
	}
	
}
