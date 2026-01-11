package com.esvar.dekanat.uikit.util;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public final class ConfirmDialogHelper {
    private ConfirmDialogHelper() {
    }

    public static void show(String title, String message, Runnable onConfirm) {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle(title);

        Paragraph text = new Paragraph(message);
        Div content = new Div(text);
        dialog.add(content);

        Button confirm = new Button("Підтвердити", event -> {
            onConfirm.run();
            dialog.close();
        });
        Button cancel = new Button("Скасувати", event -> dialog.close());

        HorizontalLayout footer = new HorizontalLayout(cancel, confirm);
        dialog.getFooter().add(footer);
        dialog.open();
    }
}
