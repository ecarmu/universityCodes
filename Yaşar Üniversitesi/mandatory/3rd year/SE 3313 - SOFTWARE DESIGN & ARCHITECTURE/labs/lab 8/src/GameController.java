import java.util.Scanner;

public class GameController {
    Command[] commands;

    public GameController(){
        commands = new Command[7];

        for (Command command: commands) {
            command = null;
        }
    }

    public void setCommands(int slot, Command command){
        commands[slot] = command;
    }

    public void commandExecuted(int slot){
        commands[slot].execute();
    }

    public void displayGameMenu(){
        System.out.println(
                "Select an action: \n" +
                "w - Move Up\n" +
                "a - Move Left\n" +
                "s - Move Down\n" +
                "d - Move Right\n" +
                "j - Jump\n" +
                "k - Attack\n" +
                "q -quit\n\n" +

                "Enter your choice: "

        );
    }

    public void game(){

        boolean gameContinue = true;

        do{

            displayGameMenu();
            Scanner scanner = new Scanner(System.in);
            String st = scanner.next();

            switch (st) {
                case "w":
                    commandExecuted(0);
                    break;
                case "a":
                    commandExecuted(1);
                    break;
                case "s":
                    commandExecuted(2);;
                    break;
                case "d":
                    commandExecuted(3);
                    break;
                case "j":
                    commandExecuted(4);
                    break;
                case "k":
                    commandExecuted(5);
                    break;
                case "q":
                    commandExecuted(6);
                    gameContinue = false;
                    break;
            }
        }while (gameContinue);

    }

    public static void main(String[] args) {

        GameController gameController = new GameController();
        GameCharacter gameCharacter = new GameCharacter();

        Command attack = new AttackCommand(gameCharacter);
        Command moveUp = new MoveUpCommand(gameCharacter);
        Command moveDown = new MoveDownCommand(gameCharacter);
        Command moveLeft = new MoveLeftCommand(gameCharacter);
        Command moveRight = new MoveRightCommand(gameCharacter);
        Command jump = new JumpCommand(gameCharacter);
        Command quit = new QuitCommand(gameCharacter);



        gameController.setCommands(0, moveUp);
        gameController.setCommands(1, moveLeft);
        gameController.setCommands(2, moveDown);
        gameController.setCommands(3, moveRight);
        gameController.setCommands(4, jump);
        gameController.setCommands(5, attack);
        gameController.setCommands(6, quit);

        gameController.game();


    }
}
