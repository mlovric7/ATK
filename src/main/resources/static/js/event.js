document.addEventListener("DOMContentLoaded", () => {
    const eventList = document.getElementById("event-list");
    const addEventButton = document.getElementById("add-event-button");

    // Fetch and display events
    fetch('/api/event')
        .then(response => response.json())
        .then(data => {
            data.forEach(event => addEventToList(event));
        });

    // Handle add event button click
    addEventButton.addEventListener("click", () => {
        window.location.href = "/add-event";
    });

    function addEventToList(event) {
        const eventItem = document.createElement('div');
        eventItem.className = 'card mb-2';
        eventItem.dataset.id = event.id;
        eventItem.innerHTML = `
            <div class="card-body">
                <h5 class="card-title">
                    <span class="event-name">${event.name}</span>
                </h5>
                <p class="card-text">
                    Početak: <span class="event-start"><b>${event.start}</b></span><br>
                    Kraj: <span class="event-end"><b>${event.end}</b></span><br>
                    Organizator: <span class="event-organizer"><b>${event.organizer.name}</b></span>
                </p>
                <button class="btn btn-info btn-sm details-event-button">Detalji</button>
                <button class="btn btn-danger btn-sm delete-event-button">Obrisi</button>
            </div>
        `;
        eventList.appendChild(eventItem);

        const detailsButton = eventItem.querySelector('.details-event-button');
        const deleteButton = eventItem.querySelector('.delete-event-button');

        detailsButton.addEventListener('click', () => {
            window.location.href = `/event-details/${event.id}`;
        });

        deleteButton.addEventListener('click', () => {
            if (confirm('Jeste li sigurni da zelite ukloniti događaj?')) {
                fetch(`/api/event/${event.id}`, {
                    method: 'DELETE'
                })
                    .then(() => {
                        eventList.removeChild(eventItem);
                    });
            }
        });
    }
});
