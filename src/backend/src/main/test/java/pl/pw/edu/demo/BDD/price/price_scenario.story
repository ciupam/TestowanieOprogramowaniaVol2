Narrative: Użytkownik chce poznać najkrótszy czasowo lot pomiedzy wybranymi miastami

Given the connections is <connections>
When startCity is <startCity>
When destCity is <destCity>
Then the Algoritm should return <result>

Examples:
|connections                                                      |startCity |destCity   |result
|Ciechanów-Kraków-250-45,Kraków-Poznań-400-15                     |Ciechanów |Poznań     |Poznań-Kraków-Ciechanów,650
|Warszawa-Lublin-200-80,Lublin-Poznań-10-50,Warszawa-Poznań-250-90|Warszawa  |Poznań     |Poznań-Lublin-Warszawa,210
|Warszawa-Lublin-477-80,Lublin-Poznań-400-5,Warszawa-Poznań-400-90|Warszawa  |Rybnik     |Podane miasto docelowe nie istnieje,0
|Warszawa-Lublin-477-80,Lublin-Poznań-400-5,Warszawa-Poznań-400-90|Warrzawa  |Poznań     |Podane miasta nie sa polaczone,0
|Warszawa-Lublin-477-80,Lublin-Poznań-400-5,Warszawa-Toruń-400-90 |Lublin    |Toruń      |Podane miasta nie sa polaczone,0