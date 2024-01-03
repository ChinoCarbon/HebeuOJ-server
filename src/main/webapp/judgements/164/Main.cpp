#include<iostream>
using namespace std;
int sum=0,n,a[101],b[101],c[101],d[101];
int print()
{
	sum++;
	if(sum<=3)
	{
		for(int i=1;i<=n;i++) printf("%d ",a[i]);
		printf("\n");
	}
}
int dfs(int x)
{
	for(int i=1;i<=n;i++)
	{
		if(!b[i] && !c[x+i] && !d[x-i+7])
		{
			a[x]=i;
			b[i]=1;		
			c[x+i]=1;	
			d[x-i+7]=1;	
			if(x==n) print();
			else dfs(x+1);
			b[i]=0;		
			c[x+i]=0;	
			d[x-i+7]=0;
		} 
	}
}
int main()
{
	scanf("%d",&n);
	dfs(1);
	printf("%d",sum);
	return 0;
}