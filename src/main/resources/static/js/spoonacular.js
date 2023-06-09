import keys from "./keys.js";

// make API call for recipe information from spoonacular when user clicks "Search" button on recipe search page
const searchButton = document.querySelector("#searchButton");
const allRecipesButton = document.querySelector("#allRecipes");

//create a function that loops through all the results.extendedIngredients and creates a list of ingredients

const createIngredientsList = (ingredients) => {
    const ingredientsList = document.createElement("p");
    ingredientsList.id = "ingredientsList";

    let ingredientsNames = ingredients.map((ingredient) => ingredient.name);
    ingredientsList.innerText = ingredientsNames.join(", ");

    return ingredientsList;
}
searchButton.addEventListener("click", (e) => {
    e.preventDefault();
    const searchInput = document.querySelector("#searchInput");
    const searchValue = searchInput.value;
    const searchURL = `https://api.spoonacular.com/recipes/complexSearch?query=${searchValue}&number=1&addRecipeInformation=true&fillIngredients=true&apiKey=${keys.spoonacularAPIKey}`;
    fetch(searchURL)
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            console.log(data);
            const resultsContainer = document.querySelector("#resultsContainer");
            resultsContainer.innerHTML = "";
            data.results.forEach((result) => {
                const resultDiv = document.createElement("div");
                resultDiv.classList.add("resultDiv");
                const resultImage = document.createElement("img");
                resultImage.id = "resultImage";
                resultImage.src = result.image;
                const resultTitle = document.createElement("h3");
                resultTitle.id = "resultTitle";
                resultTitle.innerText = result.title;
                const resultLink = document.createElement("a");
                resultLink.href = `/recipes/${result.id}`;
                resultLink.innerText = "View Recipe";
                const hiddenInput = document.createElement("input");
                hiddenInput.type = "hidden";
                hiddenInput.id = "recipeDescription";
                hiddenInput.value = result.summary;
                resultDiv.appendChild(hiddenInput);
                resultDiv.appendChild(resultImage);
                resultDiv.appendChild(resultTitle);
                resultDiv.appendChild(resultLink);
                resultsContainer.appendChild(resultDiv);
            });
            let ingredientsList = createIngredientsList(data.results[0].extendedIngredients);
            resultsContainer.appendChild(ingredientsList);
        })
        .catch((error) => {
            console.log(error);
        });
});
