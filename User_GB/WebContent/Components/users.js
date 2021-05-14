$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	{
	$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});

//SAVE
$(document).on("click", "#btnSave", function(event)
	{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateCustomerForm();
	if (status != true)
	{
	$("#alertError").text(status);
	$("#alertError").show();
	return;
	}
	
	// If valid------------------------
	var type = ($("#hidCustomerIDSave").val() == "") ? "POST" : "PUT";
	$.ajax(
	{
	url : "UserAPI",
	type : type,
	data : $("#formCustomer").serialize(),
	dataType : "text",
	complete : function(response, status)
	{
	onCustomerSaveComplete(response.responseText, status);
	}
	});
});


function onCustomerSaveComplete(response, status)
{
	if (status == "success")
	{
	var resultSet = JSON.parse(response);
	if (resultSet.status.trim() == "success")
	{
	$("#alertSuccess").text("Successfully saved.");
	$("#alertSuccess").show();
	$("#divItemsGrid").html(resultSet.data);
	} else if (resultSet.status.trim() == "error")
	{
	$("#alertError").text(resultSet.data);
	$("#alertError").show();
	}
	} else if (status == "error")
	{
	$("#alertError").text("Error while saving.");
	$("#alertError").show();
	} else
	{
	$("#alertError").text("Unknown error while saving..");
	$("#alertError").show();
	}
	$("#hidCustomerIDSave").val("");
	$("#formCustomer")[0].reset();
}


$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidCusIDSave").val($(this).data("userID"));
	$("#name").val($(this).closest("tr").find('td:eq(0)').text());
	$("#type").val($(this).closest("tr").find('td:eq(1)').text());
	$("#email").val($(this).closest("tr").find('td:eq(2)').text());
	$("#username").val($(this).closest("tr").find('td:eq(3)').text());
	$("#password").val($(this).closest("tr").find('td:eq(4)').text());
})


$(document).on("click", ".btnRemove", function(event)
{
	$.ajax(
	{
	url : "UserAPI",
	type : "DELETE",
	data : "userID=" + $(this).data("userid"),
	dataType : "text",
	complete : function(response, status)
	{
	onCustomerDeleteComplete(response.responseText, status);
	}
	});
})


function onCustomerDeleteComplete(response, status)
{
	if (status == "success")
	{
	var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
		$("#alertSuccess").text("Successfully deleted.");
		$("#alertSuccess").show();
		$("#divItemsGrid").html(resultSet.data);
		} 
		else if (resultSet.status.trim() == "error")
		{
		$("#alertError").text(resultSet.data);
		$("#alertError").show();
		}
	} 
	else if (status == "error")
	{
	$("#alertError").text("Error while deleting.");
	$("#alertError").show();
	} 
	else
	{
	$("#alertError").text("Unknown error while deleting..");
	$("#alertError").show();
	}
}





// CLIENT-MODEL================================================================
function validateCustomerForm()
{
// Name----------------
	if ($("#name").val().trim() == "")
	{
	return "Insert  Name.";
	}
//type------------
	if ($("#type").val().trim() == "")
	{
	return "Insert Type.";
}

// email-------------------------------
if ($("#email").val().trim() == "")
{
return "Insert email.";
}

//username-------------------------------
if ($("#username").val().trim() == "")
{
return "Insert username.";
}

// Password------------------------
if ($("#password").val().trim() == "")
{
return "Insert password.";
}
return true;
}

//Validate Email
const email = 
    document.getElementById('email');
email.addEventListener('blur', ()=>{
   let regex =
/^([_\-\.0-9a-zA-Z]+)@([_\-\.0-9a-zA-Z]+)\.([a-zA-Z]){2,7}$/;
   let s = email.value;
   if(regex.test(s)){
      email.classList.remove(
            'is-invalid');
      emailError = true;
    }
    else{
        email.classList.add(
              'is-invalid');
        emailError = false;
    }
})


// Validate Password
    $('#passcheck').hide();
    let passwordError = true;
    $('#password').keyup(function () {
        validatePassword();
    });
    function validatePassword() {
        let passwrdValue = 
            $('#password').val();
        if (passwrdValue.length == '') {
            $('#passcheck').show();
            passwordError = false;
            return false;
        } 
        if ((passwrdValue.length < 3)|| 
            (passwrdValue.length > 10)) {
            $('#passcheck').show();
            $('#passcheck').html
("**length of your password must be between 3 and 10");
            $('#passcheck').css("color", "red");
            passwordError = false;
            return false;
        } else {
            $('#passcheck').hide();
        }
    }