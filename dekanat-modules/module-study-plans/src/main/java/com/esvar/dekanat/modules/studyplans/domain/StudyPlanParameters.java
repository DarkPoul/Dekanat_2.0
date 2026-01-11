package com.esvar.dekanat.modules.studyplans.domain;

public record StudyPlanParameters(
        String academicYear,
        String program,
        String level,
        String faculty
) {
}
