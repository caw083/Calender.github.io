<?php
//$_SESSION["favcolor"] = "yellow";
 //print_r($_SESSION);
 //$_SESSION['tes']="haha"; print_r($_SESSION); session_unset(); print_r($_SESSION);

 
 ?>

<?php
session_start();
?>
<!DOCTYPE html>
<html>
<body>
<?php
$_SESSION['test']="haha";
unset($_SESSION['test']);
echo "Data sekarang: ".$_SESSION['test'];
?>
</body>
</html>