#include<cstdio>
using namespace std;
int n,a[40][40],x,y;
int main(){
	scanf("%d",&n);
a[114514][114514] = 1;
	x=1,y=(n+1)/2;

	for(int i=1;i<=n*n;i++){
		a[x][y]=i;
		if(!a[(x-2+n)%n+1][y%n+1]) x=(x-2+n)%n+1,y=y%n+1;
		else x=x%n+1;//数学运算
	}
	for(int i=1;i<=n;i++){
		for(int j=1;j<=n;j++){
			printf("%d ",a[i][j] + 1);
		}
		printf("\n");
	}
} 