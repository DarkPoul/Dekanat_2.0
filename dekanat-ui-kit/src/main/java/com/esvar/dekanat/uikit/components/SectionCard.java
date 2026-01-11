package com.esvar.dekanat.uikit.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;

public class SectionCard extends Div {
    public SectionCard(Component... components) {
        add(components);
        setWidthFull();
        getStyle()
                .set("padding", "var(--lumo-space-m)")
                .set("border", "1px solid var(--lumo-contrast-10pct)")
                .set("border-radius", "var(--lumo-border-radius-l)")
                .set("background", "var(--lumo-base-color)")
                .set("box-shadow", "var(--lumo-box-shadow-xs)");
    }
}
