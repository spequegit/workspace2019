@cd C:\_workspace\HelloPiLocal\
@plink.exe 192.168.0.133 -pw pi -l pi sudo java -cp transfer/HelloPi/HelloPi-1.0-SNAPSHOT-jar-with-dependencies.jar com.hellopi.Step 0 10 200 1
@plink.exe 192.168.0.133 -pw pi -l pi sudo java -cp transfer/HelloPi/HelloPi-1.0-SNAPSHOT-jar-with-dependencies.jar com.hellopi.ClockStep