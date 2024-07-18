public class QuitCommand implements Command{
    GameCharacter character;

    public QuitCommand(GameCharacter gameCharacter){
        this.character = gameCharacter;
    }
    @Override
    public void execute() {
        character.quit();
    }
}
