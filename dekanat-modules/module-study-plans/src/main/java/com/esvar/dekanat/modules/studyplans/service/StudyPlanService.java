package com.esvar.dekanat.modules.studyplans.service;

import com.esvar.dekanat.modules.studyplans.domain.HistoryEntry;
import com.esvar.dekanat.modules.studyplans.domain.StudyPlanEntry;
import com.esvar.dekanat.modules.studyplans.domain.StudyPlanParameters;
import com.esvar.dekanat.modules.studyplans.domain.SummaryMetric;

import java.util.List;

public interface StudyPlanService {
    List<String> getAvailableGroups();

    List<String> getAvailableSemesters();

    List<StudyPlanEntry> getPlanEntries(String group, String semester);

    List<SummaryMetric> getSummaryMetrics(String group, String semester);

    StudyPlanParameters getParameters(String group, String semester);

    List<HistoryEntry> getHistory(String group, String semester);
}
