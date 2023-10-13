function createInvoice() {
    const invoiceForm = document.getElementById('invoiceForm');
    const formData = new FormData(invoiceForm);

    // Check if an attachment was selected
    const attachment = document.getElementById('attachment').files[0];
    if (attachment) {

        // Generate a pre-signed URL for uploading the attachment to S3
        fetch('/generate-presigned-url?file_name=' + attachment.name)
            .then(response => response.json())
            .then(data => {
                // Upload the attachment to S3 using the pre-signed URL
                fetch(data.preSignedUrl, {
                    method: 'PUT',
                    body: attachment
                })
                    .then(response => {
                        if (response.ok) {
                            // Attachment uploaded successfully
                            console.log('Attachment uploaded successfully');
                        } else {
                            console.error('Failed to upload attachment');
                        }

                        // Create the invoice with attachment details
                        createInvoiceWithData(formData, data.filePath);
                    })
                    .catch(error => {
                        console.error('Error uploading attachment:', error);
                    });
            })
            .catch(error => {
                console.error('Error generating pre-signed URL:', error);
            });
    } else {
        // Create the invoice without attachment
        createInvoiceWithData(formData, null);
    }
}

function createInvoiceWithData(formData, attachmentPath) {
    // Append attachment path to the form data
    formData.append('attachmentPath', attachmentPath);

    // Send the form data to the server to create the invoice
    fetch('/api/invoices', {
        method: 'POST',
        body: formData
    })
        .then(response => response.json())
        .then(data => {
            // Handle the response from the server
            const responseDiv = document.getElementById('response');
            responseDiv.innerHTML = 'Invoice created successfully. Invoice ID: ' + data.id;
        })
        .catch(error => {
            console.error('Error creating invoice:', error);
        });
}
