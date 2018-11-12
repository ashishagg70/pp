/**
 * 
 * 
 */

// Function to show snack bar
function snack(str) {
    // Get the snackbar DIV
    var x = document.getElementById("snackbar")
    x.innerHTML = str;
    // Add the "show" class to DIV
    x.className = "show";
    // After 3 seconds, remove the show class from DIV
    setTimeout(function() {
        x.className = x.className.replace("show", "");
    }, 3000);
}

// Function to activate button on basic details page
function activateButton1(element) {

    if (element.checked) {
        document.getElementById("basicbtn").disabled = false;
    } else {
        document.getElementById("basicbtn").disabled = true;
    }

}

// Function to activate button for terms and conditions
function activateButton2(element) {

    if (element.checked) {
        document.getElementById("termsbtn").disabled = false;
    } else {
        document.getElementById("termsbtn").disabled = true;
    }

}

var rootUrl = "http://localhost:2222/SAP"
var simServiceUrl = rootUrl + "/sim/validation";
var offersUrl = rootUrl + "/sim/offer";
var basicUrl = rootUrl + "/customer/validation";
var idProofUrl = rootUrl + "/idproof/validation";
var customerUrl = rootUrl + "/customer/activate"
var activateUrl = rootUrl + "/sim/activation";
var checkUserUrl=rootUrl + "/customer/identification";
var checkAddressUrl=rootUrl + "/customer/addressValidation";


$(document).ready(function() {
    // Validating SIM and Service Details
	// Sends the SIM and Service number
	// Returns success or error
	
    $('#pg1btn').click(
        function(event) {
            var simnum1 = $('#simNum1').val();
            var sernum1 = $('#servNum1').val();
            // console.log('sim' + simnum1 + 'ser' + sernum1);
            jsondata = {
            		'simNumber':simnum1,
            		'serviceNumber':sernum1
            };
            console.log(jsondata);
            $.ajax({
        		url:simServiceUrl,
        		type:'POST',
        		data:JSON.stringify(jsondata),
        		contentType:"application/json",
        		headers:{contentType:"application/x-www-form-urlencoded;charset=UTF-8"},
        		success : function(response, status, xhr) {
        			if (response) {
        				console.log("SUCCESS" + response);
        				localStorage.setItem('simNumber', simnum1);
        				localStorage.setItem('serviceNumber', sernum1);
                        $('#carousel-example-generic').carousel('next');
        			}
        		},
        		error : function(error) {
        			{
        				console.log(error.responseJSON.message);
        				snack(error.responseJSON.message);
        			}
        		}
        	});
            
            $.getJSON({
                url: offersUrl + "/" + simnum1,
                contentType:"application/json",
                headers:{contentType:"application/x-www-form-urlencoded;charset=UTF-8"},
                success: function(data) {
                    if(data) {
                    	console.log(data);
                    	 $("#offer").html(data.offerName + "<br/><br/>" +
                    			 "Get " + data.callQty + " minutes calling,<br/>" + 
                    			 data.dataQty + " MB 4G data <br/>for " + 
                    			 data.duration + " days<br><br>" + 
                    			 "at just Rs. " + data.cost);
                    	 localStorage.setItem('offerId', data.offerId);
                    	 localStorage.setItem('offerName', data.offerName);

                    }
                    else {
                    	snack("Some unknown error occurred");
                    }
                   
                },
                error: function(error) {
                    snack("Some unknown error occurred");
                }
            });

    });

    // Action on clicking offers button
    // Carousel moves to Basic User Validation
    $("#offbtn").click(function(event) {
        $('#carousel-example-generic').carousel('next');
    });

    // Basic user validation button click action
    // Verifies email and DOB
    $("#basicbtn").click(function(event) {
        var email1 = $('#email1').val();
        var dob1 = $('#dob1').val();
        console.log(email1);
        console.log(dob1);
        jsondata = {
        		'emailAddress':email1,
        		'dateOfBirth':dob1
        };
        $.ajax({
    		url:basicUrl,
    		type:'POST',
    		data:JSON.stringify(jsondata),
    		contentType:"application/json",
    		headers:{contentType:"application/x-www-form-urlencoded;charset=UTF-8"},
    		success : function(response, status, xhr) {
    			if (response) {
    				console.log("SUCCESS" + JSON.stringify(response));
    		        localStorage.setItem('email', email1);
    		        localStorage.setItem('dob', dob1);
    		        $('#dob2').val(dob1);
    		        $('#email2').val(email1);
                    $('#carousel-example-generic').carousel('next');
    			}
    		},
    		error : function(error) {
    			{
    				console.log(error.responseJSON.message);
    				snack(error.responseJSON.message);
    			}
    		}
    	});
    });

    // On Personal Details page
    $("#persbtn").click(function(event) {
        var title1 = $('#title1').val();
        var fname1 = $('#fname1').val();
        var lname1 = $('#lname1').val();
        var cemail = $('#cemail2').val();
        
        jsondata = {
        		'firstName':fname1,
        		'lastName':lname1,
        		'emailAddress':cemail
        };
        
        $.ajax({
    		url:checkUserUrl,
    		type:'POST',
    		data:JSON.stringify(jsondata),
    		contentType:"application/json",
    		headers:{contentType:"application/x-www-form-urlencoded;charset=UTF-8"},
    		success : function(response, status, xhr) {
    			if (response) {
                    $('#carousel-example-generic').carousel('next');
    			}
    		},
    		error : function(error) {
    			{
    				console.log(error.responseJSON.message);
    				snack(error.responseJSON.message);
    			}
    		}
    	});
        
       
        if (localStorage.getItem('email') == cemail) {
            localStorage.setItem('title1', title1);
            localStorage.setItem('lname', lname1);
            localStorage.setItem('fname', fname1);
        	//$('#carousel-example-generic').carousel('next');
        }
        else {
        	snack("Please check the details entered again.")
        }
    });

    
	 $('#pin11').change(function(event) { 
		 	pin1 = $("#pin11").val(); 
		 	url1 = "http://maps.googleapis.com/maps/api/geocode/json?address="+pin1+"&region=in"; 
		 	console.log(url1);
		 	$.getJSON({
		 		url : url1, 
			 	success : function(data) { 
			 		console.log(data);
			 		data1 = data;
			 		po = data1['results'][0]['address_components'];
			 		var city = po[1]['long_name'];
			 		var state = po[3]['long_name'];
			 		localStorage.setItem('state', state);
			 		console.log(city + state); 
			 		// document.getElementById('title1').value = state;
			 		$("#title1 option[value='"+state+"']").prop('selected', 'selected');

			 		$("#city1").val(city); 
			 		
		 		}, 
		 		error : function(error) {
		 			console.log('error in getting pin code data')
 				}
	 		});
	 	});
	 

    // Action on clicking Address Details page button
    $("#addrbtn").click(function(event) {
        add1 = $("#add1").val();
        state1 = $("#state1").val();
        city1 = $("#city1").val();
        pin1 = $("#pin11").val();
        
        
        jsondata = {
        		'address':add1,
        		'city':city1,
        		'pincode':pin1
        };
        
        localStorage.setItem('address', (add1));
        localStorage.setItem('city', (city1));
        localStorage.setItem('pin', (pin1));
        
        $.ajax({
    		url:checkAddressUrl,
    		type:'POST',
    		data:JSON.stringify(jsondata),
    		contentType:"application/json",
    		headers:{contentType:"application/x-www-form-urlencoded;charset=UTF-8"},
    		success : function(response, status, xhr) {
    			if (response) {
                    $('#carousel-example-generic').carousel('next');
    			}
    		},
    		error : function(error) {
    			{
    				console.log(error.responseJSON.message);
    				snack(error.responseJSON.message);
    			}
    		}
    	});
        
 		$("#state2 option[value="+state1+"]").prop('selected', 'selected');
 /*       $("#fname2").val(localStorage.getItem('fname'));
        $("#lname2").val(localStorage.getItem('lname'));*/
        $("#dob3").val(localStorage.getItem('dob'));
        $("#ffname").html(localStorage.getItem('fname2'));
        $("#flname").html(localStorage.getItem('lname2'));
        $("#femail").html(localStorage.getItem('email'));
        $("#fdob").html(localStorage.getItem('dob'));
        $("#foffer").html(localStorage.getItem('offerName'));
        $("#fservice").html(localStorage.getItem('serviceNumber'));
    });


    $("#idbtn").click(function(event) {
        state1 = $("#state2").val();
        idn = $("#idn1").val();
        fname2 = $("#fname2").val();
        lname2 = $("#lname2").val();
        idtype = $("#id1").val();
        dob = $("#dob3").val();

        localStorage.setItem('state1', (state1));
        localStorage.setItem('idn', (idn));
        localStorage.setItem('fname2', (fname2));
        localStorage.setItem('lname2', (lname2));
	        jsondata = {
	        		'idType':idtype,
	        		'uniqueIdNumber':idn,
	        		'firstName':fname2,
	        		'lastName':lname2,
	        		'state':state1,
	        		'dateOfBirth':dob
	        }
	        console.log("data" + JSON.stringify(jsondata));
			$.ajax({
	    		url:idProofUrl,
	    		type:'POST',
	    		data:JSON.stringify(jsondata),
	    		contentType:"application/json",
	    		headers:{contentType:"application/x-www-form-urlencoded;charset=UTF-8"},
	    		success : function(response, status, xhr) {
	    		
	    			if (response) {
	    				console.log("SUCCESS" + JSON.stringify(response));
	    		        $('#dob2').val(dob1);
	    		        $('#ffname').val(fname2);
	    		        $('#flname').val(lname2);
	                    $('#carousel-example-generic').carousel('next');
	    			}
	    		},
	    		error : function(error) {
	    			{
	    				console.log("FAILURE" + JSON.stringify(error));
	    				snack(error.responseJSON.message);
	    			}
	    		}
	    	});

    });

    $("#termsbtn").click(function(event) {
        jsondata = {
        		'firstName':localStorage.getItem('fname'),
        		'lastName':localStorage.getItem('lname'),
        		'email':localStorage.getItem('email'),
        		'dob':localStorage.getItem('dob'),
        		'simNumber': localStorage.getItem('simNumber')
        };
        console.log(jsondata);
        $.ajax({
    		url:customerUrl,
    		type:'POST',
    		data:JSON.stringify(jsondata),
    		contentType:"application/json",
    		headers:{contentType:"application/x-www-form-urlencoded;charset=UTF-8"},
    		success : function(response, status, xhr) {
    			if (response) {
    				console.log("SUCCESS" + JSON.stringify(response));
    				
    			}
    			$('#carousel-example-generic').carousel('next');
    		},
    		error : function(error) {
    			{
    				console.log(error.responseJSON.message);
    				snack(error.responseJSON.message);
    			}
    		}
    	});

    });


});