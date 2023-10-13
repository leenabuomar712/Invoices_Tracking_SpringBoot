// Function to fetch and display invoices
function fetchInvoices() {
    // Make a GET request to your API endpoint
    fetch('/api/auth/invoices')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            // Assuming the API response returns an array of invoices
            const invoices = data.content;

            // Get the container element
            const invoiceContainer = document.getElementById('invoice-container');

            // Clear the existing content
            invoiceContainer.innerHTML = '';

            // Loop through the invoices and create elements to display them
            invoices.forEach(invoice => {
                const invoiceDiv = document.createElement('div');
                invoiceDiv.classList.add('invoice');

                const invoiceTitle = document.createElement('h2');
                invoiceTitle.textContent = `Invoice ID: ${invoice.id}`;

                const invoiceInfo = document.createElement('p');
                invoiceInfo.textContent = `Title: ${invoice.title}, Description: ${invoice.description}`;

                invoiceDiv.appendChild(invoiceTitle);
                invoiceDiv.appendChild(invoiceInfo);
                invoiceContainer.appendChild(invoiceDiv);
            });
        })
        .catch(error => {
            console.error('There was a problem fetching the invoices:', error);
        });
}

// Call the fetchInvoices function when the page loads
window.addEventListener('load', fetchInvoices);
