$(document).ready(function () {
  $("#order_table").DataTable({
    ajax: {
      url: "/getAllOrders",
      dataSrc: "",
    },
    columns: [
      {
        data: "id",
        render: function (data, type, row, meta) {
          return meta.row + 1;
        },
      },
      { data: "productName" },
      { data: "username" },
      { data: "email" },
      { data: "quantity" },
      {
        data: "status",
        render: function (data, type, row) {
          return data == 0
            ? '<i class="bi bi-hourglass-split"></i>'
            : '<div>' + (data == 1 ? '<i class="bi bi-patch-check-fill text-success"></i>' : '<i class="bi bi-ban-fill text-danger"></i>') + '</div>';
        },
      },
      {
        data: "status",
        render: function (data, type, row) {
          return data === 0
            ? '<div><i class="bi bi-check-circle big-icon text-success mr-3 accept-button" data-id="' +
                row.id +
                '" data-email="' +
                row.email +
                '"></i><i class="bi bi-x-circle-fill big-icon text-danger decline-button" data-id="' +
                row.id +
                '" data-email="' +
                row.email +
                '"></i></div>'
            : '<div>' + (data == 1 ? '<button type="button" class="btn btn-secondary" disabled>Approved</button>' : '<button type="button" class="btn btn-danger" disabled>Declined</button>') + '</div>';
        },
      },
    ],
  });
  // Handle click events on ban button
  $("#order_table").on("click", ".decline-button", function () {
    var id = $(this).data("id");
    var email = $(this).data("email");
    var emailSubject = "Order Declined"
    var emailBody = "Sorry,Your Order is Declined.This item is out of Stock"
    showDialogBox(id, "ban", email,emailBody,emailSubject);
  });

  // Handle click events on activate button
  $("#order_table").on("click", ".accept-button", function () {
    var id = $(this).data("id");
    var email = $(this).data("email");
    var emailSubject = "Order Confirmed"
    var emailBody = "Your Order is confirmed.Sender will contact you soon"
    showDialogBox(id, "active", email,emailBody,emailSubject);
  });

  function showDialogBox(id, status,email,emailBody,emailSubject) {
    if (status == "ban") {
      var text = "Decline this order?";
      var buttonName = "Decline";
      var order_status = 2;
      var title = "Order Declined"
    } else {
      var text = "Confirm this order?";
      var buttonName = "Confirm";
      var order_status = 1;
      var title = "Order Confirmed"
    }
    Swal.fire({
      title: "Are you sure?",
      text: text,
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: buttonName,
    }).then((result) => {
      if (result.isConfirmed) {
        $.ajax({
          url: "/sendMail",
          type: "POST",
          contentType: "application/json",
          data: JSON.stringify({
            recipient:email,
            msgBody: emailBody,
            subject:emailSubject
          }),
        });
        $.ajax({
          url: "/change_order_status",
          type: "POST",
          contentType: "application/json",
          data: JSON.stringify({
            id: id,
            status : order_status,
          }),
          success: function (response) {
            Swal.fire({
              title: title,
              icon: 'success',
              showConfirmButton: false,
              timer: 2000,
          }); 
            $('#order_table').DataTable().ajax.reload();
          },
        });
      }
    });
  }
});
