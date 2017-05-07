# Projekt na przedmiot GIS - Izomorfizm Grafów

## Treść zadania

Dwa grafy G1 = (V1,E1) i G2 = (V2,E2) są izomorficzne, jeśli istnieje takie
wzajemnie jednoznaczne przekształcenie wierzchołków grafu G1 na wierzchołki grafu G2,
które zachowuje relacje przylegania wierzchołków. Należy zaimplementować algorytm sprawdzania
izomorfizmu dwóch grafów skierowanych.

## Wymagania programu
Aplikacja do poprawnego działania wymaga systemu operacyjnego
z zainstalowym oprogramowaniem Java 8.

## Uruchomienie aplikacji
 Aplikację można uruchomić po zaimportowaniu projektu do wybranego IDE
 (np. IntelliJ Idea lub Eclipse) lub z poziomu linii poleceń będąc w katalogu 'ui' projektu.
 
 ### Główny moduł aplikacji
```commandline
javac Application.java
java Application 'ścieżka do pliku wejściowego'
```

Przykład pliku wejściowego:
```json
[{
    "name": "Graph A",
    "verticesCount": 3,
    "edges": [
      {"v1": 0, "v2": 1},
      {"v1": 1, "v2": 2},
      {"v1": 2, "v2": 1}
    ]
  },
  {
    "name": "Graph B",
    "verticesCount": 3,
    "edges": [
      {"v1": 0, "v2": 1},
      {"v1": 1, "v2": 0},
      {"v1": 2, "v2": 1}
    ]
  }
]
```

### Generator przypadków testowych
```commandline
javac Generator.java
java Generator 'ścieżka do pliku wyjściowego' 'rozmiar grafu' 'prawdopodobieństwo wystąpienia krawędzi'
```
Przykładowy plik wyjściowy:
```json
{
  "graphA": {
    "name": "Graph A",
    "verticesCount": 3,
    "edges": [
      {"v1": 0, "v2": 1},
      {"v1": 1, "v2": 2},
      {"v1": 2, "v2": 1}
    ]
  },
  "graphB": {
    "name": "Graph B",
    "verticesCount": 3,
    "edges": [
      {"v1": 0, "v2": 1},
      {"v1": 1, "v2": 0},
      {"v1": 2, "v2": 1}
    ]
  },
  "verticesMapping": {
    "0": 2,
    "1": 1,
    "2": 0
  }
}
```
### Wyznaczenie średniego czasu znalezienia izomorfizmu
```commandline
javac AverageExecutionTimeChecker.java
java AverageExecutionTimeChecker 'liczba próbek' 'rozmiar grafu' 'prawdopodobieństwo wystąpienia krawędzi'
```