$(document).ready(function () {
  // show product data into grid
  $("#product_table").DataTable({
    ajax: {
      url: "/productlist",
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
      { data: "productType" },
      { data: "price" },
      {
        data: null,
        render: function (data, type, row) {
          return (
            '<i class="fa fa-edit edit-icon" data-id="' + row.id + '"></i>'
          );
        },
      },
      {
        data: null,
        render: function (data, type, row) {
          return (
            '<i class="fa fa-trash delete-icon" data-id="' + row.id + '"></i>'
          );
        },
      },
    ],
  });

  // Handle click events on edit icons
  $("#product_table").on("click", ".edit-icon", function () {
    var id = $(this).data("id");
    window.location.href = "/product/edit?id=" + id;
  });

  // Handle click events on delete icons
  $("#product_table").on("click", ".delete-icon", function () {
    var id = $(this).data("id");
    Swal.fire({
      title: "Are you sure?",
      text: "Do you want to delete?",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Delete",
    }).then((result) => {
      if (result.isConfirmed) {
        $.ajax({
          url: "/delete_product",
          type: "POST",
          contentType: "application/json",
          data: JSON.stringify({
            id: id,
          }),
          success: function (response) {
            Swal.fire({
              title: "Product Deleted Succefully",
              icon: "success",
              showConfirmButton: false,
              timer: 2000,
            });
            $("#product_table").DataTable().ajax.reload();
          },
        });
      }
    });
  });

  // submit product form
  $(".productForm").submit(function (e) {
    e.preventDefault();
    var formId = $(this).attr("id");
    var url = formId === "product_create" ? "/create_product" : "/edit_product";
    let alert_text = formId === "product_create" ? "Product Created Succefully" : "Product Updated Succefully";

    $.ajax({
      url: url,
      type: "POST",
      contentType: "application/json",
      data: JSON.stringify({
        id: $('input[name="id"]').val(),
        productName: $('input[name="productName"]').val(),
        price: $('input[name="price"]').val(),
        productType: $('input[name="productType"]').val(),
      }),
      success: function (response) {
        Swal.fire({
          title: "Process Success",
          icon: "success",
          showConfirmButton: false,
          timer: 2000,
        });
        $('input[type="text"]').val("");
      },
    });
  });
});
