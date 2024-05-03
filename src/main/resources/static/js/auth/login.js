$(document).ready(function () {
  $("#loginForm").submit(function (event) {
    event.preventDefault();
    let username = $("#username").val();
    let password = $("#password").val();


    if (username.trim() === "" && password.trim() === "") {
      $("#username").addClass("error-border");
      $("#password").addClass("error-border");
      $("#username-error-message").text("Please enter username");
      $("#password-error-message").text("Please enter password");
      return false;
    } else if (username.trim() === "") {
      $("#username").addClass("error-border");
      $("#username-error-message").text("Please enter username");
      return false;
    } else if (password.trim() === "") {
      $("#password").addClass("error-border");
      $("#password-error-message").text("Please enter password");
      return false;
    }

    let requestBody = {
      username: username,
      password: password,
    };

    fetch("/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(requestBody),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        return response.json();
      })
      .then((data) => {
        if (data) {
          console.log(data.role);
          console.log(typeof data.role);

          if (data.status !== 0) {
            Swal.fire({
              title: "Login Success",
              icon: "success",
              showConfirmButton: false,
              timer: 2000,
            });
            setTimeout(function () {
              if (data.role === 0) {
                location.href = "dashboard";
              } else if (data.role === 1) {
                location.href = "user_home";
              }
            }, 2000);
          } else {
            Swal.fire({
              title: "Login Failed",
              text: "Your account has been banned",
              icon: "error",
              confirmButtonText: "Try again",
            });
          }
        } else if (!data) {
          Swal.fire({
            title: "Login Failed",
            text: "Incorrect Username or Password",
            icon: "error",
            confirmButtonText: "Try again",
          });
        }
      })
      .catch((error) => {
        console.error("Login error:", error);
      });


    });

      $("#registerForm").submit(function (event) {
        alert('test')
        event.preventDefault();
        let username = $("#username").val();
        let password = $("#password").val();
        let email = $("#email").val();
    
        let requestBody = {
          username: username,
          password: password,
          email: email,
        };
    
        fetch("/register_user", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(requestBody),
        })
          .then((response) => {
            if (!response.ok) {
              throw new Error("Network response was not ok");
            }
            return response.json();
          })
          .then((data) => {
            if (data) {
              Swal.fire({
                title: "Registeration Success",
                icon: "success",
                showConfirmButton: false,
                timer: 2000,
              });
            }
  
          })
          .catch((error) => {
            console.error("Login error:", error);
          });
        });

  function hideErrorMessage(inputElement, errorMessageElement) {
    inputElement.removeClass("error-border");
    errorMessageElement.text("");
    errorMessageElement.hide();
  }
  $("#username").on("input", function () {
    hideErrorMessage($("#username"), $("#username-error-message"));
  });

  $("#password").on("input", function () {
    hideErrorMessage($("#password"), $("#password-error-message"));
  });
});
