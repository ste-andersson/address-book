package se.sveki.addressbook;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
