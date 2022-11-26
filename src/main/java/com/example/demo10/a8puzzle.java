package com.example.demo10;

public class a8puzzle implements Comparable<a8puzzle>{
    int[][] grid;
    int cost=Integer.MAX_VALUE;
    a8puzzle parent;

    public void printgrid(){
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++)
                System.out.print(grid[i][j]+" ");
        System.out.print("\n");}
        System.out.print(cost);

    }
    @Override
    public boolean equals(Object o){
        if(o instanceof a8puzzle){

            for(int i=0;i<3;i++)
                for (int j=0;j<3;j++)if (((a8puzzle) o).grid[i][j]!=grid[i][j])return false;
        }
        return true;
    }
    public a8puzzle[] makechildren(a8puzzle curr){
        int posx = 0,posy = 0;


        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                if (curr.grid[i][j]==9){
                    posy=i;
                    posx=j;
                }
            }
        }

        int counterab=0;
        if (posx+1!= 3)counterab++;
        if (posx-1!= -1)counterab++;
        if (posy+1!= 3)counterab++;
        if (posy-1!= -1)counterab++;
        a8puzzle [] children=new a8puzzle[counterab];


            a8puzzle child=new a8puzzle(curr.grid);
        int count=0;
        if (posx+1!=3){
            int v;

            v=child.grid[posx][posy];
            child.grid[posx][posy]=child.grid[posx+1][posy];
            child.grid[posx+1][posy]=v;
            children[count++]=child;
         //   child.parent=curr;
            child=new a8puzzle(curr.grid);
        }
        if (posx-1!=-1){
            int v;
            v=child.grid[posx][posy];
            child.grid[posx][posy]=child.grid[posx-1][posy];
            child.grid[posx-1][posy]=v;
            children[count++]=child;
            //child.parent=curr;
            child=new a8puzzle(curr.grid);
        }
        if (posy+1!=3){
            int v;
            v=child.grid[posx][posy];
            child.grid[posx][posy]=child.grid[posx][posy+1];
            child.grid[posx][posy+1]=v;
            children[count++]=child;
//            child.parent=curr;
            child=new a8puzzle(curr.grid);
        }
        if (posy-1!=-1){
            int v;
            v=child.grid[posx][posy];
            child.grid[posx][posy]=child.grid[posx][posy-1];
            child.grid[posx][posy-1]=v;
            children[count++]=child;
//            child.parent=curr;

        }




        return children;
    }
    public a8puzzle(int[][] grid) {
        this.grid=new int[3][3];
        for (int i=0;i<3;i++)
            for (int j=0;j<3;j++)
                this.grid[i][j]=grid[i][j];
    }

    @Override
    public int compareTo(a8puzzle o) {
        if(o.cost>cost)return -1;
        else if (o.cost==cost)return 0;
        return 1;
    }
}
