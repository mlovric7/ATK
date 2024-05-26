document.addEventListener("DOMContentLoaded", () => {
    const eventId = window.location.pathname.split('/').pop();
    const eventForm = document.getElementById("event-details-form");
    const organizerSelect = document.getElementById("event-organizer");
    const feedbackList = document.getElementById("feedback-list");
    const addFeedbackButton = document.getElementById("add-feedback-button");
    const deleteEventButton = document.getElementById("delete-event-button");
    let users = [];

    fetch(`/api/event/${eventId}`)
        .then(response => response.json())
        .then(data => {
            populateEventForm(data.event);
            populateFeedbackList(data.feedbacks);
        });

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

    fetch('/api/user')
        .then(response => response.json())
        .then(data => {
            users = data;
        });

    eventForm.addEventListener("submit", (event) => {
        event.preventDefault();
        const updatedEvent = {
            id: eventId,
            name: document.getElementById("event-name").value,
            description: document.getElementById("event-description").value,
            start: document.getElementById("event-start-display").value,
            end: document.getElementById("event-end-display").value,
            numberOfSeats: document.getElementById("event-seats").value,
            address: document.getElementById("event-address").value,
            organizer: { id: document.getElementById("event-organizer").value }
        };

        fetch(`/api/event/${eventId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatedEvent)
        })
            .then(response => response.json())
            .then(updatedEvent => {
                console.log('Event updated successfully');
            });
    });

    deleteEventButton.addEventListener('click', () => {
        if (confirm('Are you sure you want to delete this event?')) {
            fetch(`/api/event/${eventId}`, {
                method: 'DELETE'
            })
                .then(() => {
                    window.location.href = '/events';
                });
        }
    });

    function populateEventForm(event) {
        document.getElementById("event-name").value = event.name;
        document.getElementById("event-description").value = event.description;
        document.getElementById("event-start-display").value = event.start;
        document.getElementById("event-end-display").value = event.end;
        document.getElementById("event-seats").value = event.numberOfSeats;
        document.getElementById("event-address").value = event.address;
        document.getElementById("event-organizer").value = event.organizer.id;
    }

    function populateFeedbackList(feedbacks) {
        feedbackList.innerHTML = '';
        feedbacks.forEach(feedback => {
            addFeedbackToList(feedback);
        });
    }

    function addFeedbackToList(feedback) {
        const feedbackItem = document.createElement('tr');
        feedbackItem.dataset.id = feedback.id;
        feedbackItem.innerHTML = `
            <td>
                <span class="feedback-rating">${feedback.rating}</span>
                <input type="number" class="form-control d-none feedback-rating-input" value="${feedback.rating}">
            </td>
            <td>
                <span class="feedback-comment">${feedback.comment}</span>
                <input type="text" class="form-control d-none feedback-comment-input" value="${feedback.comment}">
            </td>
            <td>
                <span class="feedback-user">${feedback.user.username}</span>
                <select class="form-control d-none feedback-user-select">
                    ${users.map(user => `<option value="${user.id}" ${user.id === feedback.user.id ? 'selected' : ''} data-username="${user.username}">${user.username}</option>`).join('')}
                </select>
            </td>
            <td>
                <button class="btn btn-warning btn-sm edit-feedback-button">Edit</button>
                <button class="btn btn-success btn-sm d-none save-feedback-button">Save</button>
                <button class="btn btn-danger btn-sm delete-feedback-button">Delete</button>
            </td>
        `;
        feedbackList.appendChild(feedbackItem);

        const editButton = feedbackItem.querySelector('.edit-feedback-button');
        const saveButton = feedbackItem.querySelector('.save-feedback-button');
        const deleteButton = feedbackItem.querySelector('.delete-feedback-button');

        editButton.addEventListener('click', () => {
            toggleFeedbackEditMode(feedbackItem);
        });

        saveButton.addEventListener('click', () => {
            const updatedRating = feedbackItem.querySelector('.feedback-rating-input').value;
            const updatedComment = feedbackItem.querySelector('.feedback-comment-input').value;
            const updatedFeedback = {
                id: feedback.id,
                rating: updatedRating,
                comment: updatedComment
            };
            fetch(`/api/feedback/${feedback.id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(updatedFeedback)
            })
                .then(response => response.json())
                .then(updatedFeedback => {
                    feedbackItem.querySelector('.feedback-rating').textContent = updatedFeedback.rating;
                    feedbackItem.querySelector('.feedback-comment').textContent = updatedFeedback.comment;
                    toggleFeedbackEditMode(feedbackItem);
                });
        });

        deleteButton.addEventListener('click', () => {
            if (confirm('Are you sure you want to delete this feedback?')) {
                fetch(`/api/feedback/${feedback.id}`, {
                    method: 'DELETE'
                })
                    .then(() => {
                        feedbackList.removeChild(feedbackItem);
                    });
            }
        });
    }

    function toggleFeedbackEditMode(feedbackItem) {
        const ratingSpan = feedbackItem.querySelector('.feedback-rating');
        const commentSpan = feedbackItem.querySelector('.feedback-comment');
        const ratingInput = feedbackItem.querySelector('.feedback-rating-input');
        const commentInput = feedbackItem.querySelector('.feedback-comment-input');
        const editButton = feedbackItem.querySelector('.edit-feedback-button');
        const saveButton = feedbackItem.querySelector('.save-feedback-button');

        ratingSpan.classList.toggle('d-none');
        commentSpan.classList.toggle('d-none');
        ratingInput.classList.toggle('d-none');
        commentInput.classList.toggle('d-none');
        editButton.classList.toggle('d-none');
        saveButton.classList.toggle('d-none');
    }

    addFeedbackButton.addEventListener('click', () => {
        const newFeedbackItem = document.createElement('tr');
        newFeedbackItem.innerHTML = `
            <td><input type="number" class="form-control new-feedback-rating" placeholder="Rating"></td>
            <td><input type="text" class="form-control new-feedback-comment" placeholder="Comment"></td>
            <td>
                <select class="form-control new-feedback-user">
                    ${users.map(user => `<option value="${user.id}" data-username="${user.username}">${user.username}</option>`).join('')}
                </select>
            </td>
            <td>
                <button class="btn btn-success btn-sm save-new-feedback-button">Save</button>
                <button class="btn btn-danger btn-sm cancel-new-feedback-button">Cancel</button>
            </td>
        `;
        feedbackList.appendChild(newFeedbackItem);

        const saveNewButton = newFeedbackItem.querySelector('.save-new-feedback-button');
        const cancelNewButton = newFeedbackItem.querySelector('.cancel-new-feedback-button');

        saveNewButton.addEventListener('click', () => {
            const newRating = newFeedbackItem.querySelector('.new-feedback-rating').value;
            const newComment = newFeedbackItem.querySelector('.new-feedback-comment').value;
            const newUserSelect = newFeedbackItem.querySelector('.new-feedback-user');
            const newUserId = newUserSelect.value;
            const newUsername = newUserSelect.selectedOptions[0].getAttribute('data-username');
            const newFeedback = {
                rating: newRating,
                comment: newComment,
                user: { id: newUserId, username: newUsername },
                eventId: eventId
            };
            fetch(`/api/event/${eventId}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(newFeedback)
            })
                .then(response => response.json())
                .then(savedFeedback => {
                    addFeedbackToList(savedFeedback);
                    feedbackList.removeChild(newFeedbackItem);
                });
        });

        cancelNewButton.addEventListener('click', () => {
            feedbackList.removeChild(newFeedbackItem);
        });
    });

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
