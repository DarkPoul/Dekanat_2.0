package com.esvar.dekanat.uikit.state;

public record GroupRef(
        String abbr,          // ІБК
        int course,           // 4
        int subgroup,         // 1
        int admissionYear,    // 2019
        long specialtyId      // 23451
) {
    public String code() {
        return abbr + "-" + course + "-" + subgroup + "-" + admissionYear;
    }

    public String searchKey() {
        // Простий ключ для пошуку "по-людськи"
        return (code() + " " + abbr + " " + specialtyId + " " + course + " " + subgroup + " " + admissionYear)
                .toUpperCase();
    }
}
