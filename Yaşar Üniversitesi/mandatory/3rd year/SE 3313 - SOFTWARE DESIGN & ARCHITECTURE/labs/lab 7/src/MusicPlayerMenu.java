import java.util.Scanner;

public class MusicPlayerMenu {

    private static MusicPlayerMenu menu;

    private MusicPlayerMenu(){

    }

    public void work(){

        Scanner scanner = new Scanner(System.in);
        int input = -1;

        while (input != 0){
            displayMenu();
            input = scanner.nextInt();
            doOperation(input);
        }
    }

    public void displayMenu(){
        System.out.println( "\n   ==== MUSIC PLAYER MENU ====" + "\n\n"
                + "1. Play MP3" + "\n" + "2. Play FLAC" + "\n" + "3. Play AAC" + "4. Add to Playlist" + "\n" + "5. Remove from Playlist" + "\n" + "6. Show Playlist" +
                "\n" + "7. Authenticate User" + "\n" + "0. Exit" + "\n\n\n");
    }

    public static MusicPlayerMenu getInstance(){
        if (menu == null){
            menu = new MusicPlayerMenu();
        }
        return menu;
    }

    public void doOperation(int a){
        switch (a) {
            case 1 -> playMP3();
            case 2 -> playFLAC();
            case 3 -> playAAC();
            case 4 -> addPlaylist();
            case 5 -> removePlaylist();
            case 6 -> showPlaylist();
            case 7 -> authUser();
            case 8 -> logEvent();
            case 0 -> exitMenu();
        }
    }

    public void playMP3(){
        System.out.println("MP3 played");
    }

    public void playFLAC(){
        System.out.println("FLAC played");
    }

    public void playAAC(){
        System.out.println("AAC played");
    }

    public void addPlaylist(){
        System.out.println("Playlist added");
    }

    public void removePlaylist(){
        System.out.println("Playlist removed");
    }

    public void showPlaylist(){
        System.out.println("Playlist showed");
    }

    public void authUser(){
        System.out.println("User authenticated");
    }

    public void logEvent(){
        System.out.println("Event logged");
    }

    public void exitMenu(){
        System.out.println("Exited");
        menu = null;
    }



}
