#Ludo Spil
Forritið er borðspilið Ludo
Gefið er val á 2-4 leikmönnum, valkvætt er að setja nafn sitt inn og hægt er að velja liti.

#Leiðbeiningar um keyrslu
Forritið notar verkefnisstjórnunartólið Maven til þess að byggja forritið, til þess að geta notað það.
Farið eftir leiðbeiningur í eftirfarandi hlekk til þess að sækja maven:
https://maven.apache.org/install.html

Þegar maven er rétt sett upp á að vera hægt að keyra í cmd skipunina "mvn -v" sem segir þér hvaða útgáfu af maven þú hefur náð í.
Því næst opnar þú skjalamöppuna með Ludo leiknum, ferð í slóðagluggann og slærð þar inn "cmd" og ýtir á enter,
þannig færð þú command promt sem opnast beint í möppunni.
Þar skaltu skrifa "mvn clean install" til þess að byggja upp forritið.
Til þess að keyra loks forritið og spila Ludo skaltu skrifa inn "mvn clean javafx:run"

Einnig er hægt að sækja ritil eins og IntelliJ og keyrt forritið í gegnum ritilinn, sjá hlekk:
https://www.jetbrains.com/help/idea/maven-support.html
