$(function () {

   $("#make_transfer").click(function () {
       $("#transfer_arena").toggle();
   });

   $("#view_transactions").click(function () {
       $("#transaction_list").toggle();
       $("#transfer_arena").toggle();
       $('.transfer_form')[0].reset();
       $('#transfer_form').hide();
   });

   // confirm money transfer recipient
   $("#recipient_form").submit(function (e) {
       e.preventDefault();
       var recipient_account_number = $('#recipient').val(),
           transfer = $("#transfer_arena");

       $.ajax({
           method: "POST",
           url: "/verify-account",
           data: { accountNo: recipient_account_number }
       }).done(function(msg) {
           alert("Account verified");
       }).fail(function () {
           console.log("Error!");
           transfer.append($('#transfer_form').show());
       });

       console.log(recipient_account_number);
   })
});