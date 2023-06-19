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
const searchButton = document.querySelector("#searchButton");
// Event listener for search button for searchDb
searchButton.addEventListener("click", (e) => {
    e.preventDefault();
    const searchInput = document.getElementById("searchInput").value;
    const url = `/api/recipes/search?search=${searchInput}`;

    fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': document.querySelector('meta[name="_csrf"]').getAttribute('content')
        }
    })
        .then(response => response.json())
        .then(data => {
            displaySearchResults(data); // Call the helper function to display search results
            executeSpoonacularSearch(searchInput); // Call the spoonacular search function after searchDb results are displayed
        })
        .catch(error => {
            console.error(error);
        });
});