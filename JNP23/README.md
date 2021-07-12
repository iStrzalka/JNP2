PSQL:
-Stworz baze jnp2db
-Stworz uzytkownika jnp2 z haslem jnp2
-Daj wszystkie uprawnienia do bazy jnp2db uzytkownikowi jnp2
-Zaimportuj plik jnp2db.sql do bazy jnp2db.
-Jezeli jest blad z komendą psql polecam zrestartować (sudo service postgresql restart)

WAŻNE : Psql musi dzialac na porcie takim samym co jest w application.properties

Backend:
-Rozpakuj pliki
-npm clean install spring-boot:run

Aplikacja działa na localhost:8080 

Url:

localhost:8080/advert - wszystkie samochody
Parametryzacja strony (GET) 
	distance - odległość od danej osoby
    min_engine_capacity,
    max_engine_capacity,
    min_price,
    max_price,
    min_rating,
    max_rating,
    posX - pozycja N współrzędnych geograficznych,
    posY - pozycja E 
	
localhost:8080/create_advert - tworzenie ogloszenia
Parametryzacja strony (POST)
	car_model_id - id modelu samochodu z bazy,
	dealership_id - id wypozyczalni z bazy,
	price - cena na miesiac,
	(opcjonalna) description - opis.

localhost:8080/book_advert - zajęcie samochodu z ogłoszenia
Parametryzacja strony (GET)
	advert_id - id ogłoszenia.
	
Frontend:
- UWAGA : PROCES ZAJMUJE ~10min
-npx create-react-app frontend
-cd frontend
-npm install @material-ui/core
-npm install @material-ui/icons
-npm install web-vitals
-Wstaw foldery src i public
-npm start

Aplikacja działa na localhost:3000