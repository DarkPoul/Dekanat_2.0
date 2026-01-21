package com.esvar.dekanat.uikit.util;

import com.esvar.dekanat.uikit.AppModuleDescriptor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ModuleRegistry {

    private final List<AppModuleDescriptor> modules;

    public ModuleRegistry(List<AppModuleDescriptor> modules) {
        this.modules = modules.stream()
                .sorted(Comparator.comparingInt(AppModuleDescriptor::getOrder))
                .toList();
    }

    public List<AppModuleDescriptor> all() {
        return modules;
    }
}
