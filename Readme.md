## Задание

1. Основной класс приложения должен следовать правилам JavaBean, то есть инициализироваться через setter'ы. Параметры инициализации - данные для подключения к БД и число N.

2. После запуска, приложение вставляет в TEST N записей со значениями 1..N. Если в таблице TEST находились записи, то они удаляются перед вставкой.

3. Затем приложение запрашивает эти данные из TEST.FIELD и формирует корректный XML-документ вида
   <entries>
   <entry>
   <field>значение поля field</field>
   </entry>
   ...
   <entry>
   <field>значение поля field</field>
   </entry>
   </entries>
   (с N вложенных элементов <entry>)
   Документ сохраняется в файловую систему как "1.xml".

4. Посредством XSLT, приложение преобразует содержимое "1.xml" к следующему виду:
   <entries>
   <entry field="значение поля field">
   ...
   <entry field="значение поля field">
   </entries>
   (с N вложенных элементов <entry>)
   Новый документ сохраняется в файловую систему как "2.xml".

5. Приложение парсит "2.xml" и выводит арифметическую сумму значений всех атрибутов field в консоль. 

Примечания: Необходимо написать консольное приложение на Java, использующее стандартную библиотеку JDK7 (желательно) либо JDK8
При больших N (~1000000) время работы приложения не должно быть более пяти минут.
Программу нужно реализовать с учетом возможности переиспользования/встраивания в других задачах и проектах.

_Автор Гилязов Артур_

Выполнение задания решил делать со стандартными библиотеками с некоторыми дополнениями(lombok). 
Выбор на такой тип выполнения пал в связи с некоторыми требованиями из дополнения к заданию, 
ну и в связи с некоторой неуверенностью в возможности реализации проекта на фреймворке Spring(есть некоторый опыт), 
хотя полагаю это имело бы некоторое превосходство над проектом на стандартных библиотеках.