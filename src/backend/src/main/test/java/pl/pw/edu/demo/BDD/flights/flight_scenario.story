Scenario: W bazie są niepołączone miasta

Given the cities is <cities>
When startCity is <startCity>
When destCity is <destCity>
When price is <price>
When time is <time>
Then the Algoritm should return <result>

Examples:
cities           |startCity |destCity |time |price |result                                                  |
Warszawa,Legnica |Warszawa  |Legnica  |10   |10    |ok                                                      |
Warszawa,Legnica |Radom     |Legnica  |10   |10    |Miasto Radom nie zostało wprowadzone                   |
Warszawa,Legnica |Warszawa  |Radom    |10   |10    |Nie odnaleziono połączenia z miasta Warszawa do Radom |
Warszawa,Legnica |Warszawa  |Legnica  |-10  |10    |Czas -10 jest niepoprawny                               |
Warszawa,Legnica |Warszawa  |Legnica  |10   |-10   |Cena -10 jest niepoprawna                               |



Scenario: Po��czenie istnieje ju� w bazie

Given the connections is <connections>
When startCity is <startCity> 
When destCity is <destCity>
When price is <price> 
When time is <time> 
Then the Algoritm should return <result>

Examples:
connections                                     |destCity |price |result |startCity |time |
Warszawa-Legnica-250-45,Legnica-Szczecin-400-15 |Legnica  |250   |ok     |Warszawa  |45   |
Warszawa-Legnica-250-45,Legnica-Szczecin-400-15 |Szczecin |400   |ok     |Legnica   |15   |
