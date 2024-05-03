var options = {
    chart: {
      type: 'bar'
    },
    series: [{
      name: 'sales',
      data: [30,40,45,50,70,91,125]
    }],
    xaxis: {
      categories: ["Mon","Tue","Wed","Thurs","Fri","Sat","Sun"]
    }
  }
  
  var chart = new ApexCharts(document.querySelector("#chart"), options);
  
  chart.render();