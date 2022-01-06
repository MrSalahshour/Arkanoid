package ir.sharif.math.ap99_2.arkanoid.models.prizes;

import ir.sharif.math.ap99_2.arkanoid.models.GameState;
import ir.sharif.math.ap99_2.arkanoid.models.breaks.PrizeBreak;

import java.util.LinkedList;
import java.util.Random;

public class RandomPrize extends Prize {
    private final Random random;
    private final LinkedList<Prize> prizes;

    public RandomPrize(PrizeBreak prizeBreak, GameState gameState) {
        super(prizeBreak,gameState);
        this.prizes = new LinkedList<>();
        this.prizes.add(new BigBoard(getPrizeBreak(),gameState));
        this.prizes.add(new DizzyBoard(getPrizeBreak(),gameState));
        this.prizes.add(new FastBall(getPrizeBreak(),gameState));
        this.prizes.add(new FireBall(getPrizeBreak(),gameState));
        this.prizes.add(new MultiBall(getPrizeBreak(),gameState));
        this.prizes.add(new SlowBall(getPrizeBreak(),gameState));
        this.prizes.add(new SmallBoard(getPrizeBreak(),gameState));
        this.random = new Random();
    }


    public LinkedList<Prize> getPrizes() {
        return prizes;
    }

    @Override
    public void applyPrize() {
        int prizeIndex = random.nextInt(prizes.size());
        setAvailable(false);
        setTaken(true);
        prizes.get(prizeIndex).applyPrize();
    }
    public String getName(){
        return "RandomPrize";
    }
}
