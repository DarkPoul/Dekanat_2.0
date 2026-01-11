package com.esvar.dekanat.modules.studyplans.service;

import com.esvar.dekanat.modules.studyplans.domain.HistoryEntry;
import com.esvar.dekanat.modules.studyplans.domain.StudyPlanEntry;
import com.esvar.dekanat.modules.studyplans.domain.StudyPlanParameters;
import com.esvar.dekanat.modules.studyplans.domain.SummaryMetric;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Profile("mock")
public class MockStudyPlanService implements StudyPlanService {
    @Override
    public List<String> getAvailableGroups() {
        return List.of("КН-21", "КН-22", "ІП-21", "ЕК-22");
    }

    @Override
    public List<String> getAvailableSemesters() {
        return List.of("1", "2", "3", "4", "5", "6", "7", "8");
    }

    @Override
    public List<StudyPlanEntry> getPlanEntries(String group, String semester) {
        return List.of(
                new StudyPlanEntry("Програмування", 6, 180, "Екзамен", "Кафедра ПЗ", "Активний", "#2dd4bf"),
                new StudyPlanEntry("Бази даних", 5, 150, "Залік", "Кафедра ІС", "Погоджено", "#60a5fa"),
                new StudyPlanEntry("Математичний аналіз", 4, 120, "Екзамен", "Кафедра МА", "Потребує рев'ю", "#f97316"),
                new StudyPlanEntry("Англійська мова", 3, 90, "Залік", "Кафедра ІМ", "Активний", "#2dd4bf"),
                new StudyPlanEntry("Проєктний практикум", 2, 60, "Залік", "Кафедра ПЗ", "Чернетка", "#a78bfa")
        );
    }

    @Override
    public List<SummaryMetric> getSummaryMetrics(String group, String semester) {
        return List.of(
                new SummaryMetric("Усього кредитів", "30"),
                new SummaryMetric("Годин", "780"),
                new SummaryMetric("Лекції / Практики / Лаби", "240 / 280 / 260")
        );
    }

    @Override
    public StudyPlanParameters getParameters(String group, String semester) {
        return new StudyPlanParameters("2024/2025", "Комп'ютерні науки", "Бакалавр", "Факультет ІТ");
    }

    @Override
    public List<HistoryEntry> getHistory(String group, String semester) {
        return List.of(
                new HistoryEntry(LocalDate.now().minusDays(2), "Деканат", "Оновлено форму контролю заліку"),
                new HistoryEntry(LocalDate.now().minusDays(10), "Методист", "Додано дисципліну " + "Проєктний практикум"),
                new HistoryEntry(LocalDate.now().minusDays(21), "Зав. кафедри", "Погоджено план для семестру " + semester)
        );
    }
}
