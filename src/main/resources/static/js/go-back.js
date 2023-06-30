document.addEventListener('DOMContentLoaded', () => {
    let goBack = document.getElementById("go-back-button");

    goBack.addEventListener('click', () => {
        history.go(-1);
    });

    // MODIFIES THE INGREDIENTS SECTION TO DISPLAY IN TWO COLUMNS START
    let ingredientsElement = document.querySelector('#ingredients-content');
    let ingredientsString = ingredientsElement.innerText;
    console.log(ingredientsString);

    const ingredients = ingredientsString.split(',');

    let ingredientList = '';
    let halfLength = Math.ceil(ingredients.length / 2);

    ingredients.forEach((ingredient, index) => {
        ingredientList += ingredient.trim();

        // Add a line break after each ingredient except the last one
        if (index < ingredients.length - 1) {
            ingredientList += '<br>';
        }

        // Split into two columns after reaching halfway point
        if (index === halfLength - 1) {
            ingredientList += '</div><div class="row justify-center" id="ingredients-content">';
        }
    });

    // Wrap the ingredientList in <div> elements
    ingredientList = '<div class="row justify-center" id="ingredients-content">' + ingredientList + '</div>';

    ingredientsElement.innerHTML = ingredientList;
    // MODIFIES THE INGREDIENTS SECTION TO DISPLAY IN TWO COLUMNS END
});
