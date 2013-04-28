package net.empego.zkbasics.composer;

import java.util.Date;

import javax.inject.Inject;

import net.empego.zkbasics.service.ContentService;
import net.empego.zkbasics.util.ZKCDIUtil;

import org.zkoss.zhtml.Div;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.BookmarkEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Calendar;
import org.zkoss.zul.Label;

@VariableResolver(org.zkoss.zkplus.cdi.DelegatingVariableResolver.class)
public class ZKBasicsWebappComposer extends SelectorComposer<Component> {

	private static final long serialVersionUID = 8338613318454037425L;

	@Wire
	Label selectedDate;

	@Wire
	Calendar calendar;

	@Wire
	Div content;

	@Inject
	ContentService contentService;

	Date now = new Date();

	@Listen("onChange=#calendar")
	public void updateSelectedDateLabel() {
		Date calendarValue = calendar.getValue();
		selectedDate.setValue(calendarValue.toString());
		doSomethingImportantWithSelectedDate(calendarValue);
	}

	@Listen("onBookmarkChange=#helloWorldWindow")
	public void reloadContent(BookmarkEvent event) {
		String bookmark = event.getBookmark();
		Object newContent = contentService.getContent(bookmark);
		if (newContent != null) {
			content.getChildren().clear(); // clear content div
			Executions.getCurrent().createComponentsDirectly(
					(String) newContent, null, content, null);
		}
	}

	private void doSomethingImportantWithSelectedDate(Date calendarValue) {
		if (calendarValue != null && calendarValue.after(now)) {
			// mark dates in the future in red
			selectedDate.setStyle("color:red; font-weight:bold;");
		} else {
			selectedDate.setStyle("");
		}
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		ZKCDIUtil.inject(this);
		selectedDate.setValue("Initial Date: " + now);
	}
}
