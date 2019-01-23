ENG
Project contains Tic Tac Toe game and bot for the game on Alpha Beta Pruning algorithm. AI choose always best move.
It's my first little bigger project, so it contains a lot of really bad code in old commits.

Main purpopose of project is learning Spring Dependency Injenction, Spring Boot, some TDD, jUnit+ Mockito, Hibernate+JPA, Lombok and basic code architecture. The view is written in jQuery.

Second purpose of project is coding library for board games with bot based on Alpha Beta Pruning algorithm: https://github.com/jansowa/BoardGame.

WHAT WOULD I CHANGE IN THID PROJECT WITH CURRENT KNOWLEDGE?
1. I would use Clean Code principles - first of all I would choose better names for variables, functions, classes, write shorter functions and use "Autowired" annotations at the constructors.
2. I would use a simpler database which would not require so much configuration before running an application.
3. I would write tests of classes independent of other objects from project. I would use more Mocks.

HOW TO RUN APPLICATION?
1. Install Java 8 or newer version.
2. Install MySQL
3. Add schema 'ticTacToeDB'
4. Add user 'ticTacToeUser' with password 'password'. This user must have privileges to 'ticTacToeDB' schema.
(You can make 2 previous steps with MySQL Workbench)
5. Download file https://github.com/jansowa/TicTacToe/blob/master/target/ticTacToe-0.0.1-SNAPSHOT.jar?raw=true
6. Open file directory in terminal and run 'java -jar ticTacToe-0.0.1-SNAPSHOT.jar'
7. Open site 'http://localhost:8080' in web browser
Author: Jan Sowa

****************************************

PL
Projekt zawiera grę Kółko i Krzyżyk i bota do niej opartego na algorytmie Alpha Beta Pruning. AI wybiera zawsze optymalny ruch.
To mój pierwszy nieco większy projekt, więc zawiera dużo naprawdę złego kodu w starych commitach.

Głównym celem tego projektu jest nauka Spring Dependency Injection, Spring Boot, nieco TDD, jUnit+Mockito, Hibernate+JPA, Lombok i podstawową architekturę oprogramowania. Widok jest napisany w jQuery.

Drugim celem projektu jest napisanie biblioteki dla gier planszowych z botem opartym o algorytm Alpha Beta Pruning: https://github.com/jansowa/BoardGame

CO ZMIENIŁBYM W PROJEKCIE Z OBECNĄ WIEDZĄ?
1. Kierowałbym się zasadami Clean Code - przede wszystkim właściwie dobierał nazwy zmiennych, funkcji, klas, pisał krótsze funkcje i stosował adnotację "Autowired" przy konstruktorze
2. Zastosowałbym prostszą bazę danych, która nie wymagałaby tylu ustawień przed włączeniem projektu
3. Pisałbym testy klas niezależne od innych obiektów z projektu - używałbym częściej Mockowania.

JAK URUCHOMIĆ APLIKACJĘ?
1. Zainstalują Java 8 lub nowszę wersję
2. Zainstaluj MySQL
3. Dodaj schemat 'ticTacToeDB'
4. Dodaj użytkownika 'ticTacToeUser' z hasłem 'password'. Ten użytkownik musi mieć uprawnienia do schematu 'ticTacToeDB'.
(Możesz wykonać 2 poprzednie kroki za pomocą MySQL Workbench)
5. Pobierz plik https://github.com/jansowa/TicTacToe/blob/master/target/ticTacToe-0.0.1-SNAPSHOT.jar?raw=true
6. Otwórz katalog pliku w terminalu i wpisz 'java -jar ticTacToe-0.0.1-SNAPSHOT.jar'
7. Otwórz stronę 'http://localhost:8080' w przeglądarce internetowej.
Autor: Jan Sowa
