Narrative: Użytkownik chce poznać najkrótszy czasowo lot pomiedzy wybranymi miastami

Given the connections is <connections>
When startCity is <startCity>
When destCity is <destCity>
Then the Algoritm should return <result>

Examples:
|connections                                                      |startCity |destCity   |result
|Ciechanów-Kraków-250-45,Kraków-Poznań-400-15                     |Ciechanów |Poznań     |Poznań-Kraków-Ciechanów,60
|Warszawa-Lublin-477-80,Lublin-Poznań-400-5,Warszawa-Poznań-400-90|Warszawa  |Poznań     |Poznań-Lublin-Warszawa,85
|Warszawa-Lublin-477-80,Lublin-Poznań-400-5,Warszawa-Poznań-400-90|Warszawa  |Lubartów   |Podane miasto docelowe nie istnieje,0