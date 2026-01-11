package com.esvar.dekanat.uikit.layout;

import com.esvar.dekanat.uikit.util.UITheme;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;

public abstract class BaseView extends Div {
    protected BaseView() {
        setWidthFull();
        getStyle()
                .set("padding", UITheme.PAGE_HORIZONTAL_PADDING)
                .set("box-sizing", "border-box");
    }

    protected void setContentWidth(String maxWidth) {
        getStyle().set("max-width", maxWidth);
        getStyle().set("margin", "0 auto");
    }

    protected void setContentWidthDefault() {
        setContentWidth(UITheme.CONTENT_MAX_WIDTH);
    }

    protected void setPageTitle(String title) {
        getElement().setProperty("pageTitle", title);
    }

    protected void addSection(Component component) {
        component.getElement().getStyle().set("margin-bottom", UITheme.SECTION_GAP);
        add(component);
    }
}
