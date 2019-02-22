import java.util.Arrays;

public class QueenAttack {

    static int[] nearestLeftObstacle;
    static int[] nearestRightObstacle;
    static int[] nearestTopObstacle;
    static int[] nearestDownObstacle ;
    static int[] nearestTopLeftObstacle ;
    static int[] nearestTopRightObstacle;
    static int[] nearestDownLeftObstacle;
    static int[] nearestDownRightObstacle;

    static int findQueenAttack(int n, int r_q, int c_q){
        // Second part approach:
        // If there is an obstacle point for specific direction, then queen can only go that direction which is
        // (point - queen point - 1) units, because queen can not pass after this point.
        // If there is no obstacle point for specific direction, then queen can pass through that direction without stopping.


        // if there is nearest left obstacle point => queen can only go (c_q - nearestLeft column point - 1)
        // if there is no nearest left obstacle point => queen can go through all left side which is c_q - 1
        // NOTE: I have applied same approach for other directions.
        int leftAttack = nearestLeftObstacle[1] != -1 ? c_q - nearestLeftObstacle[1] - 1 : c_q - 1 ;
        int rightAttack = nearestRightObstacle[1] != n + 1 ? nearestRightObstacle[1] - c_q - 1 : n - c_q;
        int topAttack = nearestTopObstacle[0] != n + 1 ? nearestTopObstacle[0] -r_q - 1 : n - r_q;
        int downAttack = nearestDownObstacle[0] != -1 ? r_q - nearestDownObstacle[0] - 1 : r_q - 1;
        int topLeftAttack = nearestTopLeftObstacle[0] != n + 1 ? nearestTopLeftObstacle[0] - r_q - 1 : Math.min(n - r_q, c_q - 1);
        int topRightAttack = nearestTopRightObstacle[0] != n + 1 ? nearestTopRightObstacle[0] - r_q - 1 : Math.min(n - r_q, n - c_q);
        int downLeftAttack = nearestDownLeftObstacle[0] != -1 ? r_q - nearestDownLeftObstacle[0] - 1 : Math.min(r_q - 1, c_q - 1);
        int downRightAttack = nearestDownRightObstacle[0] != -1 ? r_q - nearestDownRightObstacle[0] - 1:Math.min(r_q - 1, n - c_q);

        return leftAttack + rightAttack + topAttack + downAttack + topLeftAttack + topRightAttack +
                downLeftAttack + downRightAttack;
    }


    // Solution:
    // First , find the nearest obstacle point to the queen point's for each 8 directions.
    // Second, according to the nearest point, calculate the queen's attack number
    // Note: Second will be done by findQueensAttack(...) method
    static int queensAttack(int n, int k, int r_q, int c_q, int[][] obstacles) {
        // for all example: assume r_q = 4, c_q = 3 and n = 12
        // and each nearestObstacleArray[0] represents row and the other represents column

        // nearestLeftObstacle will hold the nearest left obstacle point to the queen point's. (row, column - 1)
        // for example; if there are 2 left obstacles point such as (4, 1) and (4, 2) => this array will hold (4, 2)
        // I will fill the array with -1s. Because each obstacles iteration, I will check whether current obstacle column is greater
        // than nearestLeftObstacle[1], therefore it would be a meaningless to fill array something like n.(in that way,
        // current obstacle column would never be greater than nearestLeftObstacle[1], I would not update the array)
        nearestLeftObstacle = new int[2];
        Arrays.fill(nearestLeftObstacle, -1);

        // nearestRightObstacle will hold the nearest right obstacle point to the queen point's. (row, column + 1)
        // for example; if there are 2 left obstacles point such as (4, 12) and (4, 4) => this array will hold (4, 4)
        // I will fill the array with n+1s. Because each obstacles iteration, I will check whether current obstacle column is lower
        // than nearestRightColumn[1], therefore it would be a meaningless to fill array something like -1s.(in that way,
        // current obstacle column would never be lower than nearestRightColumn[1], I would not update the array)
        nearestRightObstacle = new int[2];
        Arrays.fill(nearestRightObstacle, n + 1);

        // nearestTopObstacle will hold the nearest top obstacle point to the queen point's. (row + 1, column)
        // for example; if there are 2 left obstacles point such as (10, 3) and (5, 3) => this array will hold (5, 3)
        // I will fill the array with n+1s. Because each obstacles iteration, I will check whether current obstacle row is lower
        // than nearestTopObstacle[0], therefore it would be a meaningless to fill array something like -1s.(in that way,
        // current obstacle row would never be lower than nearestTopObstacle[0], I would not update the array)
        nearestTopObstacle = new int[2];
        Arrays.fill(nearestTopObstacle, n + 1);

        // nearestDownObstacle will hold the nearest down obstacle point to the queen point's. (row - 1, column)
        // for example; if there are 2 left obstacles point such as (2, 3) and (3, 3) => this array will hold (3, 3)
        // I will fill the array with -1s. Because each obstacles iteration, I will check whether current obstacle row is greater
        // than nearestDownObstacle[0], therefore it would be a meaningless to fill array something like n+1s.(in that way,
        // current obstacle row would never be lower than nearestDownObstacle[0], I would not update the array)
        nearestDownObstacle = new int[2];
        Arrays.fill(nearestDownObstacle, -1);

        // nearestTopLeftObstacle will hold the nearest top-left obstacle point to the queen point's. (row + 1, column - 1)
        // for example; if there are 2 left obstacles point such as (6, 1) and (5, 2) => this array will hold (5, 2)
        nearestTopLeftObstacle = new int[2];
        nearestTopLeftObstacle[0] = n + 1;
        nearestTopLeftObstacle[1] = -1;

        // nearestTopRightObstacle will hold the nearest top-right obstacle point to the queen point's. (row + 1, column + 1)
        // for example; if there are 2 left obstacles point such as (6, 5) and (5, 4) => this array will hold (5, 4)
        nearestTopRightObstacle = new int[2];
        Arrays.fill(nearestTopRightObstacle, n + 1);

        // nearestDownLeftObstacle will hold the nearest down-left obstacle point to the queen point's. (row - 1, column - 1)
        // for example; if there are 2 left obstacles point such as (2, 1) and (3, 2) => this array will hold (3, 2)
        nearestDownLeftObstacle = new int[2];
        Arrays.fill(nearestDownLeftObstacle, -1);

        // nearestDownRightObstacle will hold the nearest down-right obstacle point to the queen point's. (row - 1, column + 1)
        // for example; if there are 2 left obstacles point such as (2, 5) and (3, 4) => this array will hold (3, 4)
        nearestDownRightObstacle = new int[2];
        nearestDownRightObstacle[0] = -1;
        nearestDownRightObstacle[1] = n + 1;

        // this for loop will do the first part of the solution
        for (int[] obstaclePoint : obstacles){
            // for each obstacle iteration, find the row and column distance from obstacle point to the queen point's.
            // I will use these distance values to determine the direction. (this obstacle point is in the left obstacle,
            // right obstacle, down-left obstacle etc...)
            int rowDistance = obstaclePoint[0] - r_q;
            int columnDistance = obstaclePoint[1] - c_q;

            // only left obstacle point (0, -1)
            // if rowDistance is equal to 0 and column distance is lower than 0 (for example; our obstacle point could be
            // (4, 1) , these 2 condition says: this is a left point) and also column of the obstaclePoint is greater than nearest one => then update the nearest left point
            if (rowDistance == 0 && columnDistance < 0 && obstaclePoint[1] >= nearestLeftObstacle[1]){
                nearestLeftObstacle[0] = obstaclePoint[0];
                nearestLeftObstacle[1] = obstaclePoint[1];
            }
            // only right obstacle point (0, +1)
            // First 2 condition says: this is right point obstacle(example: 4, 7) and also column of the obtaclePoint
            // is lower than current one => then update the nearest right point
            // For example: obstacle point (4, 7) and nearestRightObstacle(4, 6) => don't update.
            else if (rowDistance == 0 && columnDistance > 0 && obstaclePoint[1] <= nearestRightObstacle[1]){
                nearestRightObstacle[0] = obstaclePoint[0];
                nearestRightObstacle[1] = obstaclePoint[1];
            }
            // only top obstacle point (+1, 0)
            // First 2 condition says: this is top point obstacle
            else if (rowDistance > 0 && columnDistance == 0 && obstaclePoint[0] <= nearestTopObstacle[0]){
                nearestTopObstacle[0] = obstaclePoint[0];
                nearestTopObstacle[1] = obstaclePoint[1];
            }
            // only down obstacle point (-1, 0)
            // First 2 condition says: this is down point obstacle
            else if (rowDistance < 0 && columnDistance == 0 && obstaclePoint[0] >= nearestDownObstacle[0]){
                nearestDownObstacle[0] = obstaclePoint[0];
                nearestDownObstacle[1] = obstaclePoint[1];
            }
            // only top-left obstacle point (+1, -1)
            // First 2 condition says: this is top-left obstacle
            // why I used Math.abs() ? => because, regardless of the points, distance from the queen should be equal.
            // why distance should be equal ? => Because problem states that queen only can go one direction if it is
            // left, then it goes all the left side(it can not turn to the right or other directions), therefore if it goes top-left point which
            // actually is to change the points +1 row and -1 column, then
            // regardless of the points value, distance points (absolute values) must be the same.
            // For example (5, 1) can not be top-left value => |4 - 5| != |3 - 1|

            // NOTE: absolute distance must be the same also top-right, down-left and down-right. Therefore queen will never
            // encounter to the point (5, 1) - to encounter with that point, it must have changed its direction - for all directions.
            else  if (Math.abs(rowDistance) == Math.abs(columnDistance) && rowDistance > 0 && columnDistance < 0 &&
                    (obstaclePoint[0] <= nearestTopLeftObstacle[0] || obstaclePoint[1] >= nearestTopLeftObstacle[1]) ){
                nearestTopLeftObstacle[0] = obstaclePoint[0];
                nearestTopLeftObstacle[1] = obstaclePoint[1];
            }
            // only top-right obstacle point (+1, +1)
            // same approach with top-left
            else if (Math.abs(rowDistance) == Math.abs(columnDistance) && rowDistance > 0 && columnDistance > 0 &&
                    (obstaclePoint[0] <= nearestTopRightObstacle[0] || obstaclePoint[1] <= nearestTopRightObstacle[1]) ){
                nearestTopRightObstacle[0] = obstaclePoint[0];
                nearestTopRightObstacle[1] = obstaclePoint[1];
            }
            // only down-left obstacle point (-1, -1)
            // same approach with top-left
            else if (Math.abs(rowDistance) == Math.abs(columnDistance) && rowDistance <0 && columnDistance < 0 &&
                    (obstaclePoint[0] >= nearestDownLeftObstacle[0] || obstaclePoint[1] >= nearestDownLeftObstacle[1]) ){
                nearestDownLeftObstacle[0] = obstaclePoint[0];
                nearestDownLeftObstacle[1] = obstaclePoint[1];
            }
            // only down-right obstacle point(-1, +1)
            // same approach with top-left
            else if (Math.abs(rowDistance) == Math.abs(columnDistance) && rowDistance < 0 && columnDistance > 0 &&
                    (obstaclePoint[0] >= nearestDownRightObstacle[0] || obstaclePoint[1] <= nearestDownRightObstacle[1]) ){
                nearestDownRightObstacle[0] = obstaclePoint[0];
                nearestDownRightObstacle[1] = obstaclePoint[1];
            }else{
                // do nothing
            }
        }
        // this is second part of the solution
        int result = findQueenAttack(n, r_q, c_q);
        System.out.println(result);

        return result;
    }

}
