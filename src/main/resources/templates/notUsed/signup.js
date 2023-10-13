document.getElementById('signup-form').addEventListener('submit', function (e) {
    e.preventDefault();

    const name = document.querySelector('input[name="name"]').value;
    const username = document.querySelector('input[name="username"]').value;
    const password = document.querySelector('input[name="password"]').value;
    const email = document.querySelector('input[name="email"]').value;

    const userData = {
        name,
        username,
        password,
        email
    };

    fetch('/api/auth/signup', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(userData),
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Signup failed');
            }
        })
        .then(data => {
            // Handle successful signup, e.g., show a success message or redirect to login
            alert('Signup successful!');
            // Optionally, you can redirect to a login page or perform other actions
        })
        .catch(error => {
            // Handle signup error, e.g., display an error message
            alert('Signup failed. Please try again.');
            console.error('Signup error:', error);
        });
});
