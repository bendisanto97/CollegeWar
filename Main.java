import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[][] leavesInit = {
                {0, 0, 0, 0},
                {0, 1, 1, 0},
                {1, 0, 0, 1},
                {1, 0, 0, 0},
                {1, 0, 0, 0}};

        int width = leavesInit.length;
        int height = leavesInit[0].length;
        System.out.println(Arrays.deepToString(leavesInit));
        System.out.println(countLeaves(leavesInit, height, width));
        int countFinal = remainingLeaves(width, height, leavesInit, "R");
        System.out.println(countFinal);
    }

    public static int remainingLeaves(int w, int h, int[][] leaves, String winds) {
        /*
        if (w > 20 || h > 20 || w <= 0 || h <= 0 || winds.length() == 0) {

        }
        */
        for (int i = 0 ; i < winds.length() ; i++) {
            switch (winds.charAt(i)) {
                case 'L':
                    System.out.println("Moving Left");
                    leaves = moveLeft(leaves, w, h);
                    break;
                case 'R':
                    System.out.println("Moving Right");
                    leaves = moveRight(leaves, w, h);
                    break;
                case 'U':
                    System.out.println("Moving Up");
                    leaves = moveUp(leaves, w, h);
                    break;
                case 'D':
                    System.out.println("Moving Down");
                    leaves = moveDown(leaves, w, h);
                    break;
            }

        }
        return countLeaves(leaves, h, w);
    }

    public static int[][] moveLeft(int [][] leaves, int width, int height) {
        for (int i = 0 ; i < width ; i++) {
            for (int j = 0 ; j < height ; j++) {
                if (leaves[i][j] == 1) {
                    if (j-1 < 0) {
                        leaves[i][j] = 0;
                    } else {
                        leaves[i][j - 1] = 1;
                        leaves[i][j] = 0;
                    }
                    System.out.println(Arrays.deepToString(leaves));
                }
            }
        }
        //System.out.println(Arrays.deepToString(leaves));
        return leaves;
    }

    public static int[][] moveRight(int [][] leaves, int width, int height) {
        for (int i = 0 ; i < width ; i++) {
            for (int j = 0 ; j < height ; j++) {
                if ((j+2 < height && j+1 < height) && leaves[i][j] == 1 && leaves[i][j+1] == 1) {
                    leaves[i][j+2] = 2;
                    leaves[i][j+1] = 2;
                    leaves[i][j] = 0;
                }
                if (leaves[i][j] == 1) {
                    if (j+1 >= height)
                        leaves[i][j] = 0;
                    else {
                        leaves[i][j+1] = 2;
                        leaves[i][j] = 0;
                    }
                }
            }
        }

        System.out.println(Arrays.deepToString(leaves));
        return leaves;
    }

    public static int[][] moveUp(int [][] leaves, int width, int height) {
        for (int i = 0 ; i < width ; i++) {
            for (int j = 0 ; j < height ; j++) {
                if (leaves[i][j] == 1)
                    leaves[i][j-1] = leaves[i][j];
            }
        }
        return leaves;
    }

    public static int[][] moveDown(int [][] leaves, int width, int height) {
        for (int i = 0 ; i < width ; i++) {
            for (int j = 0 ; j < height ; j++) {
                if (leaves[i][j+1] == 1)
                    leaves[i][j] = leaves[i][j+1];
            }
        }
        return leaves;
    }

    public static int countLeaves(int[][] leaves, int h, int w) {
        int count = 0;
        for (int i = 0 ; i < w ; i++) {
            for (int j = 0 ; j < h ; j++) {
                if (leaves[i][j] == 1)
                    count++;
            }
        }
        return count;
    }


}