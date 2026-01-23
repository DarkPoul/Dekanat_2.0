package com.esvar.dekanat.uikit.state;

import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@UIScope
public class UiContextState {
    private GroupRef selectedGroup;

    public Optional<GroupRef> getSelectedGroup() {
        return Optional.ofNullable(selectedGroup);
    }

    public void setSelectedGroup(GroupRef selectedGroup) {
        this.selectedGroup = selectedGroup;
    }
}
