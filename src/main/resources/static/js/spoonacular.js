// import keys from "./keys.js";
//
// // make API call for recipe information from spoonacular when user clicks "Search" button on recipe search page
// const searchButton = document.querySelector("#searchButton");
// const allRecipesButton = document.querySelector("#allRecipes");
//
// //create a function that loops through all the results.extendedIngredients and creates a list of ingredients
//
//
//
// const createIngredientsList = (ingredients) => {
//
//     let ingredientsNames = ingredients.map((ingredient) => ingredient.original);
//     return ingredientsNames.join(", "); // Return the plain string representation
//
//
// }
//
//
// // Create a function that loops through the instructions and creates a list of instructions
//
// const createRecipeSteps = (analyzedInstructions) => {
//
//     let steps = analyzedInstructions[0].steps.map((step) => step.step);
//     return steps.join("\n"); // Return the plain string representation with line breaks
//
// }
// const extractBoldTags = (text) => {
//     const regex = /<\/?b>/gi;
//     return text.replace(regex, "");
// };
//
//
// const extractSummaryInfo = (summary) => {
//     const regex = /(.*spoonacular.*b>)/i;
//     const match = summary.match(regex);
//     if (match) {
//         return extractBoldTags(match[0] + ".");
//     }
//     return '';
// }
//
//
// searchButton.addEventListener("click", (e) => {
//     e.preventDefault();
//     const searchInput = document.querySelector("#searchInput");
//     const searchValue = searchInput.value;
//     const searchURL = `https://api.spoonacular.com/recipes/complexSearch?query=${searchValue}&number=20&addRecipeInformation=true&fillIngredients=true&apiKey=${keys.spoonacularAPIKey}`;
//     fetch(searchURL)
//         .then((response) => {
//             return response.json();
//         })
//         .then((data) => {
//             console.log(data);
//             const resultsContainer = document.querySelector("#resultsContainer");
//             resultsContainer.innerHTML = "";
//             data.results.forEach((result, index) => {
//
//                 const recipe = {};
//
//                 const ingredientsList = createIngredientsList(result.extendedIngredients);
//                 const recipeStepsParagraph = createRecipeSteps(result.analyzedInstructions);
//
//                 const resultDiv = document.createElement("div");
//                 resultDiv.classList.add("resultDiv");
//                 resultDiv.innerHTML = `
//
//                 <img class="resultImage" src="${result.image}" alt="result image from spoonacular">
//                 <p class = "recipeDescription">Recipe Description: ${extractSummaryInfo(result.summary)}</p>
//                 <h3 class="resultTitle">Title: ${result.title}</h3>
//                 <a href="/recipes/${result.id}">View Recipe</a>
//                 <p>recipe ingredients: ${ingredientsList}</p>
//                 <p>recipe instructions: ${recipeStepsParagraph}</p>
//                 <br>
//                 <button class="add-to-cookbook-btn" data-results-index="${index}">Add to cookbook</button>
//                 `;
//
//                 resultDiv.addEventListener('click', (e) => {
//                     console.log('add to cookbook button clicked')
//                     e.preventDefault();
//                     console.log('add to cookbook button clicked')
//                     const resultIndex = e.target.getAttribute('data-results-index');
//                     const result = data.results[resultIndex];
//                     const recipe = {
//                         recipeDescription: extractSummaryInfo(result.summary),
//                         recipeImageUrl: result.image,
//                         recipeIngredients: createIngredientsList(result.extendedIngredients),
//                         recipeInstructions: createRecipeSteps(result.analyzedInstructions),
//                         recipeName: result.title
//                     };
//
//                     console.log(recipe);
//                     fetch('/recipes/search/create', {
//                         method: 'POST',
//                         headers: {
//                             'Content-Type': 'application/json',
//                             'X-CSRF-TOKEN': document.querySelector('meta[name="_csrf"]').getAttribute('content')
//                         },
//                         body: JSON.stringify(recipe)
//                     })
//                         .then((response) => {
//                             return response.json();
//                         })
//                         .then((data) => {
//                             console.log(data);
//                             // redirect to "/recipes"
//                             window.location.href = "/recipes";
//                         })
//                         .catch((error) => {
//                             console.log(error);
//                         });
//                 });
//
//                 console.log(extractSummaryInfo(result.summary))
//                 resultsContainer.appendChild(resultDiv);
//             });
//
//         })
//         .catch((error) => {
//             console.log(error);
//         });
// });