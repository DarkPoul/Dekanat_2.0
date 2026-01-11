package com.esvar.dekanat.modules.studyplans.domain;

import java.time.LocalDate;

public record HistoryEntry(LocalDate date, String author, String description) {
}
