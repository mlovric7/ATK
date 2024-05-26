document.addEventListener("DOMContentLoaded", () => {
    const organizerList = document.getElementById("organizer-list");
    const addOrganizerButton = document.getElementById("add-organizer-button");
    const addOrganizerForm = document.getElementById("add-organizer-form");
    const organizerForm = document.getElementById("organizer-form");

    fetch('/api/organizer')
        .then(response => response.json())
        .then(data => {
            data.forEach(organizer => addOrganizerToList(organizer));
        });

    addOrganizerButton.addEventListener("click", () => {
        addOrganizerForm.classList.toggle("d-none");
    });

    organizerForm.addEventListener("submit", (event) => {
        event.preventDefault();
        const name = document.getElementById("organizer-name").value;
        const contact = document.getElementById("organizer-contact").value;

        fetch('/api/organizer', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ name, contact })
        })
            .then(response => response.json())
            .then(newOrganizer => {
                console.log(newOrganizer)
                console.log(organizerList)
                addOrganizerToList(newOrganizer);
                organizerForm.reset();
                addOrganizerForm.classList.add("d-none");
            });
    });

    function addOrganizerToList(organizer) {
        const organizerItem = document.createElement('div');
        organizerItem.className = 'card mb-2';
        organizerItem.dataset.id = organizer.id;
        organizerItem.innerHTML = `
            <div class="card-body">
                <h5 class="card-title">
                    <span class="organizer-name">${organizer.name}</span>
                    <input type="text" class="form-control d-none organizer-name-input" value="${organizer.name}">
                </h5>
                <p class="card-text">
                    Kontakt email: <span class="organizer-contact">${organizer.contact}</span>
                    <input type="text" class="form-control d-none organizer-contact-input" value="${organizer.contact}">
                </p>
                <button class="btn btn-warning btn-sm update-organizer-button">Izmjeni</button>
                <button class="btn btn-success btn-sm d-none save-organizer-button">Spremi</button>
                <button class="btn btn-danger btn-sm delete-organizer-button">Obrisi</button>
            </div>
        `;
        organizerList.appendChild(organizerItem);

        const updateButton = organizerItem.querySelector('.update-organizer-button');
        const saveButton = organizerItem.querySelector('.save-organizer-button');
        const deleteButton = organizerItem.querySelector('.delete-organizer-button');

        updateButton.addEventListener('click', () => {
            toggleEditMode(organizerItem);
        });

        saveButton.addEventListener('click', () => {
            const updatedName = organizerItem.querySelector('.organizer-name-input').value;
            const updatedContact = organizerItem.querySelector('.organizer-contact-input').value;
            fetch(`/api/organizer/${organizer.id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ id: organizer.id, name: updatedName, contact: updatedContact })
            })
                .then(response => response.json())
                .then(updatedOrganizer => {
                    organizerItem.querySelector('.organizer-name').textContent = updatedOrganizer.name;
                    organizerItem.querySelector('.organizer-contact').textContent = updatedOrganizer.contact;
                    toggleEditMode(organizerItem);
                });
        });

        deleteButton.addEventListener('click', () => {
            if (confirm('Jeste li sigurni da zelite obrisati ovog organizatora?')) {
                fetch(`/api/organizer/${organizer.id}`, {
                    method: 'DELETE'
                })
                    .then(() => {
                        organizerList.removeChild(organizerItem);
                    });
            }
        });
    }

    function toggleEditMode(organizerItem) {
        const nameSpan = organizerItem.querySelector('.organizer-name');
        const contactSpan = organizerItem.querySelector('.organizer-contact');
        const nameInput = organizerItem.querySelector('.organizer-name-input');
        const contactInput = organizerItem.querySelector('.organizer-contact-input');
        const updateButton = organizerItem.querySelector('.update-organizer-button');
        const saveButton = organizerItem.querySelector('.save-organizer-button');

        nameSpan.classList.toggle('d-none');
        contactSpan.classList.toggle('d-none');
        nameInput.classList.toggle('d-none');
        contactInput.classList.toggle('d-none');
        updateButton.classList.toggle('d-none');
        saveButton.classList.toggle('d-none');
    }
});
