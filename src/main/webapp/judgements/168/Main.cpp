#include <iostream>
#include <vector>
int n, num;
std::vector<std::vector<int> > map;
bool check(int a, int b)
{
	for (int i = 1; i <= n; i++) {
		int t1 = i + b - a, t2 = a + b - i;
		if (map[i][b] || (t1 > 0 && t1 <= n && map[i][t1]) || (t2 > 0 && t2 <= n && map[i][t2]))
			return 0;
	}
}
void print()
{
	if (num < 3) {
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= n; j++)
				if (map[i][j]) std::cout << j << " ";
		std::cout << std::endl;
	}
	num++;
}
void dfs(int i)
{
	for (int j = 1; j <= n; j++)
		if (check(i, j)) {
			map[i][j] = 1;
			if (i < n) dfs(i + 1);
			else print();
			map[i][j] = 0;
		}
} 
int main()
{
	std::cin >> n;
	map.resize(n + 1);
	for (int i = 1; i <= n; i++)
		map[i].resize(n + 1);
	dfs(1);
	std::cout << num << std::endl;
	return 0;
}