#include<iostream>
using namespace std;
int n;
long long sum;
long long x[20];
int pd(int k)
{
	for (int i = 1; i < k; i++)
	{
		if (abs(i - k) == abs(x[i] - x[k]))
			return 0;
		else if (x[i] == x[k])
			return 0;
	}
	return 1;
}
int l = 3;
void bfs(int k)
{
	if (k > n)
	{
		sum++;
		if (l>0)
		{
			for (int i = 1; i <= n; i++)
			{
				cout << x[i] << " ";
			}
			cout << endl;
			l--;
		}
	}
	else
	{
		for (int i = 1; i <= n; i++)
		{
			x[k] = i;
			if (pd(k))
				bfs(k + 1);
		}
	}
}
int main()
{
	cin >> n;
	bfs(1);
	cout << sum;
}