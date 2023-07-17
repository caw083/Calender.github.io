<?php 
print_r($_FILES);

if (isset($_FILES)){
    echo "Keterangan file: <br>";
    echo "Nama file: ".$_FILES["upload"]["name"]." <br>";
//echo "Tipe file: ".$_FILES["upload"]["type"]." <br>"; 
  //  echo "Ukuran file: ".($_FILES["upload"]["size"]/1024)." KB <br>";
    //echo "Disimpan pada:" .$_FILES["upload"]["tmp_name"]." <br>";
}
echo "Keterangan file: <br>";
echo "Nama file: ".$_FILES["upload"]["name"]." <br>";
echo "Tipe file: ".$_FILES["upload"]["type"]." <br>"; 
echo "Ukuran file: ".($_FILES["upload"]["size"]/1024)." KB <br>";
echo "Disimpan pada:" .$_FILES["upload"]["tmp_name"]." <br>";

echo "<br>";

$uploadfile = "images/".$_FILES["upload"]["name"];
print_r($uploadfile);
echo ("<br>");
    // if(move_uploaded_file($_FILES["upload"]["tmp_name"], $uploadfile))
    //     { echo "Sukses upload";}
    // else{echo "Tidak berhasil upload";}

    // if(file_exists($uploadfile)){ echo "Sudah ada file ini!";
    // }else{}
    $tipefile = strtolower(pathinfo($uploadfile, PATHINFO_EXTENSION));
     if($tipefile != "jpg" && $tipefile != "png" && $tipefile != "jpeg") 
         {echo "Hanya bisa JPG, PNG, dan JPEG!";}
     else{};

    if ($_FILES["upload"]["type"] != "image/jpeg"){
        echo "<br> Helllo World";}
     else {
        if(file_exists($uploadfile)){ 
            echo "Sudah ada file ini!";
        }
        else{
            if(move_uploaded_file($_FILES["upload"]["tmp_name"], $uploadfile)){ 
                echo "Sukses upload";}
            else{
                echo "Tidak berhasil upload";}

        }


     };


?>