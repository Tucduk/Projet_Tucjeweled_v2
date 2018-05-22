import java.util.Random;

public class Model {
    private int[][] tabColor = new int[8][8];
    private Random random = new Random();
    private int level = 0;
    private int tries = 0;
    private int score = 0;
    private int x1;
    private int x2;
    private int couleurtemp = 0;
    private int y1;
    private int y2;

    private boolean select = false;
    private boolean select2 = false;
    public Model(){
    }

    public void remplirTabColor(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int valeur = 1 + random.nextInt(9 - 1);
                tabColor[i][j] = valeur;
            }
        }
    }

    public boolean testGrille (int[][] tabColor) {
        if (testLigne(tabColor) || testColonne(tabColor) || testDiagonale(tabColor))
            return false;
        else return true;
    }

    public boolean testLigne (int[][] tabcolor) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 6; j++) {
                if (tabcolor[i][j] == tabcolor[i][j+1] && tabcolor[i][j] == tabcolor[i][j+2]){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean testColonne (int[][] tabcolor) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                if (tabcolor[i][j] == tabcolor[i+1][j] && tabcolor[i][j] == tabcolor[i+2][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean testDiagonale (int[][] tabcolor) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                if (j < 6 && i < 6) {
                    if (tabcolor[i][j] == tabcolor[i + 1][j + 1] && tabcolor[i][j] == tabcolor[i + 2][j + 2]) {
                        return true;
                    }
                }
                if (j >2 && i < 6){
                    if (tabcolor[i][j] == tabcolor[i + 1][j - 1] && tabcolor[i][j] == tabcolor[i + 2][j - 2]){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void echanger( int x2, int y2){
        if (siaCote(x1,y1,x2,y2) && !testModifIfEchange(x1,y1,x2,y2)){
            couleurtemp = tabColor[x1][y1];
            tabColor[x1][y1] = tabColor[x2][y2];
            tabColor[x2][y2] = couleurtemp;
        }
    }

    public boolean testModifIfEchange(int x1, int y1, int x2, int y2){
        int[][] test = tabColor;
        int teste2;
        teste2 = test[x1][y1];
        test[x1][y1] = test[x2][y2];
        test[x2][y2] = teste2;
        return  !testGrille(test);
    }

    public boolean siaCote(int x1, int y1, int x2, int y2){
        if (y1==y2){
            if (x1==x2+1){
                return true;
            }else if (x1-1==x2){
                return true;
            }
        }else if (x1==x2){
            if (y1==y2+1){
                return true;
            }else if (y1+1==y2){
                return true;
            }
        }
        return false;
    }

    public int[][] getTabColor() {
        return tabColor;
    }

    public void setTabColor(int nb, int i, int j) {
        tabColor[i][j] = nb;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getTries() {
        return tries;
    }

    public void setTries(int tries) {
        this.tries = tries;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public boolean isSelect2() {
        return select2;
    }

    public void setSelect2(boolean select2) {
        this.select2 = select2;
    }


    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getCouleurtemp() {
        return couleurtemp;
    }

    public void setCouleurtemp(int couleurtemp) {
        this.couleurtemp = couleurtemp;
    }
}
