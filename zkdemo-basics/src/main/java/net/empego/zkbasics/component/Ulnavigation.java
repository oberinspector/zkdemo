package net.empego.zkbasics.component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import net.empego.zkbasics.service.ContentService;
import net.empego.zkbasics.util.ZKCDIUtil;

import org.zkoss.zhtml.Li;
import org.zkoss.zhtml.Ul;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.A;
import org.zkoss.zul.Div;

public class Ulnavigation extends Div implements AfterCompose {

	private static final long serialVersionUID = 3372737411720308632L;

	@Inject
	ContentService contentService;

	Map<String, Li> menuEntries = new HashMap<String, Li>();

	/**
	 * Wird aufgerufen nachdem ZK den serverseitigen Komponentenbaum aufgebaut hat.
	 */
	@Override
	public void afterCompose() {
		ZKCDIUtil.inject(this);
		Ul ul = new Ul();
		this.appendChild(ul);

		Set<String> menu = contentService.getMenu();

		for (String menuEntryLabel : menu) {
			Li menuEntry = creatMenuEntry(menuEntryLabel);
			ul.appendChild(menuEntry);
		}
	}

	/**
	 * Erzeugt einen Li Menueeintrag und speichert ihn in der menuEntries Map.
	 * 
	 * @param menuEntryLabel
	 * @return
	 */
	private Li creatMenuEntry(String menuEntryLabel) {
		A link = new A(menuEntryLabel);
		link.setHref("#" + menuEntryLabel);
		Li li = new Li();
		li.appendChild(link);
		menuEntries.put(menuEntryLabel, li);
		return li;
	}

	/**
	 * Markiert den gesetzten Menupunkt mit id="current".
	 * 
	 * @param menuEntryLabel
	 */
	public void changeCurrentItem(String menuEntryLabel) {
		for (Li li : menuEntries.values()) {
			li.removeAttribute("id");
		}
		Li li = menuEntries.get(menuEntryLabel);
		if (li != null) {
			li.setAttribute("id", "current");
		}
	}
}
