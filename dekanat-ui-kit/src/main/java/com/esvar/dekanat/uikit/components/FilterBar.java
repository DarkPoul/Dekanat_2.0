package com.esvar.dekanat.uikit.components;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class FilterBar extends HorizontalLayout {
    public FilterBar() {
        setWidthFull();
        setSpacing(true);
        setPadding(true);
        getStyle()
                .set("background", "var(--lumo-contrast-5pct)")
                .set("border-radius", "var(--lumo-border-radius-m)");
    }
}
