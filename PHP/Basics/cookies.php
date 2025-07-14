<?php

    // Access cookies on Google Chrome:
    //      - Configuration.
    //      - Privacy and security.
    //      - Cookies.

    // Cookie that doesn't expire. Its value is 1.
    setcookie('noExpires', 1);

    // Cookie that expires after 10 seconds.Its value is 2.
    setcookie('expiringCookie', 2, time() + 10);

    // Cookies that define language. No expire.
    setcookie('lang', 'eng');

    // Use of isset() function to check the cookie.
    if($_COOKIE['lang'] == 'eng') {
        echo "Your language is established as <english>.";
    };

    // Use of unset() function to unset the cookie value.
    unset($_COOKIE['noExpires']);

    // Set again of cookie 'noExpires' and its value set to 2.
    set($_COOKIE['noExpires'], 20);
?>