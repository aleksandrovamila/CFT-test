# File Filter Utility

   ## Описание
   **File Filter Utility** - это утилита для обработки файлов, содержащих различные типы данных (целые числа, вещественные числа и строки). Программа читает файлы, анализирует их содержимое и сохраняет данные в отдельные файлы в зависимости от типа.
   
## Функциональность
    - Читает входные файлы и классифицирует данные по трём категориям:
        - Целые числа (integers.txt)
        - Вещественные числа (floats.txt)
        - Строки (strings.txt)
    - Поддерживает задание каталога вывода (-o) и префикса выходных файлов (-p).
    - Поддерживает режим добавления в файлы (-a).
    - Выводит краткую (-s) или полную (-f) статистику по данным.
   
## Требования
    - Java 21 и выше
    - Maven 3.8+
   
## Установка и сборка
   
 1. Склонируйте репозиторий:
   ```sh
   git clone https://github.com/aleksandrovamila/CFT-test.git
   ```
2. Соберите проект с помощью Maven:
   ```sh
   mvn clean package
   ```
3. В результате сборки будет создан JAR-файл `util.jar`.

## Использование

### Запуск программы

```sh
java -jar util.jar [options] <input_files>
```

### Аргументы командной строки

| Аргумент  | Описание                                                        |
| --------- | --------------------------------------------------------------- |
| `-o`      | Указывает каталог вывода (по умолчанию — текущий каталог).      |
| `-p`      | Добавляет префикс к именам выходных файлов.                     |
| `-a`      | Включает режим добавления в существующие файлы.                 |
| `-s`      | Выводит краткую статистику (количество элементов каждого типа). |
| `-f`      | Выводит полную статистику (мин, макс, сумма, среднее).          |
| `<input_files>` | Список входных файлов для обработки.                            |

### Примеры использования

1. Разбор файла `int1.txt` с сохранением результатов в текущий каталог:

   ```sh
   java -jar util.jar int1.txt
   ```

2. Вывод результатов в каталог `output` с префиксом `result_`:

   ```sh
   java -jar util.jar -o output -p result_ int1.txt
   ```

3. Добавление новых данных в существующие файлы:

   ```sh
   java -jar util.jar -a int1.txt
   ```

4. Вывод краткой статистики по обработанным данным:

   ```sh
   java -jar util.jar -s int1.txt
   ```

5. Вывод полной статистики:

   ```sh
   java -jar util.jar -f int1.txt
   ```

## Пример работы

**Входные файлы (****`int1.txt, int2.txt`****)**:

- `int1.txt`:
```
Lorem ipsum dolor sit amet
45
Пример
3.1415
consectetur adipiscing
-0.001
тестовое задание
100500
```

- `int2.txt`:
```
Нормальная форма числа с плавающей запятой
1.528535047E-25
Long
1234567890123456789
```

**Выходные файлы:**

- `integers.txt`:
  ```
    45
    100500
    1234567890123456789
  ```
  
- `floats.txt`:
  ```
    3.1415
    -0.001
    1.528535047E-25
  ```
  
- `strings.txt`:
  ```
    Lorem ipsum dolor sit amet
    Пример
    consectetur adipiscing
    тестовое задание
    Нормальная форма числа с плавающей запятой
    Long
  ```

**Вывод краткой статистики (****`-s`****)**:

```
Statistics:
Integers: 3
Floats: 3
Strings: 6
```

**Вывод полной статистики (****`-f`****)**:

```
Statistics:
Integers: 3
  Min: 45
  Max: 1234567890123456789
  Sum: 1234567890123557334
  Average: 4.115226300411858E17
Floats: 3
  Min: -0.001
  Max: 3.1415
  Sum: 3.1405000000000003
  Average: 1.0468333333333335
Strings: 6
  Shortest: Long
  Longest: Нормальная форма числа с плавающей запятой
```





