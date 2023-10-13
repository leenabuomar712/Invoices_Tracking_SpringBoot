document.addEventListener('DOMContentLoaded', function () {
    const contentDiv = document.getElementById('content');

    // Load the initial template
    loadTemplate('template1.html');

    // Add event listeners to buttons or links that trigger template switches
    document.getElementById('fetch-invoices-button').addEventListener('click', function () {
        window.location.href = 'signup.html';
    });

    document.getElementById('load-template2-btn').addEventListener('click', function () {
        loadTemplate('template2.html');
    });

    function loadTemplate(templateUrl) {
        // Use AJAX to fetch the template content
        const xhr = new XMLHttpRequest();
        xhr.open('GET', templateUrl, true);

        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                // Replace the content with the fetched template
                contentDiv.innerHTML = xhr.responseText;
            }
        };

        xhr.send();
    }
});
