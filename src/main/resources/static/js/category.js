/**
 * 動的にカテゴリー項目を表示
 */

"use strict";

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
      }
    })
    .fail(function (XMLHTTPRequest, textStatus, errorThrown) {
      alert("fail");
      console.log(XMLHTTPRequest);
      console.log(textStatus);
      console.log(errorThrown);
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
        for (let i = 0; i < data.length; i++) {
          let op = document.createElement("option");
          op.value = data[i][0];
          op.text = data[i][1];
          document.getElementById("middle").append(op);
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
        for (let i = 0; i < data.length; i++) {
          let op = document.createElement("option");
          op.value = data[i][0];
          op.text = data[i][1];
          document.getElementById("small").append(op);
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
