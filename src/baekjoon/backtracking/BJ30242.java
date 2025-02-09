package baekjoon.backtracking;

import java.util.*;

public class BJ30242 {
    static int N;
    static int[] board;
    static boolean[] colUsed, diag1Used, diag2Used;
    static boolean solved = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        board = new int[N];
        colUsed = new boolean[N + 1];
        diag1Used = new boolean[2 * N];
        diag2Used = new boolean[2 * N];

        for (int i = 0; i < N; i++) {
            board[i] = sc.nextInt();
            if (board[i] != 0) {
                int col = board[i];
                colUsed[col] = true;
                diag1Used[i - col + N] = true;
                diag2Used[i + col] = true;
            }
        }
        sc.close();

        solve(0);
        if (!solved) System.out.println(-1);
    }

    static void solve(int row) {
        if (row == N) {
            printSolution();
            solved = true;
            return;
        }
        
        if (board[row] != 0) {
            solve(row + 1);
            return;
        }

        for (int col = 1; col <= N; col++) {
            if (colUsed[col] || diag1Used[row - col + N] || diag2Used[row + col]) continue;
            
            board[row] = col;
            colUsed[col] = diag1Used[row - col + N] = diag2Used[row + col] = true;
            solve(row + 1);
            if (solved) return;
            colUsed[col] = diag1Used[row - col + N] = diag2Used[row + col] = false;
            board[row] = 0;
        }
    }

    static void printSolution() {
        for (int i = 0; i < N; i++) {
            System.out.print(board[i] + " ");
        }
        System.out.println();
    }
}


/*
백준 N-Queen (Easy)(30242)

문제
N-Queen 문제는 크기가 N*N인 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는 문제이다.
N이 주어졌을 때, 퀸을 놓는 방법 한 가지를 출력하는 것은 쉽다.
이 문제에서는 몇 개의 퀸이 이미 놓여있을 때, 퀸을 놓는 방법 한 가지를 출력해 보자.

입력
첫 번째 줄에 정수 
N이 주어진다.(1 <= N <= 20)


만약 
Qi가 0이라면 i번째 행에는 퀸이 놓여있지 않다는 뜻이다.
퀸이 서로 공격하는 올바르지 않은 상태의 입력 혹은 
$N$개의 퀸이 모두 놓여있는 경우의 입력은 없다.

만약 
N개의 퀸을 놓을 수 없다면 -1을 출력한다.

예제 입력 1 
3
0 0 0
예제 출력 1 
-1
예제 입력 2 
4
0 0 0 0
예제 출력 2 
2 4 1 3
예제 입력 3 
4
3 0 0 0
예제 출력 3 
3 1 4 2
예제 입력 4 
4
0 0 0 1
예제 출력 4 
-1
예제 입력 5 
10
3 0 0 0 0 0 0 0 0 0
예제 출력 5 
3 1 6 9 5 10 8 4 2 7
예제 입력 6 
19
0 0 2 0 0 0 0 0 0 0 0 7 0 0 0 0 0 0 10
예제 출력 6 
1 5 2 6 17 12 8 19 14 18 15 7 3 11 9 4 13 16 10
*/