package com.esvar.dekanat.app.views;

import com.esvar.dekanat.uikit.AppModuleDescriptor;
import com.esvar.dekanat.uikit.layout.AppShellLayout;
import com.esvar.dekanat.uikit.layout.BaseView;
import com.esvar.dekanat.uikit.util.ModuleRegistry;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route(value = "", layout = AppShellLayout.class)
@PageTitle("Головна")
public class HomeView extends BaseView {

    private static final int COLS = 2;
    private static final int ROWS = 3;
    private static final int PAGE_SIZE = COLS * ROWS;

    private final List<AppModuleDescriptor> modules;
    private int offset = 0;

    private final Button upBtn = new Button("▲");
    private final Button downBtn = new Button("▼");
    private final Div grid = new Div();

    public HomeView(ModuleRegistry registry) {
        this.modules = registry.all();

        addClassName("dk-home");

        H2 title = new H2("Головна");
        title.addClassName("dk-home__title");

        grid.addClassName("dk-home__grid");

        Div viewport = new Div(grid);
        viewport.addClassName("dk-home__viewport");

        setupArrow(upBtn, true);
        setupArrow(downBtn, false);

        upBtn.addClickListener(e -> {
            offset = Math.max(0, offset - PAGE_SIZE);
            render();
        });

        downBtn.addClickListener(e -> {
            int maxOffset = Math.max(0, modules.size() - PAGE_SIZE);
            offset = Math.min(maxOffset, offset + PAGE_SIZE);
            render();
        });

        VerticalLayout root = new VerticalLayout();
        root.addClassName("dk-home__root");
        root.setPadding(false);
        root.setSpacing(false);
        root.setWidthFull();
        root.setSizeFull();

        // по центру по горизонталі
        root.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);

        // порядок: заголовок, ▲, сітка, ▼
        root.add(title, upBtn, viewport, downBtn);

        // viewport розтягується, тому ▼ сяде вниз, а весь блок буде центрований CSS-ом
        root.expand(viewport);

        add(root);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        if (attachEvent.isInitialAttach()) {
            render();
        }
    }

    private void setupArrow(Button b, boolean isUp) {
        b.addClassName("dk-home__arrow");
        b.addClassName(isUp ? "dk-home__arrow--up" : "dk-home__arrow--down");
        b.setAriaLabel(isUp ? "Прокрутити вгору" : "Прокрутити вниз");
    }

    private void render() {
        grid.removeAll();

        if (modules.isEmpty()) {
            Div empty = new Div("Немає доступних модулів.");
            empty.addClassName("dk-home__empty");
            grid.add(empty);

            upBtn.setEnabled(false);
            downBtn.setEnabled(false);
            syncArrowState();
            return;
        }

        int end = Math.min(offset + PAGE_SIZE, modules.size());
        List<AppModuleDescriptor> visible = modules.subList(offset, end);

        for (AppModuleDescriptor m : visible) {
            grid.add(createModuleTile(m));
        }

        int placeholders = PAGE_SIZE - visible.size();
        for (int i = 0; i < placeholders; i++) {
            grid.add(createPlaceholderTile());
        }

        upBtn.setEnabled(offset > 0);
        downBtn.setEnabled(offset + PAGE_SIZE < modules.size());
        syncArrowState();
    }

    private void syncArrowState() {
        toggleDisabledClass(upBtn);
        toggleDisabledClass(downBtn);
    }

    private void toggleDisabledClass(Button b) {
        if (b.isEnabled()) b.removeClassName("is-disabled");
        else b.addClassName("is-disabled");
    }

    private Button createModuleTile(AppModuleDescriptor module) {
        Button tile = new Button(module.getName(), module.getIcon().create());
        tile.addClassName("dk-module-tile");

        tile.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate(module.getRoute())));
        tile.setAriaLabel("Відкрити модуль: " + module.getName());
        return tile;
    }

    private Div createPlaceholderTile() {
        Div ph = new Div();
        ph.addClassName("dk-module-tile");
        ph.addClassName("dk-module-tile--placeholder");
        return ph;
    }
}
