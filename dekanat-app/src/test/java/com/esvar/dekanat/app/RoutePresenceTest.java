package com.esvar.dekanat.app;

import com.esvar.dekanat.modules.debtors.views.DebtorsView;
import com.esvar.dekanat.modules.grades.views.GradesView;
import com.esvar.dekanat.modules.reports.views.ReportsView;
import com.esvar.dekanat.modules.students.views.StudentsView;
import com.esvar.dekanat.modules.studyplans.views.StudyPlansEmptyView;
import com.esvar.dekanat.modules.studyplans.views.StudyPlansPlanView;
import com.esvar.dekanat.modules.studyplans.views.StudyPlansStartView;
import com.esvar.dekanat.app.views.HomeView;
import com.vaadin.flow.router.Route;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class RoutePresenceTest {
    @Test
    void requiredRoutesAreDeclared() {
        Map<Class<?>, String> routes = Map.of(
                StudyPlansStartView.class, "study-plans",
                StudyPlansPlanView.class, "study-plans/plan",
                StudyPlansEmptyView.class, "study-plans/empty",
                HomeView.class, "",
                StudentsView.class, "students",
                GradesView.class, "grades",
                ReportsView.class, "reports",
                DebtorsView.class, "debtors"
        );

        routes.forEach((viewClass, expectedRoute) -> {
            Route route = viewClass.getAnnotation(Route.class);
            assertThat(route).as("Route for %s", viewClass.getSimpleName()).isNotNull();
            assertThat(route.value()).isEqualTo(expectedRoute);
        });
    }
}
