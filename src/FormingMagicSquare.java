// [Problem](https://www.hackerrank.com/challenges/magic-square-forming/problem)

public class FormingMagicSquare {

    static int[][][] allPossibleSolutions =
            {
                    {{8, 1, 6}, {3, 5, 7}, {4, 9, 2}},
                    {{6, 1, 8}, {7, 5, 3}, {2, 9, 4}},
                    {{4, 9, 2}, {3, 5, 7}, {8, 1, 6}},
                    {{2, 9, 4}, {7, 5, 3}, {6, 1, 8}},
                    {{8, 3, 4}, {1, 5, 9}, {6, 7, 2}},
                    {{4, 3, 8}, {9, 5, 1}, {2, 7, 6}},
                    {{6, 7, 2}, {1, 5, 9}, {8, 3, 4}},
                    {{2, 7, 6}, {9, 5, 1}, {4, 3, 8}}
            };

    // Complete the formingMagicSquare function below.
    static int formingMagicSquare(int[][] s) {
        int minCost = 99;
        for (int i = 0; i < 8 ; i++){
            int firstRowCost = nRowCost(s[0], allPossibleSolutions[i][0]);
            int secondRowCost = nRowCost(s[1], allPossibleSolutions[i][1]);
            int thirdRowCost = nRowCost(s[2], allPossibleSolutions[i][2]);
            int totalCostForThisSolution = firstRowCost + secondRowCost + thirdRowCost;

            if (totalCostForThisSolution < minCost)
                minCost = totalCostForThisSolution;
        }
        return minCost;
    }

    private static int nRowCost(int[] matrixRow, int[] solutionRow) {
        int cost = 0;
        for(int i = 0; i < matrixRow.length; i++){
            if (matrixRow[i] != solutionRow[i]){
                cost = cost + Math.abs(solutionRow[i]-matrixRow[i]);
            }
        }

        return cost;
    }
}
