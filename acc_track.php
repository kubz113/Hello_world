<?php
$con=mysqli_connect("sql110.byethost7.com","b7_17202882","inzaxwetrust2005!","b7_17202882_accel_track_db");

if (mysqli_connect_errno($con))
{
   echo '{"query_result":"ERROR"}';
}
 
$value = $_GET['accel'];
$output = $_GET['output'];
$value = (float) $value;
 
$result = mysqli_query($con,"INSERT INTO data (accel_input, string_output) 
          VALUES ('$value', '$output')");
 
if($result == true) {
    echo '{"query_result":"SUCCESS"}';
}
else{
    echo '{"query_result":"FAILURE"}';
}
mysqli_close($con);
?>