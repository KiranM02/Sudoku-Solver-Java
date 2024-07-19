class Solution {
    
    static int [][]location = new int [9][9];

    public void solveSudoku(char[][] board) {
        converter(board);
        boolean b = solver(location);

System.out.println(b);
printLocation();
        if(b){
            converter_2(board);
        }

    }

    static void printLocation(){

        for(int i=0; i<9; i++)
            {
                if(i%3==0)
                {
                    System.out.println(" ");
                }
                for (int j=0 ; j<9 ; j++)
                {

                    if(j%3==0)
                    {
                        System.out.print("\t");
                    }

                    System.out.print(location[i][j]);
                }
        }
    }

    void converter_2(char[][] board){
        for (int i =0 ; i<9; i++)
        {
            for (int j =0 ; j<9 ; j++){
                if(location[i][j]!=0)//actually not needed
                    board[i][j] = (char)(location[i][j]+'0');
                else
                    board[i][j]='.';
            }
        }
    }



    public static boolean checker(int row , int col , int number){

        return rowChecker(row,col,number)&&columnChecker(row,col,number)&&boxChecker(row,col,number);
    }


    static boolean rowChecker(int rowsNow ,int colsNow , int number){
        location[rowsNow][colsNow]=number;
            for (int i = 0; i <= 8; i++) {

                for (int j = i + 1; j < 9; j++) {
                    if (location[rowsNow][i] != 0 && location[rowsNow][i] == location[rowsNow][j]) {
                        // location[rowsNow][colsNow]=0;
                        return false;
                    }
                }
            }
            location[rowsNow][colsNow]=0;

        return true;
    }

    static boolean columnChecker(int rowsNow ,int colsNow , int number){
        location[rowsNow][colsNow]=number;
            for (int i = 0; i < 9; i++) {

                for (int j = i + 1; j < 9; j++) {
                    if (location[i][colsNow] != 0 && location[i][colsNow] == location[j][colsNow]) {
                        // location[rowsNow][colsNow]=0;
                        return false;
                    }
                }
            }
        location[rowsNow][colsNow]=0;
        return true;
    }

    static boolean boxChecker(int row ,int col , int number)
    {

        location[row][col]=number;
        int rolBox=(row/3)*3;
        int colsBox=(col/3)*3;

                int[] array={-1,-1,-1,-1,-1,-1,-1,-1,-1};
                for(int i=0 ; i<3 ; i++){
                    for(int j=0 ; j<3 ; j++){
                        if(location[rolBox+i][colsBox+j] != 0   &&  array[(location[rolBox+i][colsBox+j])-1] != -1)
                        {
                           
                            // location[row][col]=0;
                            return false;
                        }
                        
                        if(location[rolBox+i][colsBox+j] != 0) {
                            array[location[rolBox + i][colsBox + j] - 1] = 1;
                        }
                    }
                }
        location[row][col]=0;
                return true;

    }
 static void converter(char[][] board){

       String [][] s = new String[9][9];

       for (int i =0 ; i<9; i++)
       {
           for (int j =0 ; j<9 ; j++){
               s[i][j] = Character.toString(board[i][j]);
           }
       }

       for (int i=0 ; i<9 ; i++)
       {
           for (int j=0 ; j<9 ; j++)
           {
               try{
                   if( Integer.parseInt(s[i][j]) <=9)
                   {
                       location[i][j] = Integer.parseInt(s[i][j]);

                   }
               }
               catch(Exception e){
                   location[i][j] = 0;
               }

           }
       }

   }


    // static boolean solver(int location[][])
    // {
    //     for (int row=0 ; row<9 ; row++){
    //         for (int column=0 ; column<9 ; column++){

    //             if (location[row][column] == 0) {

    //                 for (int number=1 ; number<10 ; number++) {

    //                     // System.out.print(checker(row,column,number));
    //                     if (checker(row,column,number)) {
    //                         // System.out.print(checker(row,column,number));
    //                         location[row][column] = number;
    //                     }
    //                     if (!solver(location)) {
    //                         location[row][column]=0;
    //                         return false;
    //                     }
    //                 }
    //             }
    //         }
    //     }
    //     return true;
    // }
    static boolean solver(int location[][])
    {

        for (int row=0 ; row<9 ; row++){
            for (int column=0 ; column<9 ; column++){

                if (location[row][column] == 0) {

                    for (int number=1 ; number<10 ; number++) {

//                        System.out.println(checker(row,column,number));
                        if (checker(row,column,number)) {
                            location[row][column] = number;
                            boolean returnedSolver = solver(location);
//                            if (!solver(location)) {
                            if (!returnedSolver){
                                location[row][column]=0;
                                
                         
//                                if(count==3) {
//                                    System.out.println("This will return false");
//                                    return false;
//                                }
//                                return false;
                            }
                            else return true;
//                            else if(number==9) {
                            if(number==9 && (!returnedSolver)) {
//                                location[row][column]=0;
             
                                return false;
                            }
                        }
//                        if (!solver(location)) {
//                            location[row][column]=0;
//                            return false;
//                        }
//                        else if(number==9 && location[row][column]==0) {
//                        else if()
                        else if(number==9) {
                            location[row][column]=0;
                            
                            return false;
                        }
                    }

//                    Count:4158
                }
            }
        }
        return true;
//        return false;
    }


}



