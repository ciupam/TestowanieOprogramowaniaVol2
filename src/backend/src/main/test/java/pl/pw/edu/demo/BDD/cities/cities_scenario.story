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

Scenario: Miasta już istnieją w bazie systemu - Ciechanów, Wrocław
Given the Alghorithm with CW flightList is <flightList>
When requests is getCities
Then the Algoritm should return result

Examples:
|flightList                    |result                             |
|Kraków,Poznań                 |Ciechanów,Wrocław,Kraków,Poznań    |
|Warszawa                      |Ciechanów,Wrocław,Warszawa         |
|Ciechanów                     |Ciechanów,Wrocław                  |