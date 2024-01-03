#include<iostream>
using namespace std;
const int maxn=252000,maxm=520;
struct node
{
	int f,t,v;
}e[maxn];
int fa[maxm];
int a,b,tot,ans,num;
bool cmp(node a,node b)
{
	return a.v<b.v;
}
inline int find(int x)
{
	return x==fa[x]?x:find(fa[x]);
}
inline void build(int a,int b)
{
	a=find(a),b=find(b);
	a>b?fa[a]=b:fa[b]=a;
}
inline void kruskal()
{
	for(int i=1;i<=b;i++)
		fa[i]=i;
	sort(e+1,e+tot+1,cmp);
	for(int i=1;i<=tot&&num!=b-1;i++)
	{
		if(find(e[i].f)!=find(e[i].t))
		{
			build(e[i].f,e[i].t);
			ans+=e[i].v;
			num++;
		}
	}
}
int main()
{
	scanf("%d%d",&a,&b);
	int c;
	for(int i=1;i<=b;i++)
		for(int j=1;j<=b;j++)
		{
			scanf("%d",&c);
			if(i==j) continue;
			if(c==0&&i!=j) c=a;
			e[++tot]=(node){i,j,c};
		}
	kruskal();
	printf("%d",ans+a);
}