@ECHO OFF
ECHO Terminating eventhandler...
      FOR /F "tokens=1" %%p in ('"jps -v | find "arrowhead-eventhandler-testcloud2-aitia-4.6.2.jar""')       DO Powershell.exe -executionpolicy remotesigned -File C:\Git\Ernstsson\D7042E, IoT-based industrial automation and digitalization\clouds\cloudlek_2\arrowhead-4.6.2\testcloud2.aitia\arrowhead-4.6.2\testcloud2.aitia\_res\stopByPID.ps1 %%p
       timeout /t 3 /nobreak > NUL
SET STILL_THERE=""
       FOR /F "tokens=1" %%p in ('"jps -v | find "arrowhead-eventhandler-testcloud2-aitia-4.6.2.jar""') DO set STILL_THERE=%%p
       IF NOT "%STILL_THERE%"=="""" ( ECHO Cannot terminate gracefully. Terminating forcefully...
      timeout /t 3 /nobreak > NUL
      FOR /F "tokens=1" %%p in ('"jps -v | find "arrowhead-eventhandler-testcloud2-aitia-4.6.2.jar""') DO taskkill /F /pid %%p)