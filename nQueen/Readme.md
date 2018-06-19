# nQueen Problem

The N Queen is the problem of placing N chess queens on an N×N chessboard so that no two queens attack each other.

### Backtracking Algorithm
The idea is to place queens one by one in different columns, starting from the leftmost column. When we place a queen in a column, we check for clashes with already placed queens. In the current column, if we find a row for which there is no clash, we move to next column. If we do not find any such row due to clashes then we move back (backtrack) to previous column and modify its configuration.



1) Initially place all queens in first row. Then start with the leftmost column
2) (Check for Result state): If all queens are placed : return true and print current state.
3) Try all rows one by one in the current column.  Do following for every row.

    * If the queen can be placed safely in this row then move to next column 
    
    * Else If placing queen doesn't lead to a solution in this row then try next row .
3) If all rows have been tried and nothing worked, return false to trigger 
    backtracking (Move to previous column and modify its configuration).
