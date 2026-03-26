# String Matching Algorithms Analysis

## Project Overview
This project explores the efficiency of three classical string matching algorithms:

- Knuth–Morris–Pratt (KMP)
- Rabin–Karp
- Boyer–Moore

The algorithms are implemented and tested to analyze their performance when searching patterns in large datasets such as English text and DNA sequences.

The goal is to evaluate how preprocessing time, pattern length, and text length affect algorithm performance.

---

## Research Question

How do preprocessing time, pattern length, and text length affect the efficiency of KMP, Rabin–Karp, and Boyer–Moore algorithms when working with large text and DNA data?

---

## Algorithms Implemented

### KMP (Knuth–Morris–Pratt)
Uses prefix function (LPS array) to avoid redundant comparisons during search.

### Rabin–Karp
Uses hashing to compare pattern and text substrings efficiently.

### Boyer–Moore
Uses right-to-left comparison and character skipping techniques to improve search speed.

---

## Evaluation Metrics

The following metrics are measured during experiments:

- Execution time
- Number of character comparisons
- Preprocessing time
- Memory usage

---

## Experimental Variables

### Pattern Sizes
5, 20, 50, 100, 500 characters

### Text Sizes
10K, 100K, 1M, 5M characters

### Data Types
- English text
- DNA sequences


---

## Tools and Technologies

- Java 11+
- IntelliJ IDEA
- Git & GitHub
- Microsoft Excel / Google Sheets

---

## Expected Outcomes

- Implementation of KMP, Rabin–Karp, and Boyer–Moore algorithms
- Performance comparison across different dataset sizes
- Analysis of algorithm efficiency for large text and DNA sequences
- Graphs and visualizations of benchmark results
- Final report with conclusions based on experimental data

---

## Author

Aliona Vaha

Computer Science Project – String Matching Algorithm Analysis


