package com.kzl.rm.utils;

import java.util.Scanner;

public class Main {

	private int begin;
	private int end;

	public Main(int begin, int end) {
		this.begin = begin;
		this.end = end;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		while (true) {
			int n = input.nextInt();
			if (n == 0)
				return;
			Main[] mains = new Main[n];
			for (int i = 0; i < n; i++) {
				int a= input.nextInt();
				int b = input.nextInt();
				mains[i] = new Main(a,b);
			}

			for (int i = 0; i < n; i++)
				for (int j = i+ 1; j < n; j++) {
					if (mains[i].end > mains[j].end) {
						int temp = mains[i].end;
						int temp_1 = mains[i].begin;
						mains[i].end = mains[j].end;
						mains[i].begin = mains[j].begin;
						mains[j].end = temp;
						mains[j].begin = temp_1;
					}
				}

			int ans = 1;
			int endTime = mains[0].end;
			for (int i = 1; i < n; i++) {
				if(mains[i].begin>=endTime){
					ans++;
					endTime = mains[i].end;
				}
					
			}
			
			System.out.println(ans);
		}
	}

	public static void Dp() {
		Scanner input = new Scanner(System.in);
		int c = input.nextInt();

		for (int k = 1; k <= c; k++) {
			int n = input.nextInt();
			int[] a[] = new int[n][n];
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++) {
					a[i][j] = 0;
				}

			for (int i = 0; i < n; i++)
				for (int j = 0; j <= i; j++) {
					a[i][j] = input.nextInt();
				}

			for (int i = n - 1; i >= 1; i--) {
				for (int j = i; j >= 1; j--) {
					if (a[i][j] > a[i][j - 1])
						a[i - 1][j - 1] += a[i][j];
					else
						a[i - 1][j - 1] += a[i][j - 1];
				}
			}

			System.out.println(a[0][0]);
		}
	}

	public static void fun() {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();

		for (int k = 1; k <= n; k++) {
			double x1 = input.nextDouble();
			double y1 = input.nextDouble();
			double x2 = input.nextDouble();
			double y2 = input.nextDouble();

			double b = Math.sqrt(x1 * x1 + y1 * y1);
			double c = Math.sqrt(x2 * x2 + y2 * y2);
			double a = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
			double ans = Math.acos((b * b + c * c - a * a) / (2 * b * c)) / Math.PI * 180;
			System.out.println(String.format("%.2f", ans));
		}
	}
}
