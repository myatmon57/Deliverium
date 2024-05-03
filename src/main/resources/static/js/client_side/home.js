$(document).ready(function () {
  $.ajax({
    url: "/productlist",
    type: "GET",
    dataType: "json",
    success: function (response) {
      console.log("Response", response);
      response.forEach(function (product, index) {
        if (index % 3 === 0) {
          $(".product_container").append('<div class="row"></div>');
        }

        var productHTML = `
                <div class="col-4">
                    <a href="product_page?id=${product.id}">
                      <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTIoel7Bu4syRn8IWLhUTtN0G25IeyMtCGVfQ&s" alt="${
                        product.productName
                        }" />
                    </a>
                    
                    <h3>${product.productName}</h3>
                    <p>${product.productType}</p>
                    <p>${product.price} Kyats</p>
                    <div class="rating">
                    ${Array(5).fill('<i class="fas fa-star"></i>').join("")}
                    ${Array(5 - 5)
                        .fill('<i class="far fa-star"></i>')
                        .join("")}
                    </div>
                    
                </div>`;
        $(".row:last-child").append(productHTML);
      });
    },
    error: function (xhr, status, error) {
      // Function to handle errors
      console.error("Error fetching product list:", error);
    },
  });
});
