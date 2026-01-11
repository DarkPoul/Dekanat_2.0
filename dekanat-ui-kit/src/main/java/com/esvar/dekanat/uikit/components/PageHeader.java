package com.esvar.dekanat.uikit.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;

public class PageHeader extends Div {
    private final H2 title;
    private final Paragraph subtitle;
    private final HorizontalLayout actions;

    public PageHeader(String titleText, String subtitleText) {
        this.title = new H2(titleText);
        this.subtitle = new Paragraph(subtitleText);
        this.actions = new HorizontalLayout();

        VerticalLayout text = new VerticalLayout(title, subtitle);
        text.setPadding(false);
        text.setSpacing(false);
        text.setMargin(false);

        HorizontalLayout layout = new HorizontalLayout(text, actions);
        layout.setWidthFull();
        layout.setJustifyContentMode(JustifyContentMode.BETWEEN);
        layout.setAlignItems(Alignment.CENTER);
        layout.setPadding(false);

        add(layout);
    }

    public void addAction(Component component) {
        actions.add(component);
    }

    public void setSubtitle(String text) {
        subtitle.setText(text);
    }
}
