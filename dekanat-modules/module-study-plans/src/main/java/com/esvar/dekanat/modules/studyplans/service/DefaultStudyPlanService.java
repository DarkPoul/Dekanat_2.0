package com.esvar.dekanat.modules.studyplans.service;

import com.esvar.dekanat.modules.studyplans.domain.HistoryEntry;
import com.esvar.dekanat.modules.studyplans.domain.StudyPlanEntry;
import com.esvar.dekanat.modules.studyplans.domain.StudyPlanParameters;
import com.esvar.dekanat.modules.studyplans.domain.SummaryMetric;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("!mock")
public class DefaultStudyPlanService implements StudyPlanService {
    @Override
    public List<String> getAvailableGroups() {
        return List.of();
    }

    @Override
    public List<String> getAvailableSemesters() {
        return List.of();
    }

    @Override
    public List<StudyPlanEntry> getPlanEntries(String group, String semester) {
        return List.of();
    }

    @Override
    public List<SummaryMetric> getSummaryMetrics(String group, String semester) {
        return List.of();
    }

    @Override
    public StudyPlanParameters getParameters(String group, String semester) {
        return new StudyPlanParameters("", "", "", "");
    }

    @Override
    public List<HistoryEntry> getHistory(String group, String semester) {
        return List.of();
    }
}
