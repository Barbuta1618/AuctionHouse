Se definesc următoarele tipuri de entități folosite de aplicație:
 - Produs, cu proprietățile int id, String nume, double prețVânzare, double prețMinim, int an
    - Tablou, cu proprietățile: String numePictor, Enum culori (ulei, tempera sau acrilic)
    - Mobilă, cu proprietățile: String tip, String material
    - Bijuterie, cu proprietățile: String material, boolean piatraPrețioasă 
- Angajat
    - Broker, cu proprietățile Lista_clienți clienți
    - Administrator
- Client, cu proprietățile: int id, String nume, String adresă, int nrParticipări, int nrLicitațiiCâștigate
    - Persoană fizică, cu proprietățile: String dataNaștere
    - Persoană juridică, cu proprietățile: Enum companie (SRL sau SA), double capitalSocial
- Licitație, cu proprietățile: int id, int nrParticipanți, int idProdus, int nrPașiMaxim
- Casa de licitații menține:
    - Lista de Produse pentru vânzare
    - Lista de Clienți din sistem
    - Lista cu Licitațiile active

Funcționalități cheie:
 - Produsele vândute de Casa de licitații sunt stocate într-o listă pe care Clienții o pot vizualiza, Brokerii o pot modifica prin ștergerea (vânzarea) de produse, iar Administratorii o pot modifica prin adăugarea de noi produse. Aceste entități pot să lucreze cu lista în același timp
 - Clienții care vor să liciteze pentru un produs solicită acest lucru Casei de licitații specificând id-ul produsului dorit. Casa de licitații asociază în mod aleatoriu un Broker fiecărui Client, iar Brokerul intermediază licitația ulterior
 - Brokerul aplică un comision pentru fiecare Client asociat. Există mai mulți algoritmi de calcul al comisionului, care diferă în funcție de Client și de istoricul acestuia:
    - C1 - 20% din valoarea tranzacționată de Client pentru Persoane fizice care au licitat de mai puțin de 5 ori
    - C2 - 15% din valoarea tranzacționată de Client pentru Persoane fizice care au licitat de mai mult de 5 ori
    - C3 - 25% din valoarea tranzacționată de Client pentru Persoane juridice care au licitat de mai puțin de 25 ori
    - C4 - 10% din valoarea tranzacționată de Client pentru Persoane juridice care au licitat de mai mult de 25 ori

Mecanismul de licitare:
1. Un Client trimite o solicitare Casei de licitație pentru un anumit produs, identificat prin id. În același timp stabilește un preț maxim pe care poate să îl plătească pentru un Produs
2. Casa de licitații asociază în mod aleatoriu un Broker Clientului
3. Casa de licitații pornește Licitația când numărul de participanți înscriși este egal cu nrParticipanți și anunță toți Brokerii de start
4. O Licitație se încheie după nrPașiMaxim. Produsul este vândut Clientului care oferă cel mai mult, atât timp cât oferta este mai mare sau egală cu prețMinim al Produsului. În caz contrar, Produsul nu se vinde. Dacă la pasul final mai mulți Clienți au oferit aceeași suma pentru Produs, se vinde celui care a câștigat cele mai multe Licitații
5. La fiecare pas p al Licitației Brokerii cer Clienților suma licitată pe care o transmit mai departe Casei de licitații. Casa de licitații calculează suma maximă oferită la fiecare pas, informează Brokerii asupra sumei maxime, iar aceștia informează mai departe Clienții
6. Un Client poate să folosească orice algoritm pentru calculul sumei de licitat. Suma nu poate depăși prețul maxim stabilit la pasul 1
7. După nrPașiMaxim Licitația e oprită, Clienții sunt notificați de rezultat și se încheie comunicarea dintre Brokeri și Clienți

Au fost folosite urmatoarele Design Patterns: 
 - Singleton
 - Builder, unde s-a folosit genericitatea
 - Mediator
