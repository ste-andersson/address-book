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

    const form = document.querySelector("#contactCreationForm");

    form.addEventListener("submit", function (event) {
        event.preventDefault();

        const firstName = document.querySelector("#firstName").value;
        const lastName = document.querySelector("#lastName").value;
        const country = document.querySelector("#country").value;
        const city = document.querySelector("#city").value;
        const streetName = document.querySelector("#streetName").value;
        const streetNumber = document.querySelector("#streetNumber").value;

        const params = new URLSearchParams({
            firstName,
            lastName,
            country,
            city,
            streetName,
            streetNumber
        });

        fetch(`/api/contacts?${params.toString()}`, {
            method: "POST"
        })
        .then(response => response.json())
        .then(() => {
            form.reset();
            loadAll();
        });
    });

    loadAll();
});

document.addEventListener("click", function (event) {
    if (event.target.classList.contains("delete-button")) {
        const id = event.target.dataset.id;

        fetch(`/api/contacts/${id}`, {
            method: "DELETE"
        })
        .then(() => loadAll());
    }
});