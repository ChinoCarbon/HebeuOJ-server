#include<bits/stdc++.h>
using namespace std;
int N,a[40][40],h,l;
int main(){
	cin>>N;
	a[0][N/2]=1,h=0,l=N/2;
	for(int i=2;i<=N*N;i++){
		if(h==0&&l!=N-1) a[N-1][l+1]=i,h=N-1,l++;
		else if(l==N-1&&h!=0) a[h-1][0]=i,h--,l=0;
		else if(h==0&&l==N-1) a[h+1][l]=i,h++;
		else if(h!=0&&l!=N-1){
			if(a[h-1][l+1]==0&&h-1>=0&&l+1<=N) a[h-1][l+1]=i,h--,l++;
			else a[h+1][l]=i,h++;
		}
	}
	for(int i=0;i<N;i++){
		for(int j=0;j<N;j++) cout<<a[i][j]<<' ';
		cout<<endl;
	}
}