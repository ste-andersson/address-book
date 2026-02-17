function renderContacts(contacts) {
    const tableBody = document.querySelector("#contactTableBody");
    tableBody.innerHTML = "";

    for(let contact of contacts) {
        const row = document.createElement("tr");

        row.innerHTML = `
        <td>${contact.firstName} ${contact.lastName}</td>
        <td>${contact.streetName} ${contact.streetNumber}</td>
        <td>${contact.city} (${contact.country})</td>
        <td>
        <button data-id="${contact.id}" class="edit-button">Edit</button>
        <button data-id="${contact.id}" class="delete-button">Delete</button>
        </td>
        `;

        tableBody.append(row);
    }
}

function loadAll() {
    fetch("/api/contacts")
    .then(response => response.json())
    .then(data => renderContacts(data));
}

document.addEventListener("DOMContentLoaded", () => {
    loadAll();
});