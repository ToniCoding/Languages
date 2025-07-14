<?php
    // Receiver.
    $to = "receiver@example.com";
    $subject = "Email confirmation";
    $msg = "Confirm your email";
    $from = "admin@example.com";
    $headers = "From $from";

    // Use of mail function.
    mail($to, $subject, $msg, $headers);

    echo "Mail sent";
?>