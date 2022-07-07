package lbd.fissst.springlbd.service;

import lbd.fissst.springlbd.Entity.Sprint;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface SprintService {
    Sprint save(Sprint sprint);

    List<Sprint> getAllByGivenTimePeriod(LocalDate dateFrom, LocalDate dateTo);
}
