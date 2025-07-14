<?php
// With a session we can store data server-side so we can access it later.
    session_start();
    $_SESSION['my_session'] = "sessionValues";

// Syntax: $_SESSION['sessionKey'] = ['sessionValues'];
?>

<a href="sessions2.php">Sessions</a>