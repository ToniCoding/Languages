<?php
    // This function establishes connection to databases, send the query and echoes the result.
    function openConnection($pSrvName, $pUname, $dbName, $pPassword, $query) {
        $srvName = $pSrvName;
        $username = $pUname;
        $password = $pPassword;
        
        // Create the connection to database, send the query and then echoes the result.
        try {
            $dbConn = new PDO("mysql:host=$srvName;dbname=$dbName", $username, $password);
            $dbConn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            $sendQuery = $dbConn->query($query);

            while($result = $sendQuery->fetch(PDO::FETCH_ASSOC)) {
                echo $result['col'] . "<br/>";
            }

        } catch(PDOException $e) {
            echo "Connection failed.<br/>Reason: " . $e->getMessage();
        }
    }
?>
