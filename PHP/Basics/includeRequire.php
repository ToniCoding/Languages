<?php
    // To require or include part of other file in php we have the keywords "include" and "require".
    
    include "script.php"; // Includes a file but if it's not found, program will CONTINUE.
    require "script.php"; // Requires a file and if it's not found, program will STOP.

    // This program on web browser will raise warnings because of "include" and errors because of "require"

    // For code safety better use include_once and require_once.
    //include_once
    //require_once
?>