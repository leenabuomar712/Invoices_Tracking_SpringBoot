document.addEventListener('DOMContentLoaded', function () {
    const loginForm = document.getElementById('login-form');
    const errorMessage = document.getElementById('error-message');
    const successMessage = document.getElementById('success-message');

    loginForm.addEventListener('submit', function (e) {
        e.preventDefault();

        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;

        // Create a JSON object with the login credentials
        const credentials = {
            username: username,
            password: password
        };

        // Send a POST request to the login API
        fetch('localhost:8080/api/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(credentials)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Authentication failed');
                }
                return response.json();
            })
            .then(data => {

                // Display success message and store the token in localStorage
                successMessage.textContent = 'Login successful!';
                localStorage.setItem('token', data.token);
            })
            .catch(error => {

                // Display error message
                errorMessage.textContent = 'Login failed. Please check your credentials.';
            });
    });
});
