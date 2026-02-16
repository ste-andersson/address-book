package se.sveki.addressbook;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController

public class ContactController {
    private final AtomicLong counter = new AtomicLong();
    private final Map<Long, Contact> db = new ConcurrentHashMap<>();
}
