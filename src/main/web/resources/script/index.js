$(function () {

   $("#make_transfer").click(function () {
       $("#transfer_arena").toggle();
   });

   $("#view_transactions").click(function () {
       $("#transaction_list").toggle();
       $("#transfer_arena").toggle();

       if ($('.transfer_form').length > 0) {
           $('.transfer_form')[0].reset();
       }

       $('#transfer_form').hide();

       var data = [
           {
               "name":       "Tiger Nixon",
               "position":   "System Architect",
               "salary":     "$3,120",
               "start_date": "2011/04/25",
               "office":     "Edinburgh",
               "extn":       "5421"
           },
           {
               "name":       "Garrett Winters",
               "position":   "Director",
               "salary":     "$5,300",
               "start_date": "2011/07/25",
               "office":     "Edinburgh",
               "extn":       "8422"
           }
       ];

       $('#table_id').DataTable({
           data: data,
           columns: [
               { data: 'name' },
               { data: 'position' },
               { data: 'salary' },
               { data: 'office' }
           ]
       });

       $.ajax({
           method: "GET",
           url: "/transactionHistory"
       }).done(function(msg) {
           alert("Display list");
       }).fail(function () {
           console.log("Error!");
       });
   });

   // confirm money transfer recipient
   $("#recipient_form").submit(function (e) {
       e.preventDefault();
       var recipient_account_number = $('#recipient').val(),
           transfer = $("#transfer_arena");

       $.ajax({
           method: "POST",
           url: "/account",
           data: { recipient: recipient_account_number }
       }).done(function(msg) {
           alert("Account verified");
       }).fail(function () {
           console.log("Error!");
           transfer.append($('#transfer_form').show());
       });

       console.log(recipient_account_number);
   })

   $("#transfer_form").submit(function (e) {
       e.preventDefault();
       console.log(e);
   })
});