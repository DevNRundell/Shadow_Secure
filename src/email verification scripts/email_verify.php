<html>

    <head>
        <title>Shadow Secure - Verify Email</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="verify_email_theme.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>

    <body>
    
        <div class="header">
            <div id="img">
            <img src="images/verify_email_banner.png" alt="Banner Image"/>
            </div>         
        </div>
        
        <div class="global_container">
            
            <div class="row">
            
                <div class="full_column">
  
                    <h3 style="color: red;">Your email cannot be verified<br></h3>
                
                    <p>
                        There has been a problem trying to verify your account. Please retry the link that was emailed to you again!<br><br>

                        If you did not create an account, please contact support immediately: <a href="mailto:support@shadowsecure.com" target="_top">support@shadowsecure.com</a><br><br>
                        
                        If you have any questions or feedback, please contact us at: <a href="mailto:feedback@shadowsecure.com" target="_top">feedback@shadowsecure.com</a>
                        
                    </p>
            
                </div>
                
            </div>
            
        </div>    
        
            <?php
        
                if(isset($_GET['verify'])) {
                    
                    $servername = "localhost";
                    $username = "root";
                    $password = "root";
                    $dbname = "shadow_secure";

                    $conn = new mysqli($servername, $username, $password, $dbname);   
                    
                    $sql = "SELECT active FROM account WHERE login = '" . $_GET['verify'] . "'";
                    $result = $conn->query($sql);
                    
                    if($result = 1) {
                        header( 'Location: email_is_verified.php' );
                        die();               
                    } else {
                    
                        $sql = "UPDATE account SET active = 1 WHERE login = '" . $_GET['verify'] . "'";
                    
                        if($conn->query($sql) === TRUE) {
                            header( 'Location: verify_email_success.php' );
                            die();
                        } else {
                            header( 'Location: verify_email_.php' );
                            die();
                        }
                    }
                
                    $conn->close();
                    
                }
            ?>
    </body>
    
</html>