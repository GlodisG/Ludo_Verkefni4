# Ludo Spil
Forritið er borðspilið Ludo.
Gefið er val á 2-4 leikmönnum, valkvætt er að setja nafn sitt inn og hægt er að velja liti.

## Leiðbeiningar um keyrslu
Fyrst og fremst þarf ap hlaða niður forritinu héðan með því að ýta á "<> code" hnappinn og "download zip" síðan þarf að "extract"-a möppuna út zip skjalinu.

### Aðferð 1 - Hlaða niður Maven
Forritið notar verkefnisstjórnunartólið Maven til þess að byggja forritið, til þess að geta notað það.
Farið eftir leiðbeiningur í eftirfarandi hlekk til þess að sækja maven: 
https://maven.apache.org/install.html

Þegar maven er rétt sett upp er hægt að opna terminal og keyra
```
mvn -v
```
Síðan opnaru Ludo möppuna í terminal og skrifar
```
mvn clean install
```
``` 
mvn clean javafx:run 
```

### Aðferð 2 - Hlaða niður Intellij
Einnig er hægt að sækja Intellij og keyrt forritið í gegnum ritilinn, sjá hlekk: 
https://www.jetbrains.com/idea/download/?section=windows 
og https://www.jetbrains.com/help/idea/maven-support.html 

Síðan þarf að Ræsa Intellij. Opna skjalamöppuna með Ludo leiknum. Hægt er að finna Maven flipann á hægri hliðinni (Merkt "M"). 

Síðan þarf að ýta á 

> plugins > javafx > javafx:run
