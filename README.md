# Oblig2- Forklaringer

Navn = Rahman Shahidi
SudentNu: s300338


Oppgave 1:

Konstruktøren tar et array av en vilkårlig type. For hvert element i arrayet som ikke er lik null lages det en ny node med verdi like elementet. Når den første noden legges til i listen så settes hode lik den noden. For hvert etterfølgende node som legges til settes neste pekeren på noden før like denne noden.
Halen settes lik den siste noden.

Oppgave 2:

a)	Laget toString og omvendtString ved å iterere gjennom listen og henholdsvis starte fra hodet eller halen. 
b)	Dersom listen er tom når et element legges til settes hale og hode like en ny node, med det gitte
elementet som verdi. Hvis listen ikke er tom så settes halen like en ny node hvor den forrige
pekeren er satt til den gamle halen, mens neste er lik null. Den gamle halen sin neste peker blir satt lik den nye halen

Oppgave 3:

a) Node<T> finnNode: Hvis indeks er mindre eller like havlparten av antallet i listen letes det fra hodet, hvis ikke så letes det fra halen. Bruker en while loop der enten indeksen inkrementeres eller dekrementers. 
b) Lager en ny liste og tar i bruk leggInn metoden som ble laget i oppgave 2b. Bruker finnNode(fra) for å finne første node i sublisten. Legger de neste nodene til via for loop hvor til parameteren bestemmer hvor mange noder som legge til.

4) Indeks til: 

Looper gjennom alle nodene i listen, helt til en node med samme verdi som parameteren oppdages.
Hvis det finnes en node med samme verdi som parameteren, så returneres indeksen til noden, hvis ikke så returneres -1.

5) legg inn:

Hvis listen er tom så lages det en node som både hode og hale peker på.
Hvis noden skal legges først(og listen ikke er tom), så oppdateres hode til å peke på hode.forrige, som settes lik en ny node. Den nye noden sin neste peker, peker på det gamle hodet. 
Hvis noden ikke skal settes først eller sist, så brukes finnNode(indeks) metoden og denne noden oppdateres slik at en ny node blir lagt etter den.
Hvis noden settes siste, så gjøre det tilsvarende som nå den legges inn først, bare at hale og hale.forrige benyttes.

6)

Fjern(indeks). Først finnes noden ved hjelp av finnNode(indeks).
Dersom det første elementet skal fjernes, så endrer man hode til å peke på neste element i listen: hode = hode.neste. Hvis det eksisterer en node til høyre, så settes denne sin forrige peker lik null. Hvis ikke så er listen tom og halen settes lik null. 
Dersom det ikke er den første eller siste noden som slettes så slettes den via følgende kommando:
node.forrige.neste = node.neste;
node.neste.forrige = node.forrige;
Dersom den siste noden skal slettes, så settes halen like den forrige noden. 

Deretter settes alle medlemmene til noden som skal slettes lik null, slik at garbage collector sletter den fra minnet.

Fjern(verdi) fjerner noden med den gitt verdien på tilsvarende måte hvis den eksisterer

8a)
T next henter neste verdi i listen.
b)
returnerer en nye dobbellenketlisteiterator
c) 
sette denne lik noden med dne gitte indeksen ved bruk av hjelpemetoden finnNode(indeks)






