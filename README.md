<h1>TicTacToe project</h1>

<h3>ENG</h3>
Project contains Tic Tac Toe game and bot for the game on Alpha Beta Pruning algorithm. AI choose always best move.
It's my first little bigger project, so it contains a lot of really bad code in old commits.

Main purpopose of project is learning Spring Dependency Injenction, Spring Boot, some TDD, jUnit+ Mockito, Hibernate+JPA, Lombok and basic code architecture. The view is written in jQuery.

Second purpose of project is coding library for board games with bot based on Alpha Beta Pruning algorithm: https://github.com/jansowa/BoardGame.

<h4>WHAT WOULD I CHANGE IN THID PROJECT WITH CURRENT KNOWLEDGE?</h4>
<ol>
<li>I would use Clean Code principles - first of all I would choose better names for variables, functions, classes, write shorter functions and use "Autowired" annotations at the constructors.</li>
<li>I would use a simpler database which would not require so much configuration before running an application.</li>
<li>I would write tests of classes independent of other objects from project. I would use more Mocks.</li>
</ol>

<h4>HOW TO RUN APPLICATION?</h4>
<ol>
<li>Install Java 8 or newer version</li>
<li>Install MySQL</li>
<li>Add schema 'ticTacToeDB'</li>
<li>Add user 'ticTacToeUser' with password 'password'. This user must have privileges to 'ticTacToeDB' schema.
(You can make 2 previous steps with MySQL Workbench)</li>
<li>Download file https://github.com/jansowa/TicTacToe/blob/master/target/ticTacToe-0.0.1-SNAPSHOT.jar?raw=true</li>
<li>Open file directory in terminal and run 'java -jar ticTacToe-0.0.1-SNAPSHOT.jar'</li>
<li>Open site 'http://localhost:8080' in web browser</li>
</ol>
Author: Jan Sowa

***

<h3>PL</h3>
Projekt zawiera grę Kółko i Krzyżyk i bota do niej opartego na algorytmie Alpha Beta Pruning. AI wybiera zawsze optymalny ruch.
To mój pierwszy nieco większy projekt, więc zawiera dużo naprawdę złego kodu w starych commitach.

Głównym celem tego projektu jest nauka Spring Dependency Injection, Spring Boot, nieco TDD, jUnit+Mockito, Hibernate+JPA, Lombok i podstawową architekturę oprogramowania. Widok jest napisany w jQuery.

Drugim celem projektu jest napisanie biblioteki dla gier planszowych z botem opartym o algorytm Alpha Beta Pruning: https://github.com/jansowa/BoardGame

<h4>CO ZMIENIŁBYM W PROJEKCIE Z OBECNĄ WIEDZĄ?</h4>
<ol>
<li>Kierowałbym się zasadami Clean Code - przede wszystkim właściwie dobierał nazwy zmiennych, funkcji, klas, pisał krótsze funkcje i stosował adnotację "Autowired" przy konstruktorze</li>
<li>Zastosowałbym prostszą bazę danych, która nie wymagałaby tylu ustawień przed włączeniem projektu</li>
<li>Pisałbym testy klas niezależne od innych obiektów z projektu - używałbym częściej Mockowania.</li>
</ol>

<h4>JAK URUCHOMIĆ APLIKACJĘ?</h4>
<ol>
<li>Zainstalują Java 8 lub nowszę wersję</li>
<li>Zainstaluj MySQL</li>
<li>Dodaj schemat 'ticTacToeDB'</li>
<li>Dodaj użytkownika 'ticTacToeUser' z hasłem 'password'. Ten użytkownik musi mieć uprawnienia do schematu 'ticTacToeDB'.
(Możesz wykonać 2 poprzednie kroki za pomocą MySQL Workbench)</li>
<li>Pobierz plik https://github.com/jansowa/TicTacToe/blob/master/target/ticTacToe-0.0.1-SNAPSHOT.jar?raw=true</li>
<li>Otwórz katalog pliku w terminalu i wpisz 'java -jar ticTacToe-0.0.1-SNAPSHOT.jar'</li>
<li>Otwórz stronę 'http://localhost:8080' w przeglądarce internetowej.</li>
</ol>
Autor: Jan Sowa