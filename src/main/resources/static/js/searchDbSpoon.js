
const searchButton = document.querySelector("#searchButton");
const allRecipesButton = document.querySelector("#allRecipes");


// Helper function from searchDb
const displaySearchResults = (data) => {
    const resultContainer = document.getElementById("resultsContainer");
    resultContainer.innerHTML = "";

    data.forEach(recipe => {
        // Create a template string with the recipe information
        const recipeHTML = `
      <div class="row search-results-row no-padding main-background">
        <div class="column search-results-card">
            <div class="row no-padding">
                <div class="column justify-space-between">
                    <div class="row">
                        <h2>${recipe.recipeName}</h2>
                    </div>
                    <div class="row">
                        <p><b>Recipe Description: </b>${recipe.recipeDescription}</p>
                    </div>
                    <div class="row">
                        <p class="show-hide">Show more</p>
                    </div>
                </div>
                <div class="column search-recipe-image-wrapper">
                        <img src="${recipe.recipeImageUrl}" alt="Image from DB">
                </div>
            </div>
            
            <div class="ghost-div-search">
                <div class="column gap-20">
                    <div class="row">
                        <p><b>Recipe Ingredients:</b> ${recipe.recipeIngredients}</p>
                    </div>
                    <div class="row">
                        <p><b>Recipe Directions: </b>${recipe.recipeInstructions}</p>
                    </div>
                </div>
            </div>
        </div>
      </div>
    `;

        // Add the recipe HTML to the search results container
        resultContainer.innerHTML += recipeHTML;
    });
};

// Helper functions for spoonacular
const createIngredientsList = (ingredients) => {
    let ingredientsNames = ingredients.map((ingredient) => ingredient.original);
    ingredientsNames = ingredientsNames.join(", ");
    //the last comma i want to replace with "and" and add a period after the last ingredient
    let lastComma = ingredientsNames.lastIndexOf(",");
    let ingredientsList = ingredientsNames.substring(0, lastComma) + " and" + ingredientsNames.substring(lastComma + 1) + ".";
    return ingredientsList;
};

const createRecipeSteps = (analyzedInstructions) => {
    let steps = analyzedInstructions[0].steps.map((step) => step.step);
    return steps.join("\n");
};

const extractBoldTags = (text) => {
    const regex = /<\/?b>/gi;
    return text.replace(regex, "");
};

const extractSummaryInfo = (summary) => {
    const regex = /(.*spoonacular.*b>)/i;
    const match = summary.match(regex);
    if (match) {
        return extractBoldTags(match[0] + ".");
    }
    return '';
};


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

// Function to execute Spoonacular search
const executeSpoonacularSearch = (searchValue) => {
    const searchURL = `https://api.spoonacular.com/recipes/complexSearch?query=${searchValue}&number=20&addRecipeInformation=true&fillIngredients=true&apiKey=${keys.spoonacularAPIKey}`;
    fetch(searchURL)
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            const resultsContainer = document.querySelector("#resultsContainer");
            console.log(data);
            data.results.forEach((result, index) => {
                console.log(result);
                // Create recipe information
                const ingredientsList = createIngredientsList(result.extendedIngredients);
                const recipeStepsParagraph = createRecipeSteps(result.analyzedInstructions);
                const resultDiv = document.createElement("div");

                resultDiv.classList.add("row");
                resultDiv.classList.add("no-padding");
                resultDiv.classList.add("search-results-row")
                resultDiv.classList.add("resultDiv");
                resultDiv.classList.add("main-background")
                resultDiv.innerHTML = `
        <div class="column search-results-card">
            <div class="row no-padding">
                <div class="column justify-space-between">
                    <div class="row">
                        <h2>${result.title}</h2>
                    </div>
                    <div class="row">
                        <p> <b> Recipe Description: </b>${extractSummaryInfo(result.summary)}</p>
                    </div>
                    <div class="row">
                        <button class="add-to-cookbook-btn cta" data-results-index="${index}">Add to cookbook</button>
                    </div>
                    <div class="row">
                        <p class="show-hide">Show more</p>
                    </div>
                </div>
                <div class="column search-recipe-image-wrapper">
                    <img src="${result.image}" alt="result image from spoonacular">
                </div>
            </div>
            <div class="ghost-div-search">
                <div class="column gap-20">
                    <div class="row">
                        <p> <b> Recipe Ingredients:</b> ${ingredientsList}</p>
                    </div>
                    <div class="row">
                        <p> <b>  Recipe Instructions: </b> ${recipeStepsParagraph}</p>
                    </div>
                </div>
            </div>
        </div>

        `;

                resultDiv.addEventListener('click', (e) => {
                    e.preventDefault();
                    const resultIndex = e.target.getAttribute('data-results-index');
                    const result = data.results[resultIndex];
                    console.log(result);
                    const recipe = {
                        recipeDescription: extractSummaryInfo(result.summary),
                        recipeImageUrl: result.image,
                        recipeIngredients: createIngredientsList(result.extendedIngredients),
                        recipeInstructions: createRecipeSteps(result.analyzedInstructions),
                        recipeName: result.title
                    };

                    fetch('/recipes/search/create', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                            'X-CSRF-TOKEN': document.querySelector('meta[name="_csrf"]').getAttribute('content')
                        },
                        body: JSON.stringify(recipe)
                    })
                        .then((response) => {
                            return response.json();
                        })
                        .then((data) => {
                            console.log(data);
                            // Redirect to "/recipes"
                            window.location.href = "/recipes";
                        })
                        .catch((error) => {
                            console.log(error);
                        });
                });

                resultsContainer.appendChild(resultDiv);
            });
        })
        .catch((error) => {
            console.log(error);
        });
};


// show-hide recipe details

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

