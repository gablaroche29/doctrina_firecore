package heavenly.state;

public enum GameContext {
    INSTANCE;

    private GameState currentState = GameState.MENU;

    public GameState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(GameState newState) {
        currentState = newState;
    }
}
