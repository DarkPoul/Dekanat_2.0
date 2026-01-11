package com.esvar.dekanat.modules.studyplans.domain;

public record StudyPlanEntry(
        String discipline,
        int credits,
        int hours,
        String controlForm,
        String department,
        String status,
        String statusColor
) {
}
