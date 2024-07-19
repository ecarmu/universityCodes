public class JumpCommand implements Command{
    GameCharacter character;
    @Override
    public void execute() {
        character.jump();
    }

    public JumpCommand(GameCharacter character){
        this.character = character;
    }
}
