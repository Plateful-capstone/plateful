let parentElement = document.querySelector('#all-recipes-container');

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