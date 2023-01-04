"use strict";
let bigId = document.getElementById("form_big_id").value;
let middleId = document.getElementById("form_middle_id").value;
let smallId = document.getElementById("form_small_id").value;
console.log("処理");
$(function () {
  $.ajax({
    url: "/search/big",
    type: "GET",
    dataType: "json",
    async: true,
  })
    .done(function (data) {
      for (let i = 0; i < data.length; i++) {
        let op = document.createElement("option");
        op.value = data[i][0];
        op.text = data[i][1];
        document.getElementById("big").append(op);
        if (bigId == data[i][0]) {
          op.selected = true;
        }
      }
    })
    .fail(function (XMLHTTPRequest, textStatus, errorThrown) {
      alert("fail");
      console.log(XMLHTTPRequest);
      console.log(textStatus);
      console.log(errorThrown);
    });
});
$(function () {
  $.ajax({
    url: "/search/searchByParentId",
    type: "GET",
    dataType: "json",
    data: { parent: bigId },
    async: true,
  })
    .done(function (data) {
      for (let i = 0; i < data.length; i++) {
        let op = document.createElement("option");
        op.value = data[i][0];
        op.text = data[i][1];
        document.getElementById("middle").append(op);
        if (middleId == data[i][0]) {
          op.selected = true;
        }
      }
    })
    .fail(function (XMLHTTPRequest, textStatus, errorThrown) {
      alert("fail");
      console.log(XMLHTTPRequest);
      console.log(textStatus);
      console.log(errorThrown);
    });
  $(function () {
    $.ajax({
      url: "/search/searchByParentId",
      type: "GET",
      dataType: "json",
      data: { parent: middleId },
      async: true,
    })
      .done(function (data) {
        for (let i = 0; i < data.length; i++) {
          let op = document.createElement("option");
          op.value = data[i][0];
          op.text = data[i][1];
          document.getElementById("small").append(op);
          if (smallId == data[i][0]) {
            op.selected = true;
          }
        }
      })
      .fail(function (XMLHTTPRequest, textStatus, errorThrown) {
        alert("fail");
        console.log(XMLHTTPRequest);
        console.log(textStatus);
        console.log(errorThrown);
      });
  });
});

//中項目取得
$("#big").on("change", function () {
  let big_id = $("#big").val();
  $("#middle option:nth-child(n+2)").remove();
  $(function () {
    $.ajax({
      url: "/search/searchByParentId",
      type: "GET",
      dataType: "json",
      data: { parent: big_id },
      async: true,
    })
      .done(function (data) {
        let middleId = document.getElementById("form_middle_id").value;
        for (let i = 0; i < data.length; i++) {
          let op = document.createElement("option");
          op.value = data[i][0];
          op.text = data[i][1];
          document.getElementById("middle").append(op);
          if (middleId == data[i][0]) {
            op.selected = true;
          }
        }
      })
      .fail(function (XMLHTTPRequest, textStatus, errorThrown) {
        alert("fail");
        console.log(XMLHTTPRequest);
        console.log(textStatus);
        console.log(errorThrown);
      });
  });
});

//小項目取得
$("#middle").on("change", function () {
  let middle_id = $("#middle").val();
  $("#small option:nth-child(n+2)").remove();
  $(function () {
    $.ajax({
      url: "/search/searchByParentId",
      type: "GET",
      dataType: "json",
      data: { parent: middle_id },
      async: true,
    })
      .done(function (data) {
        let smallId = document.getElementById("form_small_id").value;
        for (let i = 0; i < data.length; i++) {
          let op = document.createElement("option");
          op.value = data[i][0];
          op.text = data[i][1];
          document.getElementById("small").append(op);
          if (smallId == data[i][0]) {
            op.selected = true;
          }
        }
      })
      .fail(function (XMLHTTPRequest, textStatus, errorThrown) {
        alert("fail");
        console.log(XMLHTTPRequest);
        console.log(textStatus);
        console.log(errorThrown);
      });
  });
});
