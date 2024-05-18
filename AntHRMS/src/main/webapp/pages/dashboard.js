



// Function to load the content of the selected tab

/*function loadContent(contentType) {
    var contentAreas = document.querySelectorAll('.container');
    contentAreas.forEach(function(content) {
        content.style.display = 'none'; // Hide all content areas
    });

    var targetContent = document.getElementById(contentType + '-content');
    if (targetContent) {
        targetContent.style.display = 'block'; // Show the requested content
    }
}

// Initially show the employee management section
document.addEventListener("DOMContentLoaded", function () {
    loadContent('employee'); // Load default content
});


document.addEventListener("DOMContentLoaded", function () {
    console.log("Document loaded");
    loadContent('employee');
});

  // Include the JavaScript functions here
   function edit_row(email) {
    var row = document.getElementById("row" + email);
    var firstname = row.querySelector(".firstname");
    var lastname = row.querySelector(".lastname");
    var dob = row.querySelector(".dob");
    var phone = row.querySelector(".phone");
    var hiredate = row.querySelector(".hiredate");

    var firstname_data = firstname.textContent;
    var lastname_data = lastname.textContent;
    var dob_data = dob.textContent;
    var phone_data = phone.textContent;
    var hiredate_data = hiredate.textContent;

    firstname.innerHTML = "<input type='text' value='" + firstname_data + "'>";
    lastname.innerHTML = "<input type='text' value='" + lastname_data + "'>";
    dob.innerHTML = "<input type='text' value='" + dob_data + "'>";
    phone.innerHTML = "<input type='text' value='" + phone_data + "'>";
    hiredate.innerHTML = "<input type='text' value='" + hiredate_data + "'>";

    document.getElementById("edit_button" + email).style.display = "none";
    document.getElementById("save_button" + email).style.display = "block";
}

function save_row(email) {
    var row = document.getElementById("row" + email);
    var firstname_input = row.querySelector(".firstname input").value;
    var lastname_input = row.querySelector(".lastname input").value;
    var dob_input = row.querySelector(".dob input").value;
    var phone_input = row.querySelector(".phone input").value;
    var hiredate_input = row.querySelector(".hiredate input").value;

    row.querySelector(".firstname").textContent = firstname_input;
    row.querySelector(".lastname").textContent = lastname_input;
    row.querySelector(".dob").textContent = dob_input;
    row.querySelector(".phone").textContent = phone_input;
    row.querySelector(".hiredate").textContent = hiredate_input;

    document.getElementById("edit_button" + email).style.display = "block";
    document.getElementById("save_button" + email).style.display = "none";

    // Implement AJAX call to server to save the data permanently
}

function delete_row(email) {
    document.getElementByEmail("row" + email).outerHTML = "";
    // Implement AJAX call to server to delete the data permanently
}

function search() {
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("search");
    filter = input.value.toUpperCase();
    table = document.getElementById("data_table");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

function add_row(email) {
    // Retrieve input data
    var new_firstname = document.getElementById("new_firstname").value;
    var new_lastname = document.getElementById("new_lastname").value;
    var new_dob = document.getElementById("new_dob").value;
    var new_phone = document.getElementById("phone").value;
    var new_hiredate = document.getElementById("hiredate").value;
    var new_email = document.getElementById("email").value;
    // Collect data from more input fields

    var table = document.getElementById("data_table");
    var table_len = table.rows.length; // get the number of rows in the table
    var row = table.insertRow(table_len).outerHTML =
        "<tr id='row" + table_len + "'>" +
        "<td id='firstname_row" + table_len + "'>" + new_firstname + "</td>" +
        "<td id='lastname_row" + table_len + "'>" + new_lastname + "</td>" +
        "<td id='dob_row" + table_len + "'>" + new_dob + "</td>" +
        "<td id='phone_row" + table_len + "'>" + new_phone + "</td>" +
        "<td id='hiredate_row" + table_len + "'>" + new_hiredate + "</td>" +
        "<td id='email_row" + table_len + "'>" + new_email + "</td>" 
        
        // Add more fields
        "<td><input type='button' id='edit_button" + table_len +
        "' value='Edit' class='edit' onclick='edit_row(\"" + table_len + "\")'>" +
        "<input type='button' id='save_button" + table_len +
        "' value='Save' class='save' onclick='save_row(\"" + table_len + "\")'>" +
        "<input type='button' value='Delete' class='delete' onclick='delete_row(\"" + table_len + "\")'></td>" +
        "</tr>";

    // Clear the input fields after inserting
    document.getElementById("new_firstname").value = "";
    document.getElementById("new_lastname").value = "";
    document.getElementById("new_dob").value = "";
    document.getElementById("email").value = "";
    document.getElementById("phone").value = "";
    document.getElementById("hiredate").value = "";
    document.getElementById(""); // Clear more fields
}
*/
