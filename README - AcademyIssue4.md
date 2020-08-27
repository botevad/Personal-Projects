# Personal-Projects - AcademyIssue4

ЗАДАЧА:
Направете таблица person с колони
personId – prima key varchar
givenName – varchar, собствено име
surname – varchar, фамилия
UID -  идентификатор ЕГН, SSN …

Направете таблица grade с колони
personId - primary key, varchar
grade_a  - varchar(1), една от A B C D E F G 
grade_b  - varchar(1), една от A B C D E F G
grade_c  - varchar(1), една от A B C D E F G   

Направете таблица grade_histo 
personId - varchar
grade_a  - varchar(1), една от A B C D E F G 
grade_b  - varchar(1), една от A B C D E F G
grade_c  - varchar(1), една от A B C D E F G   
histo_dt – дата на архивиране
op           - varchar, one of DEL or UPD

Напишете Rest услуги за вход/изход по таблиците person и grade. Когато обновявате/изтривате ред от grade, тогава текущите стойности на реда трябва да се прехвърлят в  grade_histo с histo_dt равно на системната дата и съответната операция UPD или DEL
Напишете Rest услуга, която позволява:  комплексно, по много колони, терсене на рейтинги; комплексно сортиране; търсене по комбинирания рeйтинг (grade_a|  grade_b | grade_c   )   
Направете тестове за услугите.
Използвайте Spring, NamedParameterJdbcOperations, Spring rest doc. Използвайте модела @RestController- >@Service -> @Repository. 
