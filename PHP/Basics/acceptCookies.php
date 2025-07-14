<?php
    if(isset($_GET['acceptCookies'])) {
        $expireDate = time() + (60*60*24*365); // Expires in one year.
    };
?>

<?php
    if(!isset($_GET['acceptCookies']) && !isset($_COOKIE['exampleCookie'])) {
?>

<h2>You have to accept cookies in order to keep browsing this web.</h2>
<a href="?acceptCookies=1">Accept cookies</a>

<?php
    };
?>