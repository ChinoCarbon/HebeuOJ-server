#include <queue>
#include <math.h>
#include <stack>
#include <stdio.h>
#include <iostream>
#include <vector>
#include <iomanip>
#include <string.h>
#include <algorithm>
using namespace std;
#define LL long long
const int N = 1e6 + 10;
const int INF = 0x3f3f3f3f;
int p[1000000],a[1000000];
int main()
{
	int n,k;
	cin >>n>>k;
	for(int i=1;i<=n;i++)
		scanf("%d",&a[i]);
	int f,r;
	f=r=1;
	for(int i=1;i<=n;i++){
		if(f != r&&i-p[f]>=k)//TODO
			f++;
		while(f != r&&a[p[r-1]]>=a[i])
			r--;//TODO}
		p[r++]=i;
		if(i>=k)
			printf("%d ",a[p[f]]);
	}
		cout<<endl;
		f=r=1;
		for(int i=1;i<=n;i++){
			if(f != r&&i-p[f]>=k)//TODO
				f++;
			while(f != r&&a[p[r-1]]<=a[i])
				r--;//TODO
			p[r++]=i;
			if(i>=k)
				printf("%d ",a[p[f]]);
	}
	cout<<endl;
	return 0;
}