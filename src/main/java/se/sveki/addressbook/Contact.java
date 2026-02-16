package se.sveki.addressbook;

public record Contact(
        long id,
        String firstName,
        String lastName,
        String country,
        String city,
        String streetName,
        String streetNumber
) {}