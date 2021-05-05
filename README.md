# Words statistic from HTML
## Описание: 
Данное приложение предназначено для скачивания HTML-страницы на жесткий диск компьютера с последующей выдачей статистики по количеству уникальных слов в консоль.
## Технологии в проекте:
Java 11 (Стандартная библиотека Java 11).
## Особенности: 
Приложение читает HTML- страницу, затем сохраняет результат в файл с расширением .html. Извлекает текст из видимой части сайта (Body), превращает все буквы в заглавные для объединения одинаковых слов с разными регистрами в единый элемент статистики. Помимо вывода на экран результата, статистика по колличеству уникальных слов сохраняется в файл resoult.txt в указанной директории.
## Техническое описание проекта: 
Для работы потребуется Java 11. 
Для запуска проекта на Windows запустите cmd, прейдите в путь где находится файл wordStatFromURL.jar, далее введите команду «chcp 65001»  для смены кодировки на UTF-8.
Запустите файл wordStatFromURL.jar одним из 2х вариантов:
1.	С указанием директории для сохранения.
> -Dfile.encoding=UTF-8 -jar wordStatFromURL.jar  «HTML-страница» «Директория»

Пример: java -Dfile.encoding=UTF-8 -jar wordStatFromURL.jar  https://www.simbirsoft.com D:\\downloads1
2.	Без указания директории сохранения, директория в этом случае по умолчанию D:\\downloads.
> -Dfile.encoding=UTF-8 -jar wordStatFromURL.jar  «HTML-страница»

Пример: java -Dfile.encoding=UTF-8 -jar wordStatFromURL.jar  https://www.simbirsoft.com
