package StudyWeb.controller;

import StudyWeb.domain.Calendar;
import StudyWeb.exception.ResourceNotFoundException;
import StudyWeb.repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/calendar")
public class CalendarController {

    @Autowired
    CalendarRepository calendarRepository;

    @GetMapping
    public List<Calendar> getAllClds() {
        return calendarRepository.findAll();
    }

    @PostMapping
    public Calendar createCld(@Valid @RequestBody Calendar calendar) {
        return calendarRepository.save(calendar);
    }

    @GetMapping("/{id}")
    public Calendar getCldById(@PathVariable(value = "id") Long calendarId) {
        return calendarRepository.findById(calendarId)
                .orElseThrow(() -> new ResourceNotFoundException("Calendar", "id", calendarId));
    }

    @GetMapping("/{id}/{date}")
    public List<Calendar> getCldByDate(@PathVariable(value="date") Long date, @PathVariable(value="id") Long user_id){
        return calendarRepository.findAllByDate(date, user_id);
    }

    @PutMapping("/{id}")
    public Calendar updateCld(@PathVariable(value = "id") Long calendarId,
                           @Valid @RequestBody Calendar calendarDetails) {

        Calendar calendar = calendarRepository.findById(calendarId)
                .orElseThrow(() -> new ResourceNotFoundException("Calendar", "id", calendarId));

        calendar.setDate(calendarDetails.getDate());
        calendar.setContent(calendarDetails.getContent());

        Calendar updatedCalendar = calendarRepository.save(calendar);
        return updatedCalendar;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCld(@PathVariable(value = "id") Long calendarId) {
        Calendar calendar = calendarRepository.findById(calendarId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", calendarId));

        calendarRepository.delete(calendar);

        return ResponseEntity.ok().build();
    }
}
