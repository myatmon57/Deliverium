$(document).ready(function () {
  $("#user_table").DataTable({
    ajax: {
      url: "/userlist",
      dataSrc: "",
    },
    columns: [
      {
        data: "id",
        render: function(data, type, row, meta) {
          return meta.row + 1;
        }
      },
      { data: "username" },
      { data: "email" },
      {
        data: "role",
        render: function (data, type, row) {
          return data == 0 ? "admin" : "user";
        },
      },
      {
        data: "status",
        render: function (data, type, row) {
            return data == 0 ? '<i class="bi bi-slash-circle-fill text-danger"></i>' : '<i class="bi bi-check-circle-fill text-success"></i>';
        },
      },
      {
        data: "status",
        render: function (data, type, row) {
          return (
            '<button type="button" class="btn ' + (data == 1 ? " btn-danger ban-button" : " btn-primary active-button") + '" data-id="' +
            row.id +
            '">' +
            (data == 1 ? "Ban" : "Activate") +
            "</button>"
          );
        },
      },
    ],
  });
  // Handle click events on ban button
  $("#user_table").on("click", ".ban-button", function () {
    var id = $(this).data("id");
    showDialogBox(id, "ban");
  });

  // Handle click events on activate button
  $("#user_table").on("click", ".active-button", function () {
    var id = $(this).data("id");
    showDialogBox(id, "active");
  });

  function showDialogBox(id, status) {
    if (status == "ban") {
      var text = "Do you want to Ban?"
      var buttonName = "Ban"
      var status = 0
    } else {
      var text = "Do you want to Activate?"
      var buttonName = "Activate"
      var status = 1
    }
    Swal.fire({
      title: 'Are you sure?',
      text: text,
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: buttonName
    }).then((result) => {
        if (result.isConfirmed) {
          $.ajax({
            url: "/change_status",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify({
              id: id,
              status : status,
            }),
            success: function (response) {
              Swal.fire({
                title: 'Process Success',
                icon: 'success',
                showConfirmButton: false,
                timer: 2000,
            }); 
              $('#user_table').DataTable().ajax.reload();
            },
          });
        }
    });
  }
});