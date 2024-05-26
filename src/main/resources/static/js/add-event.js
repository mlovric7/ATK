document.addEventListener("DOMContentLoaded", () => {
    const eventForm = document.getElementById("event-form");
    const organizerSelect = document.getElementById("event-organizer");

    // Fetch and display organizers in dropdown
    fetch('/api/organizer')
        .then(response => response.json())
        .then(data => {
            data.forEach(organizer => {
                const option = document.createElement('option');
                option.value = organizer.id;
                option.textContent = organizer.name;
                organizerSelect.appendChild(option);
            });
        });

    // Handle form submission
    eventForm.addEventListener("submit", (event) => {
        event.preventDefault();
        const name = document.getElementById("event-name").value;
        const description = document.getElementById("event-description").value;
        const start = document.getElementById("event-start-display").value;
        const end = document.getElementById("event-end-display").value;
        const numberOfSeats = document.getElementById("event-seats").value;
        const address = document.getElementById("event-address").value;
        const organizerId = document.getElementById("event-organizer").value;

        const eventDTO = {
            name,
            description,
            start,
            end,
            numberOfSeats,
            address,
            organizer: { id: organizerId }
        };

        fetch('/api/event', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(eventDTO)
        })
            .then(response => response.json())
            .then(newEvent => {
                window.location.href = `/events`;
            });
    });

    // Handle dates
    const startDateDisplay = document.getElementById('event-start-display');
    const startDatePicker = document.getElementById('event-start-picker');
    const startDateButton = document.getElementById('start-date-button');

    const endDateDisplay = document.getElementById('event-end-display');
    const endDatePicker = document.getElementById('event-end-picker');
    const endDateButton = document.getElementById('end-date-button');

    startDateButton.addEventListener('click', function() {
        startDatePicker.showPicker();
    });

    endDateButton.addEventListener('click', function() {
        endDatePicker.showPicker();
    });

    startDatePicker.addEventListener('change', function() {
        formatAndDisplayDate(startDatePicker, startDateDisplay);
    });

    endDatePicker.addEventListener('change', function() {
        formatAndDisplayDate(endDatePicker, endDateDisplay);
    });

    function formatAndDisplayDate(input, displayElement) {
        const date = new Date(input.value);
        displayElement.value = formatDateToCroatian(date);
    }

    function formatDateToCroatian(date) {
        const day = String(date.getDate()).padStart(2, '0');
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const year = date.getFullYear();
        return `${day}. ${month}. ${year}.`;
    }
});
