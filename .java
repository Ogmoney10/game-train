document).ready(function() {

    // Initialize Firebase
    var config = {
        apiKey: "AIzaSyDfsUGtZ25iIaWmpDJDoyInPMFaKcKy6fY",
        authDomain: "train-data-64fd6.firebaseapp.com",
        databaseURL: "https://train-data-64fd6.firebaseio.com",
        storageBucket: "train-data-64fd6.appspot.com",
        messagingSenderId: "471779744724"
    };

    firebase.initializeApp(config);

    // Get a reference to the database service
    var database = firebase.database();

    // Define each input with an empty string
    var trainName = "";
    var destination = "";
    var trainTime = "";
    var frequency = "";

    // Run the following function when the submit button is pressed
    $("#submit-button").on("click", function(event) {
        event.preventDefault();

        // Grabbed values from text boxes
        trainName = $("#trainName").val().trim();
        destination = $("#destination").val().trim();
        trainTime = $("#trainTime").val().trim();
        frequency = $("#frequency").val().trim();

        // Code for handling the push
        database.ref().push({
            trainName: trainName,
            destination: destination,
            trainTime: trainTime,
            frequency: frequency
        });

        // Empty the text boxes when the user data is submitted
        $("#trainName").val("");
        $("#destination").val("");
        $("#trainTime").val("");
        $("#frequency").val("");

    });

    // Firebase watcher + initial loader
    database.ref().on("child_added", function(childSnapshot) {

      // Log everything that's coming out of snapshot
      console.log(childSnapshot.val().trainName);
      console.log(childSnapshot.val().destination);
      console.log(childSnapshot.val().trainTime);
      console.log(childSnapshot.val().frequency);

      // Apply all of the snapshot data to table variables
      var trainNameTable = childSnapshot.val().trainName;
      var destinationTable = childSnapshot.val().destination;
      var frequencyTable = childSnapshot.val().frequency;
      var nextArrivalTable = childSnapshot.val().trainTime;