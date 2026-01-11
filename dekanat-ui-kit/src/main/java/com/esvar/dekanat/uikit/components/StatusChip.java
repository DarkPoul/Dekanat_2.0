package com.esvar.dekanat.uikit.components;

import com.vaadin.flow.component.html.Span;

public class StatusChip extends Span {
    public StatusChip(String text, String backgroundColor) {
        setText(text);
        getStyle()
                .set("background", backgroundColor)
                .set("color", "var(--lumo-base-color)")
                .set("border-radius", "999px")
                .set("padding", "2px 10px")
                .set("font-size", "var(--lumo-font-size-s)")
                .set("font-weight", "600");
    }
}
