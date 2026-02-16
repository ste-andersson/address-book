package se.sveki.addressbook;

import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController

public class ContactController {
    private final AtomicLong counter = new AtomicLong();
    private final Map<Long, Contact> db = new ConcurrentHashMap<>();

    @PostMapping("/api/contacts")
    public Contact create(@RequestParam String firstName,
                          @RequestParam String lastName,
                          @RequestParam String country,
                          @RequestParam String city,
                          @RequestParam String streetName,
                          @RequestParam String streetNumber) {
        long id = counter.incrementAndGet();
        Contact created = new Contact(id, firstName, lastName, country, city, streetName, streetNumber);
        db.put(id, created);
        return created;
    }

    @GetMapping("/api/contacts")
    public java.util.List<Contact> getAll() {
        return db.values().stream().toList();
    }

    @GetMapping("api/contacts/{id}")
    public Contact getOne(@PathVariable long id) {
        return db.get(id);
    }

    @PutMapping("api/contacts/{id}")
    public Contact update(@PathVariable long id,
                          @RequestParam String firstName,
                          @RequestParam String lastName,
                          @RequestParam String country,
                          @RequestParam String city,
                          @RequestParam String streetName,
                          @RequestParam String streetNumber) {

        Contact updated = new Contact(id, firstName, lastName, country, city, streetName, streetNumber);
        db.put(id, updated);
        return updated;
    }

    @DeleteMapping("/api/contacts/{id}")
    public void delete(@PathVariable long id) {
        db.remove(id);
    }

}
