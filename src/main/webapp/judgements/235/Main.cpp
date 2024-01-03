#include<iostream>
#include<algorithm>
using namespace std;
int ans=0;
struct qj
{
    long int l;
    long int r;
}qjj[1000010];//结果体中的l表示左边界，r表示右边界 
bool vis[1000010]={0};
bool comp(qj a,qj b)
{
    if(a.r>b.r) return 0;
    else return 1;
}//辅助sort排序函数
int main()
{
    int n;
    cin>>n;
    for(int i=1;i<=n;i++)
        cin>>qjj[i].l>>qjj[i].r;
    sort(qjj+1,qjj+1+n,comp);//排序
    for(int i=1;i<=n;i++)
    {
        if(!vis[i])//没有被选过，直接选择
        {
            ans++;//选择该区间 
            vis[i]=1;//标记已经选择，可删去
            for(int j=i+1;j<=n;j++)
            {
                if(qjj[i].r>qjj[j].l) //本区间的右边界在其他区间的左边界右边，则相交 
                {
                    vis[j]=1;//标记相交区间
                }
            }
        }
    }
    cout<<ans;
    return 0;
}