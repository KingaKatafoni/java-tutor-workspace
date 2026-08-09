a) Wklej output aplikacji (wynik 3 metod)
Attaching to app-1, db-1
app-1  | === Wszyscy mieszkancy ===
app-1  | 1 | Anna Nowak | Kraków | 1990
app-1  | 2 | Jan Kowalski | Warszawa | 1985
app-1  | 3 | Maria Wiśniewska | Kraków | 1995
app-1  | 4 | Piotr Zieliński | Gdańsk | 1978
app-1  | 5 | Katarzyna Wójcik | Warszawa | 2001
app-1  | 6 | Tomasz Lewandowski | Kraków | 1988
app-1  |
app-1  | === Mieszkancy Krakowa ===
app-1  | 1 | Anna Nowak | 1990
app-1  | 3 | Maria Wiśniewska | 1995
app-1  | 6 | Tomasz Lewandowski | 1988
app-1  |
app-1  | === Liczba mieszkancow per miasto ===
app-1  | Warszawa | 2
app-1  | Kraków | 3
app-1  | Gdańsk | 1

b) Wklej wynik docker compose ps (obie uslugi dzialaja)
NAME                 IMAGE              COMMAND                  SERVICE   CREATED         STATUS                     PORTS
mini_projekt-app-1   mini_projekt-app   "/__cacert_entrypoin…"   app       6 minutes ago   Exited (0) 6 minutes ago   
mini_projekt-db-1    postgres:16        "docker-entrypoint.s…"   db        7 minutes ago   Up 7 minutes               0.0.0.0:5432->5432/tcp, [::]:5432->5432/tcp
c) Wklej wynik docker images (Twoj obraz + postgres)
IMAGE                     ID             DISK USAGE   CONTENT SIZE   EXTRA 
mini_projekt-app:latest   c3d9ce1ef4fa        791MB          232MB    U   
postgres:16               95206741a5b2        663MB          165MB    U   

d) Wykonaj docker compose down, potem docker compose up --build.
Czy dane przetrwaly? Wklej SELECT * FROM resident;
| === Wszyscy mieszkancy ===
app-1  | 1 | Anna Nowak | Kraków | 1990
app-1  | 2 | Jan Kowalski | Warszawa | 1985
app-1  | 3 | Maria Wiśniewska | Kraków | 1995
app-1  | 4 | Piotr Zieliński | Gdańsk | 1978
app-1  | 5 | Katarzyna Wójcik | Warszawa | 2001
app-1  | 6 | Tomasz Lewandowski | Kraków | 1988
app-1  |
app-1  | === Mieszkancy Krakowa ===
app-1  | 1 | Anna Nowak | 1990
app-1  | 3 | Maria Wiśniewska | 1995
app-1  | 6 | Tomasz Lewandowski | 1988
app-1  |
app-1  | === Liczba mieszkancow per miasto ===
app-1  | Warszawa | 2
app-1  | Kraków | 3
app-1  | Gdańsk | 1
Wszystkie dane przetrwaly docker compose dowm
e) Odpowiedz (2-3 zdania): co sie zmienilo w Twoim rozumieniu
Dockera od L10.1 do teraz?
Na poczatku nie wieszialm nic o Docker'ze, po calym module poznalam idee konteneryzacji,
ktora ulatwia codzienna prace programisty -> ustawienia srodowiska moga byc zapisane w obrazie i odpalane przez 
zainteresowanych odbiorcow (produkcja, testerzy), mamy pewnosc ze wszyscy beda mieli te sama wersje.
Dowiedzialam sie ze nie musimy miec lokalnej bazy danych, tylko mozemy korzystac z posgresql i tworzyc bazy danych na serwerze a nie lokalnie.
Docker file czyli plik tekstowy z instrukcjami budowania obrazu, wszytsko mozemy sobie ustawic w pliku a nie za kazdym rzem wpisywac w terminalu.
Docker compose teraz wiem ze znaczaco ulatwia prace z wieloma kontenerami.
