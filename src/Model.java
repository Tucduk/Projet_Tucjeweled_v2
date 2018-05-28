import jdk.nashorn.internal.ir.annotations.Ignore;

import java.io.*;
import java.util.Random;

public class Model {
    private int[][] tabColor = new int[8][8];
    private Random random = new Random();
    private int ligneOucolonne;
    private int level = 1;
    private int tries = 15;
    private int score = 0;
    private int valeurAjouterProgressBarre = 0;
    private int x1;
    private int i1;
    private int couleurtemp = 0;
    private int y1;
    private int j1;
    private boolean select = false;
    private boolean select2 = false;
    private boolean partieFinie = false;
    private boolean play = true;
    private int ligne;
    private int score1;
    private int score2;
    private int score3;
    private boolean becauseOfTries;
    private int hintligne = 0;
    private int coordi,coordj;
    private boolean meilleur = false;

    public Model(){
    }


    public void Hint(){
        int[][] tabTemp = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tabTemp[i][j] = tabColor[i][j];
            }
        }
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                int temp = tabTemp[i][j];
                tabTemp[i][j] = tabTemp[i+1][j];
                tabTemp[i+1][j] = temp;
                if (!testGrille(tabTemp)){
                    coordi = i;
                    coordj = j;
                    hintligne = 1;
                    temp = tabTemp[i][j];
                    tabTemp[i][j] = tabTemp[i+1][j];
                    tabTemp[i+1][j] = temp;
                    break;
                }
                temp = tabTemp[i][j];
                tabTemp[i][j] = tabTemp[i+1][j];
                tabTemp[i+1][j] = temp;
                temp = tabTemp[i][j];
                tabTemp[i][j] = tabTemp[i][j+1];
                tabTemp[i][j+1] = temp;
                if (!testGrille(tabTemp) == true){
                    coordi = i;
                    coordj = j;
                    hintligne = 2;
                    temp = tabTemp[i][j];
                    tabTemp[i][j] = tabTemp[i][j+1];
                    tabTemp[i][j+1] = temp;
                    break;
                }
                /*if (testGrille(tabTemp)){
                    hintligne = 0;
                }*/
                temp = tabTemp[i][j];
                tabTemp[i][j] = tabTemp[i][j+1];
                tabTemp[i][j+1] = temp;

            }
        }
    }

    public void ecriture(){
        try{
            BufferedWriter fichier = new BufferedWriter(new FileWriter("src/meilleursScores.txt"));
            if (score1 != 0){
                fichier.write(String.valueOf(score1));
                fichier.newLine();
            }
            if (score1 != 0){
                fichier.write(String.valueOf(score2));
                fichier.newLine();
            }
            if (score1 != 0){
                fichier.write(String.valueOf(score3));
                fichier.newLine();
            }
            fichier.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void gestionScores(){
        lecture();
        if (score > score1){
            int temp = score1;
            score1 = score;
            score = score2;
            score2 = temp;
            score3 = score;
            meilleur = true;
        }
        if (score<score1 && score> score2 && score>score3){
            int temp = score2;
            score2 = score;
            score3 = temp;
        }
        if (score<score1 && score<score2 && score>score3){
            score3 = score;
        }
        ecriture();
    }

    public int lecture(){
        try {
            BufferedReader fichier = new BufferedReader(new FileReader("src/meilleursScores.txt"));

            score1 = Integer.parseInt(fichier.readLine());
            score2 = Integer.parseInt(fichier.readLine());
            score3 = Integer.parseInt(fichier.readLine());
            fichier.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ligne;
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
        ligneOucolonne = 0;
        if (testLigne(tabColor) || testColonne(tabColor))
            return false;
        else return true;
    }

    public boolean testLigne (int[][] tabcolor) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 6; j++) {
                if (j<4){
                    if (tabcolor[i][j] == tabcolor[i][j+1] && tabcolor[i][j] == tabcolor[i][j+2] && tabcolor[i][j] == tabcolor[i][j+3] && tabcolor[i][j] == tabcolor[i][j+4]){
                        i1 = i;
                        j1 = j;
                        ligneOucolonne = 5;
                        return true;
                    }
                }
                if (j<5){
                    if (tabcolor[i][j] == tabcolor[i][j+1] && tabcolor[i][j] == tabcolor[i][j+2] && tabcolor[i][j] == tabcolor[i][j+3]){
                        i1 = i;
                        j1 = j;
                        ligneOucolonne = 3;
                        return true;
                    }
                }
                if (tabcolor[i][j] == tabcolor[i][j+1] && tabcolor[i][j] == tabcolor[i][j+2]){
                    i1 = i;
                    j1 = j;
                    ligneOucolonne = 1;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean testColonne (int[][] tabcolor) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                if (i<4){
                    if (tabcolor[i][j] == tabcolor[i+1][j] && tabcolor[i][j] == tabcolor[i+2][j] && tabcolor[i][j] == tabcolor[i+3][j] && tabcolor[i][j] == tabcolor[i+4][j]) {
                        i1 = i;
                        j1 = j;
                        ligneOucolonne =6;
                        return true;
                    }
                }
                if (i<5){
                    if (tabcolor[i][j] == tabcolor[i+1][j] && tabcolor[i][j] == tabcolor[i+2][j] && tabcolor[i][j] == tabcolor[i+3][j]) {
                        i1 = i;
                        j1 = j;
                        ligneOucolonne =4;
                        return true;
                    }
                }
                if (tabcolor[i][j] == tabcolor[i+1][j] && tabcolor[i][j] == tabcolor[i+2][j]) {
                    i1 = i;
                    j1 = j;
                    ligneOucolonne =2;
                    return true;
                }
            }
        }
        return false;
    }

    public void destroyLorC(){
        testGrille(tabColor);
        if (ligneOucolonne%2 != 0){
            for (int i = i1; i > 0; i--) {
                int temp;
                temp = tabColor[i][j1];
                tabColor[i][j1] = tabColor[i-1][j1];
                tabColor[i-1][j1] = temp;
                temp = tabColor[i][j1+1];
                tabColor[i][j1+1] = tabColor[i-1][j1+1];
                tabColor[i-1][j1+1] = temp;
                temp = tabColor[i][j1+2];
                tabColor[i][j1+2] = tabColor[i-1][j1+2];
                tabColor[i-1][j1+2] = temp;

                if (ligneOucolonne == 3 || ligneOucolonne == 5){
                    temp = tabColor[i][j1+3];
                    tabColor[i][j1+3] = tabColor[i-1][j1+3];
                    tabColor[i-1][j1+3] = temp;
                }
                if (ligneOucolonne == 5){
                    temp = tabColor[i][j1+4];
                    tabColor[i][j1+4] = tabColor[i-1][j1+4];
                    tabColor[i-1][j1+4] = temp;
                }

            }
            tabColor[0][j1] = 1 + random.nextInt(9 - 1);
            tabColor[0][j1+1] = 1 + random.nextInt(9 - 1);
            tabColor[0][j1+2] = 1 + random.nextInt(9 - 1);
            switch (ligneOucolonne){
                case 3:
                    tabColor[0][j1+3] = 1 + random.nextInt(9 - 1);
                    break;

                case 5:
                    tabColor[0][j1+3] = 1 + random.nextInt(9 - 1);
                    tabColor[0][j1+4] = 1 + random.nextInt(9 - 1);
                    break;
            }
        }
        if (ligneOucolonne%2 == 0){
            for (int i = i1; i > 0; i--) {
                    int temp;
                    temp = tabColor[i][j1];
                    tabColor[i][j1] = tabColor[i-1][j1];
                    tabColor[i - 1][j1] = temp;
                    temp = tabColor[i + 1][j1];
                    tabColor[i + 1][j1] = tabColor[i][j1];
                    tabColor[i][j1] = temp;
                    temp = tabColor[i + 2][j1];
                    tabColor[i + 2][j1] = tabColor[i + 1][j1];
                tabColor[i + 1][j1] = temp;
                if (ligneOucolonne == 4 || ligneOucolonne == 6) {
                    temp = tabColor[i + 3][j1];
                    tabColor[i + 3][j1] = tabColor[i + 2][j1];
                    tabColor[i + 2][j1] = temp;
                }
                if (ligneOucolonne == 6 || ligneOucolonne == 8) {
                    temp = tabColor[i + 4][j1];
                        tabColor[i + 4][j1] = tabColor[i + 3][j1];
                        tabColor[i + 3][j1] = temp;
                }
            }
            tabColor[0][j1] = 1 + random.nextInt(9 - 1);
            tabColor[1][j1] = 1 + random.nextInt(9 - 1);
            tabColor[2][j1] = 1 + random.nextInt(9 - 1);
            switch (ligneOucolonne){
                case 4:
                    tabColor[3][j1] = 1 + random.nextInt(9 - 1);
                    break;

                case 6:
                    tabColor[4][j1] = 1 + random.nextInt(9 - 1);
                    break;

            }
        }
    }


    public void echanger( int x2, int y2){
        if (siaCote(x1,y1,x2,y2) && testModifIfEchange(x1,y1,x2,y2)){
            couleurtemp = tabColor[x1][y1];
            tabColor[x1][y1] = tabColor[x2][y2];
            tabColor[x2][y2] = couleurtemp;
            while(!testGrille(tabColor)){
                destroyLorC();
                if (ligneOucolonne ==1 || ligneOucolonne== 2){
                    score = score+(level*100);
                    valeurAjouterProgressBarre = 3;
                }
                if (ligneOucolonne ==3 || ligneOucolonne== 4){
                    score = score+(level*300);
                    valeurAjouterProgressBarre = 4;
                }
                if (ligneOucolonne ==5 || ligneOucolonne== 6){
                    score = score+(level*1000);
                    valeurAjouterProgressBarre = 5;
                }
            }
        }else if (testModifIfEchange(x1,y1,x2,y2) == false || siaCote(x1,y1,x2,y2)==false){
            tries = tries - 1;
            if (tries <=0){
                setPartieFinie(true);
                becauseOfTries = true;

            }
        }
    }

    public boolean testModifIfEchange(int x1, int y1, int x2, int y2){
        int[][] test = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                test[i][j] = tabColor[i][j];
            }
        }
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
            }else if (x1+1==x2){
                return true;
            }
        }else if (x1==x2){
            if (y1==y2+1){
                return true;
            }else if (y1+1==y2){
                return true;
            }
        }
        System.out.println("false x1 " + x1 + " y1 " + y1 +" x2 " + x2 + " y2 " + y2);
        return false;
    }

    public void pause(){

    }

    public int[][] getTabColor() {
        return tabColor;
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

    public int getScore() {
        return score;
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

    public void setCouleurtemp(int couleurtemp) {
        this.couleurtemp = couleurtemp;
    }

    public int getValeurAjouterProgressBarre() {
        return valeurAjouterProgressBarre;
    }

    public void setValeurAjouterProgressBarre(int valeurAjouterProgressBarre) {
        this.valeurAjouterProgressBarre = valeurAjouterProgressBarre;
    }

    public boolean isPartieFinie() {
        return partieFinie;
    }

    public void setPartieFinie(boolean partieFinie) {
        this.partieFinie = partieFinie;
    }

    public boolean isPlay() {
        return play;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }

    public int getScore1() {
        return score1;
    }

    public int getScore2() {
        return score2;
    }

    public int getScore3() {
        return score3;
    }

    public boolean isBecauseOfTries() {
        return becauseOfTries;
    }

    public void setBecauseOfTries(boolean becauseOfTries) {
        this.becauseOfTries = becauseOfTries;
    }

    public int getCoordi() {
        return coordi;
    }

    public int getCoordj() {
        return coordj;
    }

    public int isHintligne() {
        return hintligne;
    }

    public void setHintligne(int hintligne) {
        this.hintligne = hintligne;
    }

    public void setTries(int tries) {
        this.tries = tries;
    }

    public boolean isMeilleur() {
        return meilleur;
    }

    public void setMeilleur(boolean meilleur) {
        this.meilleur = meilleur;
    }
}
