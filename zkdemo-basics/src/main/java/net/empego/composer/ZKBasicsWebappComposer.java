package net.empego.composer;

import java.util.Date;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Calendar;
import org.zkoss.zul.Label;

public class ZKBasicsWebappComposer extends SelectorComposer<Component> {

	private static final long serialVersionUID = 8338613318454037425L;
	
	@Wire
	Label selectedDate;
	
	@Wire
	Calendar calendar;
	
	Date now = new Date();
	
	@Listen("onChange=#calendar")
	public void updateSelectedDateLabel(){
		Date calendarValue = calendar.getValue();
		selectedDate.setValue(calendarValue.toString());
		doSomethingImportantWithSelectedDate(calendarValue);
	}
	
	private void doSomethingImportantWithSelectedDate(Date calendarValue) {
		if(calendarValue!= null&& calendarValue.after(now)){
			// mark dates in the future in red 
			selectedDate.setStyle("color:red; font-weight:bold;");
		}else{
			selectedDate.setStyle("");
		}
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);
		selectedDate.setValue("Initial Date: "+now);
	}
}
