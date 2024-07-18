public class MoveUpCommand implements Command{
    GameCharacter character;

    @Override
    public void execute() {
        character.moveUp();
    }

    public MoveUpCommand(GameCharacter character){
        this.character = character;
    }
}
