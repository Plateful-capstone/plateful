// Get the form and modal elements
const searchForm = document.getElementById('search-form-denial');
const modalContainer = document.getElementById('modal-search-container');

// Add event listener to the form's submit event
searchForm.addEventListener('submit', function (event) {
    // Prevent the default form submission behavior
    event.preventDefault();

    // Display the modal
    modalContainer.style.display = 'block';
});

// Close the modal when the close button is clicked
const closeButtonX = document.querySelector('#close-search');
closeButtonX.addEventListener('click', closeModal);
const closeModalBtn = document.querySelector('#close-modal-btn');
closeModalBtn.addEventListener('click', function () {
    closeModal();
});

// Close the modal when the user clicks outside of it
window.addEventListener('click', function (event) {
    if (event.target === modalContainer) {
        closeModal();
    }
});

// Function to close the modal
function closeModal() {
    modalContainer.style.display = 'none';
}

let parentElement = document.querySelector('#resultsContainer');

parentElement.addEventListener('click', (event) => {
    if (event.target.classList.contains('show-hide')) {
        let ghostDiv = event.target.closest('.search-results-card').querySelector('.ghost-div-search');
        if (ghostDiv.style.display === 'none' || ghostDiv.style.display === '') {
            ghostDiv.style.display = 'block';
            event.target.textContent = 'Hide';
        } else {
            ghostDiv.style.display = 'none';
            event.target.textContent = 'Show more';
        }
    }
});