// document.addEventListener('DOMContentLoaded', () => {
//     const container = document.getElementById('resultsContainer');
//     const rows = container.querySelectorAll('.search-results-row');
//     const descriptions = {};
//
//     rows.forEach((row) => {
//         const description = row.querySelector('p b:first-child + span').textContent;
//
//         if (descriptions[description]) {
//             row.style.display = 'none';
//         } else {
//             descriptions[description] = true;
//         }
//     });
// }