$(document).ready(function () {
    // URL to fetch roles from your Spring Boot API
    const rolesUrl = '/role';

    // Select the <ul> element to populate the roles
    const roleList = $('#role');

    // Function to append a role to the <ul> element
    function appendRole(role) {
        roleList.append(`<li>${role.name}</li>`);
    }

    // Fetch roles from the API and populate in the <ul> element
    $.ajax({
        url: rolesUrl,
        method: 'GET',
        success: function (roles) {
            roles.forEach(function (role) {
                appendRole(role);
            });
        },
        error: function () {
            roleList.append('<li>Error fetching roles.</li>');
        }
    });
});
