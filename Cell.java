package guimaze;

public class Cell {

    private boolean live;
    private Wall[] walls = new Wall[4];

    public Cell(){
        initializeWalls();
    }

    private void initializeWalls(){
        for (int i = 0; i < 4; i++){

            if (i == 0 || i == 2 ){
                walls[i] = new Wall(true);
            }else{
                walls[i] = new Wall(false);
            }


        }
    }

    public void Draw(){
        for (int i = 0; i < 4; i ++){
            walls[i].Draw();
        }
    }

}
