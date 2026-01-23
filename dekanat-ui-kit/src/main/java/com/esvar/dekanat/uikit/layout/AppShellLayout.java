package com.esvar.dekanat.uikit.layout;

import com.esvar.dekanat.uikit.AppModuleDescriptor;
import com.esvar.dekanat.uikit.state.GroupRef;
import com.esvar.dekanat.uikit.state.UiContextState;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component
@UIScope
public class AppShellLayout extends AppLayout implements RouterLayout {

    private final UiContextState uiContextState;

    public AppShellLayout(List<AppModuleDescriptor> modules, UiContextState uiContextState) {
        this.uiContextState = uiContextState;
        addToNavbar(buildTopBar());
        setPrimarySection(Section.DRAWER);
    }

    private Div buildTopBar() {
        H1 title = new H1("Деканат");
        title.addClassNames(LumoUtility.Margin.NONE, "dk-topbar__title");
        // Фіксуємо ширину лівої частини, щоб вона не заважала центру
        title.setWidth("200px");

        ComboBox<GroupRef> groupBox = buildGroupCombo();
        groupBox.addClassName("dk-topbar__group");

        // Розпірки для центрування
        com.vaadin.flow.component.html.Span leftSpacer = new com.vaadin.flow.component.html.Span();
        com.vaadin.flow.component.html.Span rightSpacer = new com.vaadin.flow.component.html.Span();

        // Визначаємо сезон (логіку можна винести в сервіс)
        boolean isWinter = false; // Тимчасово hardcoded

        HorizontalLayout sessionBadge = new HorizontalLayout();
        sessionBadge.addClassNames("dk-badge");
        sessionBadge.setSpacing(true);
        sessionBadge.setAlignItems(Alignment.CENTER);
        sessionBadge.setPadding(false);

        if (isWinter) {
            sessionBadge.add(VaadinIcon.ASTERISK.create());
            sessionBadge.add(new com.vaadin.flow.component.html.Span("Зимова сесія"));
            sessionBadge.addClassName("dk-badge--winter");
        } else {
            sessionBadge.add(VaadinIcon.SUN_O.create());
            sessionBadge.add(new com.vaadin.flow.component.html.Span("Літня сесія"));
            sessionBadge.addClassName("dk-badge--summer");
        }

        Button help = new Button("Інструкція", VaadinIcon.QUESTION_CIRCLE_O.create(),
                e -> com.vaadin.flow.component.notification.Notification.show("Інструкції (PDF) — підключимо пізніше"));
        help.addClassNames("dk-help-btn");

        HorizontalLayout right = new HorizontalLayout(sessionBadge, help);
        right.addClassName("dk-topbar__right");
        right.setAlignItems(Alignment.CENTER);
        right.setSpacing(true);
        // Фіксуємо ширину правої частини рівною лівій для ідеального центру
        right.setWidth("auto");
        right.setJustifyContentMode(FlexComponent.JustifyContentMode.END);

        // Порядок: ТИТУЛ -> СПЕЙСЕР -> ГРУПА -> СПЕЙСЕР -> ПРАВИЙ БЛОК
        HorizontalLayout row = new HorizontalLayout(title, leftSpacer, groupBox, rightSpacer, right);
        row.addClassName("dk-topbar__row");
        row.setWidthFull();
        row.setAlignItems(Alignment.CENTER);

        // Розтягуємо обидві розпірки однаково
        row.expand(leftSpacer, rightSpacer);
        row.setSpacing(false); // Вимикаємо стандартний spacing, щоб spacer-и працювали точніше

        Div root = new Div(row);
        root.addClassNames("dk-app_shell__root", "dk-topbar");
        return root;
    }


    private ComboBox<GroupRef> buildGroupCombo() {
        ComboBox<GroupRef> cb = new ComboBox<>();
        cb.setWidth("min(620px, 60vw)");
        cb.setPlaceholder("Оберіть групу…");
        cb.setClearButtonVisible(true);
        cb.setAllowCustomValue(false);

        // DEMO data (заміниш на сервіс пізніше)
        List<GroupRef> groups = List.of(
                new GroupRef("ІБК", 4, 1, 2019, 23451),
                new GroupRef("ІБК", 3, 2, 2020, 23451),
                new GroupRef("КН", 2, 1, 2021, 12001)
        );

        // label у полі
        cb.setItemLabelGenerator(GroupRef::code);

        // Пошук по всіх полях (використовуємо Callback для фільтрації на сервері)
        cb.setItems(
                query -> {
                    String f = normalize(query.getFilter().orElse(""));
                    return groups.stream()
                            .filter(g -> normalize(g.searchKey()).contains(f))
                            .skip(query.getOffset())
                            .limit(query.getLimit());
                },
                query -> {
                    String f = normalize(query.getFilter().orElse(""));
                    return (int) groups.stream()
                            .filter(g -> normalize(g.searchKey()).contains(f))
                            .count();
                }
        );

        // підхоплюємо попередній вибір (якщо був)
        uiContextState.getSelectedGroup().ifPresent(cb::setValue);

        cb.addValueChangeListener(e -> uiContextState.setSelectedGroup(e.getValue()));

        return cb;
    }

    private String normalize(String s) {
        if (s == null) return "";
        return s
                .trim()
                .toUpperCase(Locale.ROOT)
                .replace(" ", "")
                .replace(".", "")
                .replace("_", "-");
    }
}
