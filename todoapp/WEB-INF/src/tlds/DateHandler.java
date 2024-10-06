package tlds;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.JspException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.util.Date;

public class DateHandler extends SimpleTagSupport{
	private Timestamp value;
	
	public void setValue(Timestamp value){
		this.value = value;
	}

	public void doTag() throws JspException, IOException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, yyyy");
		
		String simpleDate = sdf.format(new Date(value.getTime()));

		getJspContext().getOut().write(simpleDate);
	}
}