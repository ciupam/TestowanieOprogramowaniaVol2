Narrative: Użytkownik chce dodać miasta
    Jako użytkownik
    Chce dodać miasta do listy miast

Scenario: Miasta nie istnieją w bazie systemu
Given the Alghorithm flightList is <flightList>
When requests is getCities
Then the Algoritm should return result

Examples:
|flightList                    |result                     |
|Ciechanów,Kraków,Poznań       |Ciechanów,Kraków,Poznań    |
|Warszawa                      |Warszawa                   |
|Ciechanów,Kraków,Poznań,Kraków|Ciechanów,Kraków,Poznań    |