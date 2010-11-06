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

	private static final long serialVersionUID = -852816112785222647L;

	protected EventTag parent;

	@Override
	public final int doStartTag() throws JspTagException {
		parent = (EventTag) findAncestorWithClass(this, EventTag.class);
		if (parent == null) {
			throw new JspTagException("nesting error");
		}

		/* Avoids several BodyTagSupport tags to be nested */
		/*
		 * BodyTagSupport temp = (BodyTagSupport)findAncestorWithClass(this,
		 * BodyTagSupport.class); if(temp != null) { throw new
		 * JspTagException("nesting error"); }
		 */
		
		//TODO: Check that this is the correct return value. Alternative is EVAL_BODY_BUFFERED
		return (EVAL_BODY_AGAIN);
	}

	@Override
	public int doAfterBody() throws JspTagException {
		BodyContent bc = getBodyContent();
		String body;
		try {
			// Do the data handling
			body = bc.getString();
			bc.clearBody();
			processBody(body);

		} catch (Exception e) {
			throw new JspTagException("Tag " + this.getClass().getName() + ": "
					+ e.getMessage());
		}
		return SKIP_BODY;
	}

	void processBody(final String body) throws JspTagException {
		throw new JspTagException("Method Overriding not working");
	}

}
