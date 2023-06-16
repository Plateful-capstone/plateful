import keys from "./keys.js";

const searchButton = document.querySelector("#searchButton");
const allRecipesButton = document.querySelector("#allRecipes");

// Helper function from searchDb
const displaySearchResults = (data) => {
    const resultContainer = document.getElementById("resultsContainer");
    resultContainer.innerHTML = "";

    data.forEach(recipe => {
        // Create a template string with the recipe information
        const recipeHTML = `
      <div>
        <h2>${recipe.recipeName}</h2>
        <img src="${recipe.recipeImageUrl}">
        <p>${recipe.recipeDescription}</p>
        <p>${recipe.recipeIngredients}</p>
      </div>
    `;

        // Add the recipe HTML to the search results container
        resultContainer.innerHTML += recipeHTML;
    });
};

// Helper functions for spoonacular
const createIngredientsList = (ingredients) => {
    let ingredientsNames = ingredients.map((ingredient) => ingredient.original);
    return ingredientsNames.join(", ");
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
            data.results.forEach((result, index) => {
                // Create recipe information
                const ingredientsList = createIngredientsList(result.extendedIngredients);
                const recipeStepsParagraph = createRecipeSteps(result.analyzedInstructions);
                const resultDiv = document.createElement("div");
                resultDiv.classList.add("resultDiv");
                resultDiv.innerHTML = `
          <img class="resultImage" src="${result.image}" alt="result image from spoonacular">
          <p class="recipeDescription">Recipe Description: ${extractSummaryInfo(result.summary)}</p>
          <h3 class="resultTitle">Title: ${result.title}</h3>
          <a href="/recipes/${result.id}">View Recipe</a>
          <p>recipe ingredients: ${ingredientsList}</p>
          <p>recipe instructions: ${recipeStepsParagraph}</p>
          <br>
          <button class="add-to-cookbook-btn" data-results-index="${index}">Add to cookbook</button>
        `;

                resultDiv.addEventListener('click', (e) => {
                    e.preventDefault();
                    const resultIndex = e.target.getAttribute('data-results-index');
                    const result = data.results[resultIndex];
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
